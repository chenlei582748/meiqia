package org.cl.meiqia.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

import org.cl.meiqia.service.CaseReportService;
import org.cl.meiqia.entity.CaseReport;
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
@RequestMapping("/case-report")
public class CaseReportController {
    @Resource
	private CaseReportService caseReportService;

    @GetMapping("/{id}")
    public Result<CaseReport> getById(@PathVariable("id") Long id) {
        return Result.ok(caseReportService.getById(id));
    }

    @GetMapping
    public Result<List<CaseReport>> list() {
        return Result.ok(caseReportService.list());
    }

    @PostMapping
    public Result add(@RequestBody CaseReport caseReport) {
        return caseReportService.saveCaseReport(caseReport) ? Result.ok() : Result.error();
    }

    @PutMapping
    public Result update(@RequestBody CaseReport caseReport) {
        return caseReportService.updateCaseReport(caseReport) ? Result.ok() : Result.error();
    }

    @DeleteMapping("/{id}")
    public Result delById(@PathVariable("id") Long id) {
        return caseReportService.delById(id) ? Result.ok() : Result.error();
    }

    @PostMapping("search/{currPage}/{pageSize}")
    public Result<PageResult<CaseReport>> page(@PathVariable("currPage") Integer currPage,
                                            @PathVariable("pageSize") Integer pageSize,
                                            @RequestBody(required = false) SearchVO vo) {
        return Result.ok(caseReportService.page(currPage, pageSize, vo));
    }

    @PostMapping("delByIds")
    public Result delByIds(@RequestBody List<Long> ids) {
        return caseReportService.delByIds(ids) ? Result.ok() : Result.error();
    }

}
