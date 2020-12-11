package org.cl.meiqia.service.impl;

import org.cl.meiqia.entity.OrderItem;
import org.cl.meiqia.mapper.OrderItemMapper;
import org.cl.meiqia.service.OrderItemService;
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
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

    @Override
    public PageResult<OrderItem> page(Integer currPage, Integer pageSize, SearchVO vo) {
        // 分页
        Page<OrderItem> orderItemPage = new Page<>(currPage, pageSize);

        // 条件查询
        QueryWrapper<OrderItem> wrapper = new QueryWrapper<>();

        if (Objects.nonNull(vo)) {
            String keyWord = vo.getKeyWord();
            if (StringUtils.isNotEmpty(keyWord)) {
                wrapper.like("name", keyWord);
            }
        }
        orderItemPage = page(orderItemPage, wrapper);
        return PageResult.of(orderItemPage.getRecords(), orderItemPage.getTotal());
    }

    @Override
    public boolean saveOrderItem(OrderItem orderItem) {
        return save(orderItem);
    }

    @Override
    public boolean updateOrderItem(OrderItem orderItem) {
        return updateById(orderItem);
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
