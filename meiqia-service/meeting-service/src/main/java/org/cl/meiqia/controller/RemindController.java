package org.cl.meiqia.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

import org.cl.meiqia.service.RemindService;
import org.cl.meiqia.entity.Remind;
import org.cl.meiqia.struct.PageResult;
import org.cl.meiqia.struct.Result;
import org.cl.meiqia.vo.SearchVO;

import java.util.List;

/**
 * <p>
 * 用药提醒表 前端控制器
 * </p>
 *
 * @author yiyu
 * @since 2020-12-08
 */
@RestController
@RequestMapping("/remind")
public class RemindController {
    @Resource
	private RemindService remindService;

    @GetMapping("/{id}")
    public Result<Remind> getById(@PathVariable("id") Long id) {
        return Result.ok(remindService.getById(id));
    }

    @GetMapping
    public Result<List<Remind>> list() {
        return Result.ok(remindService.list());
    }

    @PostMapping
    public Result add(@RequestBody Remind remind) {
        return remindService.saveRemind(remind) ? Result.ok() : Result.error();
    }

    @PutMapping
    public Result update(@RequestBody Remind remind) {
        return remindService.updateRemind(remind) ? Result.ok() : Result.error();
    }

    @DeleteMapping("/{id}")
    public Result delById(@PathVariable("id") Long id) {
        return remindService.delById(id) ? Result.ok() : Result.error();
    }

    @PostMapping("search/{currPage}/{pageSize}")
    public Result<PageResult<Remind>> page(@PathVariable("currPage") Integer currPage,
                                            @PathVariable("pageSize") Integer pageSize,
                                            @RequestBody(required = false) SearchVO vo) {
        return Result.ok(remindService.page(currPage, pageSize, vo));
    }

    @PostMapping("delByIds")
    public Result delByIds(@RequestBody List<Long> ids) {
        return remindService.delByIds(ids) ? Result.ok() : Result.error();
    }

}
