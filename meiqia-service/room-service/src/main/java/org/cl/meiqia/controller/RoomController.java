package org.cl.meiqia.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

import org.cl.meiqia.service.RoomService;
import org.cl.meiqia.entity.Room;
import org.cl.meiqia.struct.PageResult;
import org.cl.meiqia.struct.Result;
import org.cl.meiqia.vo.SearchVO;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yiyu
 * @since 2020-12-09
 */
@RestController
@RequestMapping("/room")
public class RoomController {
    @Resource
	private RoomService roomService;

    @GetMapping("/{id}")
    public Result<Room> getById(@PathVariable("id") Long id) {
        return Result.ok(roomService.getById(id));
    }

    @PostMapping("/{id}")
    public Room getRoomDetails(@PathVariable("id")String id){
        return roomService.getRoomDetails(id);
    }

    @GetMapping
    public Result<List<Room>> list() {
        return Result.ok(roomService.list());
    }

    @PostMapping
    public Result add(@RequestBody Room room) {
        return roomService.saveRoom(room) ? Result.ok() : Result.error();
    }

    @PutMapping
    public Result update(@RequestBody Room room) {
        return roomService.updateRoom(room) ? Result.ok() : Result.error();
    }

    @DeleteMapping("/{id}")
    public Result delById(@PathVariable("id") Long id) {
        return roomService.delById(id) ? Result.ok() : Result.error();
    }

    @PostMapping("search/{currPage}/{pageSize}")
    public Result<PageResult<Room>> page(@PathVariable("currPage") Integer currPage,
                                            @PathVariable("pageSize") Integer pageSize,
                                            @RequestBody(required = false) SearchVO vo) {
        return Result.ok(roomService.page(currPage, pageSize, vo));
    }

    @PostMapping("delByIds")
    public Result delByIds(@RequestBody List<Long> ids) {
        return roomService.delByIds(ids) ? Result.ok() : Result.error();
    }

}
