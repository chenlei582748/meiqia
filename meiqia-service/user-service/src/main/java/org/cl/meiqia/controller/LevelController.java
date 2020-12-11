package org.cl.meiqia.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

import org.cl.meiqia.service.LevelService;
import org.cl.meiqia.entity.Level;
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
@RequestMapping("/level")
public class LevelController {
    @Resource
	private LevelService levelService;

    @GetMapping("/{id}")
    public Result<Level> getById(@PathVariable("id") Long id) {
        return Result.ok(levelService.getById(id));
    }

    @GetMapping
    public Result<List<Level>> list() {
        return Result.ok(levelService.list());
    }

    @PostMapping
    public Result add(@RequestBody Level level) {
        return levelService.saveLevel(level) ? Result.ok() : Result.error();
    }

    @PutMapping
    public Result update(@RequestBody Level level) {
        return levelService.updateLevel(level) ? Result.ok() : Result.error();
    }

    @DeleteMapping("/{id}")
    public Result delById(@PathVariable("id") Long id) {
        return levelService.delById(id) ? Result.ok() : Result.error();
    }

    @PostMapping("search/{currPage}/{pageSize}")
    public Result<PageResult<Level>> page(@PathVariable("currPage") Integer currPage,
                                            @PathVariable("pageSize") Integer pageSize,
                                            @RequestBody(required = false) SearchVO vo) {
        return Result.ok(levelService.page(currPage, pageSize, vo));
    }

    @PostMapping("delByIds")
    public Result delByIds(@RequestBody List<Long> ids) {
        return levelService.delByIds(ids) ? Result.ok() : Result.error();
    }

}
