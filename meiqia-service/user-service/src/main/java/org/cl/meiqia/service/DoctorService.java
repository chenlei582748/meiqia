package org.cl.meiqia.service;

import org.cl.meiqia.entity.Doctor;
import org.cl.meiqia.struct.PageResult;
import org.cl.meiqia.vo.SearchVO;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yiyu
 * @since 2020-12-08
 */
public interface DoctorService extends IService<Doctor> {

    /**
    * 分页列表查询
    * @param currPage
    * @param pageSize
    * @param vo
    * @return
    */
    PageResult<Doctor> page(Integer currPage, Integer pageSize, SearchVO vo);

    /**
    * 添加
    * @param doctor
    * @return
    */
    boolean saveDoctor(Doctor doctor);

    /**
    * 修改
    * @param doctor
    * @return
    */
    boolean updateDoctor(Doctor doctor);

    /**
    * 根据ID删除
    * @param id
    * @return
    */
    boolean delById(Long id);

    /**
    * 根据主键IDs批量删除
    * @param ids
    * @return
    */
    boolean delByIds(List<Long> ids);

    PageResult<Doctor> getPage(Integer cp, Integer pz, SearchVO vo);
}
