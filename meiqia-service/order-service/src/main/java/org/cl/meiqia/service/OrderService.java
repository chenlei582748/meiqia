package org.cl.meiqia.service;

import org.cl.meiqia.entity.Order;
import org.cl.meiqia.struct.PageResult;
import org.cl.meiqia.vo.SearchVO;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yiyu
 * @since 2020-12-08
 */
public interface OrderService extends IService<Order> {

    /**
    * 分页列表查询
    * @param currPage
    * @param pageSize
    * @param vo
    * @return
    */
    PageResult<Order> page(Integer currPage, Integer pageSize, SearchVO vo);

    /**
    * 添加
    * @param order
    * @return
    */
    boolean saveOrder(Order order);

    /**
    * 修改
    * @param order
    * @return
    */
    boolean updateOrder(Order order);

    /**
    * 根据ID删除
    * @param id
    * @return
    */
    boolean delById(Long id);

    /**
    * 根据主键IDs批量删除
    * @param ids
    * @return
    */
    boolean delByIds(List<Long> ids);

}
