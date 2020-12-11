package org.cl.meiqia.service;

import org.cl.meiqia.entity.User;
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
public interface UserService extends IService<User> {

    /**
    * 分页列表查询
    * @param currPage
    * @param pageSize
    * @param vo
    * @return
    */
    PageResult<User> page(Integer currPage, Integer pageSize, SearchVO vo);

    /**
    * 添加
    * @param user
    * @return
    */
    boolean saveUser(User user);

    /**
    * 修改
    * @param user
    * @return
    */
    boolean updateUser(User user);

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

    boolean register(String name, String pwd, Integer role);

    User getDetails(Long id);
}
