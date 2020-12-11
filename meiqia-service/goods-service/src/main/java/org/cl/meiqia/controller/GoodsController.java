package org.cl.meiqia.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

import org.cl.meiqia.service.GoodsService;
import org.cl.meiqia.entity.Goods;
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
@RequestMapping("/goods")
public class GoodsController {
    @Resource
	private GoodsService goodsService;

    @GetMapping("/{id}")
    public Result<Goods> getById(@PathVariable("id") Long id) {
        return Result.ok(goodsService.getById(id));
    }

    @GetMapping
    public Result<List<Goods>> list() {
        return Result.ok(goodsService.list());
    }

    @PostMapping
    public Result add(@RequestBody Goods goods) {
        return goodsService.saveGoods(goods) ? Result.ok() : Result.error();
    }

    @PutMapping
    public Result update(@RequestBody Goods goods) {
        return goodsService.updateGoods(goods) ? Result.ok() : Result.error();
    }

    @DeleteMapping("/{id}")
    public Result delById(@PathVariable("id") Long id) {
        return goodsService.delById(id) ? Result.ok() : Result.error();
    }

    @PostMapping("search/{currPage}/{pageSize}")
    public Result<PageResult<Goods>> page(@PathVariable("currPage") Integer currPage,
                                            @PathVariable("pageSize") Integer pageSize,
                                            @RequestBody(required = false) SearchVO vo) {
        return Result.ok(goodsService.page(currPage, pageSize, vo));
    }

    @PostMapping("delByIds")
    public Result delByIds(@RequestBody List<Long> ids) {
        return goodsService.delByIds(ids) ? Result.ok() : Result.error();
    }

}
