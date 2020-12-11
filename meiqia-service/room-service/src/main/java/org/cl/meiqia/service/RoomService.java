package org.cl.meiqia.service;

import org.cl.meiqia.entity.Room;
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
 * @since 2020-12-09
 */
public interface RoomService extends IService<Room> {

    /**
    * 分页列表查询
    * @param currPage
    * @param pageSize
    * @param vo
    * @return
    */
    PageResult<Room> page(Integer currPage, Integer pageSize, SearchVO vo);

    /**
    * 添加
    * @param room
    * @return
    */
    boolean saveRoom(Room room);

    /**
    * 修改
    * @param room
    * @return
    */
    boolean updateRoom(Room room);

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

    Room getRoomDetails(String id);
}
