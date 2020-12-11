package org.cl.meiqia.service;

import org.cl.meiqia.entity.Session;
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
public interface SessionService extends IService<Session> {

    /**
    * 分页列表查询
    * @param currPage
    * @param pageSize
    * @param vo
    * @return
    */
    PageResult<Session> page(Integer currPage, Integer pageSize, SearchVO vo);

    /**
    * 添加
    * @param session
    * @return
    */
    boolean saveSession(Session session);

    /**
    * 修改
    * @param session
    * @return
    */
    boolean updateSession(Session session);

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

}
