package org.cl.meiqia.service.impl;

import org.cl.meiqia.asserts.Asserts;
import org.cl.meiqia.constant.Constants;
import org.cl.meiqia.entity.Balance;
import org.cl.meiqia.entity.BalanceTrade;
import org.cl.meiqia.mapper.BalanceMapper;
import org.cl.meiqia.service.BalanceService;
import org.cl.meiqia.service.BalanceTradeService;
import org.cl.meiqia.struct.PageResult;
import org.cl.meiqia.util.CodecUtils;
import org.cl.meiqia.vo.SearchVO;
import org.cl.meiqia.util.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Date;
import java.util.Objects;
import java.util.List;

/**
 * <p>
 * 用户余额 服务实现类
 * </p>
 *
 * @author yiyu
 * @since 2020-12-08
 */
@Service
public class BalanceServiceImpl extends ServiceImpl<BalanceMapper, Balance> implements BalanceService {

    @Autowired
    private BalanceTradeService balanceTradeService;

    @Override
    public PageResult<Balance> page(Integer currPage, Integer pageSize, SearchVO vo) {
        // 分页
        Page<Balance> balancePage = new Page<>(currPage, pageSize);

        // 条件查询
        QueryWrapper<Balance> wrapper = new QueryWrapper<>();

        if (Objects.nonNull(vo)) {
            String keyWord = vo.getKeyWord();
            if (StringUtils.isNotEmpty(keyWord)) {
                wrapper.like("name", keyWord);
            }
        }
        balancePage = page(balancePage, wrapper);
        return PageResult.of(balancePage.getRecords(), balancePage.getTotal());
    }

    @Override
    public boolean saveBalance(Balance balance) {
        return save(balance);
    }

    @Override
    public boolean updateBalance(Balance balance) {
        return updateById(balance);
    }

    @Override
    public boolean delById(Long id) {
        return removeById(id);
    }

    @Override
    public boolean delByIds(List<Long> ids) {
        return removeByIds(ids);
    }

    @Override
    public Balance getByUserId(Long userId){
        Balance balance = getById(userId);
        if(Objects.isNull(balance)){
            balance=init(userId);
        }
        return balance;
    }

    private Balance updateBalance(Long userId,Integer amount,Integer debit,String orderNo){
        // 获取当前人的余额信息
        Balance balance = getByUserId(userId);
        // 校验
        Asserts.isTrue(checkout(balance,userId),Constants.User.BALANCE_INFO_ERROR);
        // 修改余额值
        Integer newBalance = balance.updateBalance(amount, debit);

        //添加交易明细
        BalanceTrade balanceTrade = new BalanceTrade();
        balanceTrade.setOrderNo(orderNo);
        balanceTrade.setUserId(userId);
        balanceTrade.setDebit(debit);
        balanceTrade.setAmount(amount);
        balanceTrade.setCreated(new Date());
        balanceTrade.setBalance(newBalance);
        balanceTradeService.save(balanceTrade);

        // 修改余额
        String checkNum = getCheckNum(amount, balanceTrade.getCreated(), newBalance, orderNo, debit);
        balance.setChecksum(checkNum);
        updateById(balance);

        return balance;
    }

    private boolean checkout(Balance balance,Long userId) {
        //当前登录人的最后一条记录
        BalanceTrade balanceTrade = balanceTradeService.getLastRecord(userId);

        String checkNum = getCheckNum(balanceTrade.getAmount(), balanceTrade.getCreated(), balance.getAmounts(),
                balanceTrade.getOrderNo(), balanceTrade.getDebit());

        return  balance.getChecksum().equals(checkNum);

    }

    private Balance init(Long userId) {
        // 交易明细
        BalanceTrade balanceTrade = new BalanceTrade();
        // 当前发生金额
        balanceTrade.setAmount(0);
        // 所剩余额
        balanceTrade.setBalance(0);
        balanceTrade.setCreated(new Date());
        balanceTrade.setDebit(1);
        balanceTrade.setUserId(userId);
        // 初始化，订单单号默认为“”
        balanceTrade.setOrderNo("");
        balanceTradeService.save(balanceTrade);


        Balance balance = new Balance();
        balance.setAmounts(0);
        balance.setLocked(0);
        balance.setUserId(userId);
        // 获取校验码
        String checkNum = getCheckNum(balanceTrade.getAmount(),balanceTrade.getCreated(),balanceTrade.getBalance(),balanceTrade.getOrderNo(),balanceTrade.getDebit());

        balance.setChecksum(checkNum);
        save(balance);
        return balance;
    }

    private String getCheckNum(Integer amount, Date created, Integer balance, String orderNo, Integer debit) {
        StringBuffer buffer = new StringBuffer();
        // 拼接： 交易金额+余额+订单号+支出支入+创建时间
        buffer.append(amount).append(balance).append(orderNo).append(debit).append(created);
        return CodecUtils.md5Hex(buffer.toString(), Constants.BALANCE_SALT);
    }
}
