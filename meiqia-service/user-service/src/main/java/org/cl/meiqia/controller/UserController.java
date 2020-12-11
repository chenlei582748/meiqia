package org.cl.meiqia.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

import org.cl.meiqia.service.UserService;
import org.cl.meiqia.entity.User;
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
@RequestMapping("/user")
public class UserController {
    @Resource
	private UserService userService;

    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable("id") Long id) {
        return Result.ok(userService.getById(id));
    }

    @GetMapping
    public Result<List<User>> list() {
        return Result.ok(userService.list());
    }

    @PostMapping
    public Result add(@RequestBody User user) {
        return userService.saveUser(user) ? Result.ok() : Result.error();
    }

    @PutMapping
    public Result update(@RequestBody User user) {
        return userService.updateUser(user) ? Result.ok() : Result.error();
    }

    @DeleteMapping("/{id}")
    public Result delById(@PathVariable("id") Long id) {
        return userService.delById(id) ? Result.ok() : Result.error();
    }

    @PostMapping("search/{currPage}/{pageSize}")
    public Result<PageResult<User>> page(@PathVariable("currPage") Integer currPage,
                                            @PathVariable("pageSize") Integer pageSize,
                                            @RequestBody(required = false) SearchVO vo) {
        return Result.ok(userService.page(currPage, pageSize, vo));
    }

    @PostMapping("delByIds")
    public Result delByIds(@RequestBody List<Long> ids) {
        return userService.delByIds(ids) ? Result.ok() : Result.error();
    }

}
