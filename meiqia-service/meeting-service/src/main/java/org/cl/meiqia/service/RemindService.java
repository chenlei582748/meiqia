package org.cl.meiqia.service;

import org.cl.meiqia.entity.Remind;
import org.cl.meiqia.struct.PageResult;
import org.cl.meiqia.vo.SearchVO;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 用药提醒表 服务类
 * </p>
 *
 * @author yiyu
 * @since 2020-12-08
 */
public interface RemindService extends IService<Remind> {

    /**
    * 分页列表查询
    * @param currPage
    * @param pageSize
    * @param vo
    * @return
    */
    PageResult<Remind> page(Integer currPage, Integer pageSize, SearchVO vo);

    /**
    * 添加用药提醒表
    * @param remind
    * @return
    */
    boolean saveRemind(Remind remind);

    /**
    * 修改用药提醒表
    * @param remind
    * @return
    */
    boolean updateRemind(Remind remind);

    /**
    * 根据ID删除用药提醒表
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

}
