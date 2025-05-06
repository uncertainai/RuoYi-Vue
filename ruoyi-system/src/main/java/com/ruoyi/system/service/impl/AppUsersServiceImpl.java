package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.SummaryRequest;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AppUsersMapper;
import com.ruoyi.system.domain.AppUsers;
import com.ruoyi.system.service.IAppUsersService;
import com.ruoyi.common.core.service.BaseService;

/**
 * 社交媒体用户Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@Service
public class AppUsersServiceImpl extends BaseService<AppUsers> implements IAppUsersService
{
    @Autowired
    private AppUsersMapper appUsersMapper;

    @Override
    public AppUsersMapper getMapper() {
        return appUsersMapper;
    }

    /**
     * 查询社交媒体用户
     * 
     * @param id 社交媒体用户主键
     * @return 社交媒体用户
     */
    @Override
    public AppUsers getById(Long id)
    {
        return appUsersMapper.selectById(id);
    }

    /**
     * 查询社交媒体用户列表
     * 
     * @param query 社交媒体用户
     * @return 社交媒体用户
     */
    @Override
    public List<AppUsers> selectList(AppUsers query)
    {
        return appUsersMapper.selectList(new QueryWrapper<>(query));
    }


    /**
     * 查询社交媒体用户数据汇总
     *
     * @param query 社交媒体用户
     * @return 社交媒体用户
     */
    @Override
    public AppUsers getSummary(AppUsers query)
    {
        return appUsersMapper.getSummary(query);
    }



    /**
     * 查询自定义分析数据
     *
     * @param query 社交媒体用户
     * @return 社交媒体用户
     */
    @Override
    public List<AppUsers> summary(SummaryRequest query)
    {
        return appUsersMapper.summary(query);
    }

    @Override
    public AppUsers allSummary(SummaryRequest query)
    {
        return appUsersMapper.allSummary(query);
    }

    /**
     * 新增社交媒体用户
     * 
     * @param appUsers 社交媒体用户
     * @return 结果
     */
    @Override
    public int insert(AppUsers appUsers)
    {
        appUsers.setCreateTime(DateUtils.getNowDate());
        return appUsersMapper.insert(appUsers);
    }

    /**
     * 修改社交媒体用户
     * 
     * @param appUsers 社交媒体用户
     * @return 结果
     */
    @Override
    public int update(AppUsers appUsers)
    {
        appUsers.setUpdateTime(DateUtils.getNowDate());
        return appUsersMapper.updateById(appUsers);
    }

    /**
     * 批量删除社交媒体用户
     * 
     * @param ids 需要删除的社交媒体用户主键
     * @return 结果
     */
    @Override
    public int deleteByIds(List<Long> ids)
    {
        return appUsersMapper.deleteBatchIds(ids);
    }

    /**
     * 删除社交媒体用户信息
     * 
     * @param id 社交媒体用户主键
     * @return 结果
     */
    @Override
    public int deleteById(Long id)
    {
        return appUsersMapper.deleteById(id);
    }
}
