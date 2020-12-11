package org.cl.meiqia.service;

import org.cl.meiqia.entity.Meeting;
import org.cl.meiqia.struct.PageResult;
import org.cl.meiqia.vo.SearchVO;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 会诊记录表 服务类
 * </p>
 *
 * @author yiyu
 * @since 2020-12-08
 */
public interface MeetingService extends IService<Meeting> {

    /**
    * 分页列表查询
    * @param currPage
    * @param pageSize
    * @param vo
    * @return
    */
    PageResult<Meeting> page(Integer currPage, Integer pageSize, SearchVO vo);

    /**
    * 添加会诊记录表
    * @param meeting
    * @return
    */
    boolean saveMeeting(Meeting meeting);

    /**
    * 修改会诊记录表
    * @param meeting
    * @return
    */
    boolean updateMeeting(Meeting meeting);

    /**
    * 根据ID删除会诊记录表
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
