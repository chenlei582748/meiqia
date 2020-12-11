package org.cl.meiqia.service.impl;

import org.cl.meiqia.entity.Doctor;
import org.cl.meiqia.mapper.DoctorMapper;
import org.cl.meiqia.service.DoctorService;
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
 * @since 2020-12-09
 */
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements DoctorService {

    @Override
    public PageResult<Doctor> page(Integer currPage, Integer pageSize, SearchVO vo) {
        // 分页
        Page<Doctor> doctorPage = new Page<>(currPage, pageSize);

        // 条件查询
        QueryWrapper<Doctor> wrapper = new QueryWrapper<>();

        if (Objects.nonNull(vo)) {
            String keyWord = vo.getKeyWord();
            if (StringUtils.isNotEmpty(keyWord)) {
                wrapper.like("name", keyWord);
            }
        }
        doctorPage = page(doctorPage, wrapper);
        return PageResult.of(doctorPage.getRecords(), doctorPage.getTotal());
    }

    @Override
    public boolean saveDoctor(Doctor doctor) {
        return save(doctor);
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        return updateById(doctor);
    }

    @Override
    public boolean delById(Long id) {
        return removeById(id);
    }

    @Override
    public boolean delByIds(List<Long> ids) {
        return removeByIds(ids);
    }

    @Override
    public PageResult<Doctor> getPage(Integer cp, Integer pz, SearchVO vo) {
        Page<Doctor> doctorPage = new Page<>(cp,pz);
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(vo)) {
            String keyWord = vo.getKeyWord();
            if (StringUtils.isNotEmpty(keyWord)) {
                queryWrapper.like("name", keyWord);
            }
        }
        doctorPage = baseMapper.getPage(doctorPage,queryWrapper);
        return PageResult.of(doctorPage.getRecords(),doctorPage.getTotal());
    }

}
