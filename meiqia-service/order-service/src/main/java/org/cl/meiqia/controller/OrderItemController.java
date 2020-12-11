package org.cl.meiqia.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

import org.cl.meiqia.service.OrderItemService;
import org.cl.meiqia.entity.OrderItem;
import org.cl.meiqia.struct.PageResult;
import org.cl.meiqia.struct.Result;
import org.cl.meiqia.vo.SearchVO;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yiyu
 * @since 2020-12-08
 */
@RestController
@RequestMapping("/order-item")
public class OrderItemController {
    @Resource
	private OrderItemService orderItemService;

    @GetMapping("/{id}")
    public Result<OrderItem> getById(@PathVariable("id") Long id) {
        return Result.ok(orderItemService.getById(id));
    }

    @GetMapping
    public Result<List<OrderItem>> list() {
        return Result.ok(orderItemService.list());
    }

    @PostMapping
    public Result add(@RequestBody OrderItem orderItem) {
        return orderItemService.saveOrderItem(orderItem) ? Result.ok() : Result.error();
    }

    @PutMapping
    public Result update(@RequestBody OrderItem orderItem) {
        return orderItemService.updateOrderItem(orderItem) ? Result.ok() : Result.error();
    }

    @DeleteMapping("/{id}")
    public Result delById(@PathVariable("id") Long id) {
        return orderItemService.delById(id) ? Result.ok() : Result.error();
    }

    @PostMapping("search/{currPage}/{pageSize}")
    public Result<PageResult<OrderItem>> page(@PathVariable("currPage") Integer currPage,
                                            @PathVariable("pageSize") Integer pageSize,
                                            @RequestBody(required = false) SearchVO vo) {
        return Result.ok(orderItemService.page(currPage, pageSize, vo));
    }

    @PostMapping("delByIds")
    public Result delByIds(@RequestBody List<Long> ids) {
        return orderItemService.delByIds(ids) ? Result.ok() : Result.error();
    }

}
