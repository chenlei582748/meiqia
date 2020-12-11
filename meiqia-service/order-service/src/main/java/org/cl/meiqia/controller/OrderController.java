package org.cl.meiqia.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

import org.cl.meiqia.service.OrderService;
import org.cl.meiqia.entity.Order;
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
@RequestMapping("/order")
public class OrderController {
    @Resource
	private OrderService orderService;

    @GetMapping("/{id}")
    public Result<Order> getById(@PathVariable("id") Long id) {
        return Result.ok(orderService.getById(id));
    }

    @GetMapping
    public Result<List<Order>> list() {
        return Result.ok(orderService.list());
    }

    @PostMapping
    public Result add(@RequestBody Order order) {
        return orderService.saveOrder(order) ? Result.ok() : Result.error();
    }

    @PutMapping
    public Result update(@RequestBody Order order) {
        return orderService.updateOrder(order) ? Result.ok() : Result.error();
    }

    @DeleteMapping("/{id}")
    public Result delById(@PathVariable("id") Long id) {
        return orderService.delById(id) ? Result.ok() : Result.error();
    }

    @PostMapping("search/{currPage}/{pageSize}")
    public Result<PageResult<Order>> page(@PathVariable("currPage") Integer currPage,
                                            @PathVariable("pageSize") Integer pageSize,
                                            @RequestBody(required = false) SearchVO vo) {
        return Result.ok(orderService.page(currPage, pageSize, vo));
    }

    @PostMapping("delByIds")
    public Result delByIds(@RequestBody List<Long> ids) {
        return orderService.delByIds(ids) ? Result.ok() : Result.error();
    }

}
