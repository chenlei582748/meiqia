package org.cl.meiqia.service.impl;

import org.cl.meiqia.entity.Remind;
import org.cl.meiqia.mapper.RemindMapper;
import org.cl.meiqia.service.RemindService;
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
 * 用药提醒表 服务实现类
 * </p>
 *
 * @author yiyu
 * @since 2020-12-08
 */
@Service
public class RemindServiceImpl extends ServiceImpl<RemindMapper, Remind> implements RemindService {

    @Override
    public PageResult<Remind> page(Integer currPage, Integer pageSize, SearchVO vo) {
        // 分页
        Page<Remind> remindPage = new Page<>(currPage, pageSize);

        // 条件查询
        QueryWrapper<Remind> wrapper = new QueryWrapper<>();

        if (Objects.nonNull(vo)) {
            String keyWord = vo.getKeyWord();
            if (StringUtils.isNotEmpty(keyWord)) {
                wrapper.like("name", keyWord);
            }
        }
        remindPage = page(remindPage, wrapper);
        return PageResult.of(remindPage.getRecords(), remindPage.getTotal());
    }

    @Override
    public boolean saveRemind(Remind remind) {
        return save(remind);
    }

    @Override
    public boolean updateRemind(Remind remind) {
        return updateById(remind);
    }

    @Override
    public boolean delById(Long id) {
        return removeById(id);
    }

    @Override
    public boolean delByIds(List<Long> ids) {
        return removeByIds(ids);
    }
}
