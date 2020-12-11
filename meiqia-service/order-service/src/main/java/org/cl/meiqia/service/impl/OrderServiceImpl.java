package org.cl.meiqia.service.impl;

import org.cl.meiqia.entity.Order;
import org.cl.meiqia.mapper.OrderMapper;
import org.cl.meiqia.service.OrderService;
import org.cl.meiqia.struct.PageResult;
import org.cl.meiqia.vo.SearchVO;
import org.cl.meiqia.util.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Objects;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yiyu
 * @since 2020-12-08
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public PageResult<Order> page(Integer currPage, Integer pageSize, SearchVO vo) {
        // 分页
        Page<Order> orderPage = new Page<>(currPage, pageSize);

        // 条件查询
        QueryWrapper<Order> wrapper = new QueryWrapper<>();

        if (Objects.nonNull(vo)) {
            String keyWord = vo.getKeyWord();
            if (StringUtils.isNotEmpty(keyWord)) {
                wrapper.like("name", keyWord);
            }
        }
        orderPage = page(orderPage, wrapper);
        return PageResult.of(orderPage.getRecords(), orderPage.getTotal());
    }

    @Override
    public boolean saveOrder(Order order) {
        return save(order);
    }

    @Override
    public boolean updateOrder(Order order) {
        return updateById(order);
    }

    @Override
    public boolean delById(Long id) {
        return removeById(id);
    }

    @Override
    public boolean delByIds(List<Long> ids) {
        return removeByIds(ids);
    }
}
