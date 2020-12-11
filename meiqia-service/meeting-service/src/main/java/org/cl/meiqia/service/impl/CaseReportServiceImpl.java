package org.cl.meiqia.service.impl;

import org.cl.meiqia.entity.CaseReport;
import org.cl.meiqia.mapper.CaseReportMapper;
import org.cl.meiqia.service.CaseReportService;
import org.cl.meiqia.struct.PageResult;
import org.cl.meiqia.vo.SearchVO;
import org.cl.meiqia.util.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Objects;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yiyu
 * @since 2020-12-08
 */
@Service
public class CaseReportServiceImpl extends ServiceImpl<CaseReportMapper, CaseReport> implements CaseReportService {

    @Override
    public PageResult<CaseReport> page(Integer currPage, Integer pageSize, SearchVO vo) {
        // 分页
        Page<CaseReport> caseReportPage = new Page<>(currPage, pageSize);

        // 条件查询
        QueryWrapper<CaseReport> wrapper = new QueryWrapper<>();

        if (Objects.nonNull(vo)) {
            String keyWord = vo.getKeyWord();
            if (StringUtils.isNotEmpty(keyWord)) {
                wrapper.like("name", keyWord);
            }
        }
        caseReportPage = page(caseReportPage, wrapper);
        return PageResult.of(caseReportPage.getRecords(), caseReportPage.getTotal());
    }

    @Override
    public boolean saveCaseReport(CaseReport caseReport) {
        return save(caseReport);
    }

    @Override
    public boolean updateCaseReport(CaseReport caseReport) {
        return updateById(caseReport);
    }

    @Override
    public boolean delById(Long id) {
        return removeById(id);
    }

    @Override
    public boolean delByIds(List<Long> ids) {
        return removeByIds(ids);
    }
}
