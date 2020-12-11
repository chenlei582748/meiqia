package org.cl.meiqia.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

import org.cl.meiqia.service.DoctorService;
import org.cl.meiqia.entity.Doctor;
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
@RequestMapping("/doctor")
public class DoctorController {
    @Resource
	private DoctorService doctorService;

    @GetMapping("/{id}")
    public Result<Doctor> getById(@PathVariable("id") Long id) {
        return Result.ok(doctorService.getById(id));
    }

    @GetMapping
    public Result<List<Doctor>> list() {
        return Result.ok(doctorService.list());
    }

    @PostMapping
    public Result add(@RequestBody Doctor doctor) {
        return doctorService.saveDoctor(doctor) ? Result.ok() : Result.error();
    }

    @PutMapping
    public Result update(@RequestBody Doctor doctor) {
        return doctorService.updateDoctor(doctor) ? Result.ok() : Result.error();
    }

    @DeleteMapping("/{id}")
    public Result delById(@PathVariable("id") Long id) {
        return doctorService.delById(id) ? Result.ok() : Result.error();
    }

    @PostMapping("search/{currPage}/{pageSize}")
    public Result<PageResult<Doctor>> page(@PathVariable("currPage") Integer currPage,
                                            @PathVariable("pageSize") Integer pageSize,
                                            @RequestBody(required = false) SearchVO vo) {
        return Result.ok(doctorService.page(currPage, pageSize, vo));
    }

    @PostMapping("list/{cp}/{pz}")
    public PageResult<Doctor> getPage(@PathVariable("cp")Integer cp, @PathVariable("pz")Integer pz,
                                      @RequestBody SearchVO vo){
        return doctorService.getPage(cp,pz,vo);
    }

    @PostMapping("delByIds")
    public Result delByIds(@RequestBody List<Long> ids) {
        return doctorService.delByIds(ids) ? Result.ok() : Result.error();
    }

}
