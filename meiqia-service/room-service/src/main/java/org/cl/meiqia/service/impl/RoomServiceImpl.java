package org.cl.meiqia.service.impl;

import org.cl.meiqia.asserts.Asserts;
import org.cl.meiqia.entity.Doctor;
import org.cl.meiqia.entity.Room;
import org.cl.meiqia.mapper.RoomMapper;
import org.cl.meiqia.service.DoctorService;
import org.cl.meiqia.service.RoomService;
import org.cl.meiqia.struct.PageResult;
import org.cl.meiqia.vo.SearchVO;
import org.cl.meiqia.util.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {

    @Autowired
    DoctorService doctorService;

    @Override
    public PageResult<Room> page(Integer currPage, Integer pageSize, SearchVO vo) {
        // 分页
        Page<Room> roomPage = new Page<>(currPage, pageSize);

        // 条件查询
        QueryWrapper<Room> wrapper = new QueryWrapper<>();

        if (Objects.nonNull(vo)) {
            String keyWord = vo.getKeyWord();
            if (StringUtils.isNotEmpty(keyWord)) {
                wrapper.like("name", keyWord);
            }
        }
        roomPage = page(roomPage, wrapper);
        return PageResult.of(roomPage.getRecords(), roomPage.getTotal());
    }

    @Override
    public boolean saveRoom(Room room) {
        return save(room);
    }

    @Override
    public boolean updateRoom(Room room) {
        return updateById(room);
    }

    @Override
    public boolean delById(Long id) {
        return removeById(id);
    }

    @Override
    public boolean delByIds(List<Long> ids) {
        return removeByIds(ids);
    }

    public Room getRoomDetails(String id){
        Room room = getById(id);
        Asserts.isNotNull(room,"诊室不存在");
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_id",id);
        List<Doctor> list = doctorService.list(queryWrapper);
        room.setItems(list);
        return room;
    }

}
