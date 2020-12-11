package org.cl.meiqia.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cl.meiqia.entity.Doctor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yiyu
 * @since 2020-12-08
 */
@Mapper
public interface DoctorMapper extends BaseMapper<Doctor> {

    Page<Doctor> getPage(Page<Doctor> doctorPage,@Param("ew") QueryWrapper<Doctor> queryWrapper);


}
