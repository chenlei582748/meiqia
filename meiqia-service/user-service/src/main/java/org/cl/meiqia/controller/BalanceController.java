package org.cl.meiqia.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

import org.cl.meiqia.service.BalanceService;
import org.cl.meiqia.entity.Balance;
import org.cl.meiqia.struct.PageResult;
import org.cl.meiqia.struct.Result;
import org.cl.meiqia.vo.SearchVO;

import java.util.List;

/**
 * <p>
 * 用户余额 前端控制器
 * </p>
 *
 * @author yiyu
 * @since 2020-12-08
 */
@RestController
@RequestMapping("/balance")
public class BalanceController {
    @Resource
	private BalanceService balanceService;

    @GetMapping("/{id}")
    public Result<Balance> getById(@PathVariable("id") Long id) {
        return Result.ok(balanceService.getById(id));
    }

    @GetMapping
    public Result<List<Balance>> list() {
        return Result.ok(balanceService.list());
    }

    @PostMapping
    public Result add(@RequestBody Balance balance) {
        return balanceService.saveBalance(balance) ? Result.ok() : Result.error();
    }

    @PutMapping
    public Result update(@RequestBody Balance balance) {
        return balanceService.updateBalance(balance) ? Result.ok() : Result.error();
    }

    @DeleteMapping("/{id}")
    public Result delById(@PathVariable("id") Long id) {
        return balanceService.delById(id) ? Result.ok() : Result.error();
    }

    @PostMapping("search/{currPage}/{pageSize}")
    public Result<PageResult<Balance>> page(@PathVariable("currPage") Integer currPage,
                                            @PathVariable("pageSize") Integer pageSize,
                                            @RequestBody(required = false) SearchVO vo) {
        return Result.ok(balanceService.page(currPage, pageSize, vo));
    }

    @PostMapping("delByIds")
    public Result delByIds(@RequestBody List<Long> ids) {
        return balanceService.delByIds(ids) ? Result.ok() : Result.error();
    }

}
