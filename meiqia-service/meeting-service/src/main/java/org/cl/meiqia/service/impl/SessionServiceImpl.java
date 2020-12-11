package org.cl.meiqia.service.impl;

import org.cl.meiqia.entity.Session;
import org.cl.meiqia.mapper.SessionMapper;
import org.cl.meiqia.service.SessionService;
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
 *  服务实现类
 * </p>
 *
 * @author yiyu
 * @since 2020-12-08
 */
@Service
public class SessionServiceImpl extends ServiceImpl<SessionMapper, Session> implements SessionService {

    @Override
    public PageResult<Session> page(Integer currPage, Integer pageSize, SearchVO vo) {
        // 分页
        Page<Session> sessionPage = new Page<>(currPage, pageSize);

        // 条件查询
        QueryWrapper<Session> wrapper = new QueryWrapper<>();

        if (Objects.nonNull(vo)) {
            String keyWord = vo.getKeyWord();
            if (StringUtils.isNotEmpty(keyWord)) {
                wrapper.like("name", keyWord);
            }
        }
        sessionPage = page(sessionPage, wrapper);
        return PageResult.of(sessionPage.getRecords(), sessionPage.getTotal());
    }

    @Override
    public boolean saveSession(Session session) {
        return save(session);
    }

    @Override
    public boolean updateSession(Session session) {
        return updateById(session);
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
