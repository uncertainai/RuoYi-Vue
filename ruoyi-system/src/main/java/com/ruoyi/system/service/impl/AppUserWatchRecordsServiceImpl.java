package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.SummaryRequest;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AppUserWatchRecordsMapper;
import com.ruoyi.system.domain.AppUserWatchRecords;
import com.ruoyi.system.service.IAppUserWatchRecordsService;
import com.ruoyi.common.core.service.BaseService;

/**
 * 用户观看记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@Service
public class AppUserWatchRecordsServiceImpl extends BaseService<AppUserWatchRecords> implements IAppUserWatchRecordsService
{
    @Autowired
    private AppUserWatchRecordsMapper appUserWatchRecordsMapper;

    @Override
    public AppUserWatchRecordsMapper getMapper() {
        return appUserWatchRecordsMapper;
    }

    /**
     * 查询用户观看记录
     * 
     * @param id 用户观看记录主键
     * @return 用户观看记录
     */
    @Override
    public AppUserWatchRecords getById(Long id)
    {
        return appUserWatchRecordsMapper.selectById(id);
    }

    /**
     * 查询用户观看记录列表
     * 
     * @param query 用户观看记录
     * @return 用户观看记录
     */
    @Override
    public List<AppUserWatchRecords> selectList(AppUserWatchRecords query)
    {
        return appUserWatchRecordsMapper.selectList(new QueryWrapper<>(query));
    }


    /**
     * 查询用户观看记录数据汇总
     *
     * @param query 用户观看记录
     * @return 用户观看记录
     */
    @Override
    public AppUserWatchRecords getSummary(AppUserWatchRecords query)
    {
        return appUserWatchRecordsMapper.getSummary(query);
    }



    /**
     * 查询自定义分析数据
     *
     * @param query 用户观看记录
     * @return 用户观看记录
     */
    @Override
    public List<AppUserWatchRecords> summary(SummaryRequest query)
    {
        return appUserWatchRecordsMapper.summary(query);
    }

    @Override
    public AppUserWatchRecords allSummary(SummaryRequest query)
    {
        return appUserWatchRecordsMapper.allSummary(query);
    }

    /**
     * 新增用户观看记录
     * 
     * @param appUserWatchRecords 用户观看记录
     * @return 结果
     */
    @Override
    public int insert(AppUserWatchRecords appUserWatchRecords)
    {
        appUserWatchRecords.setCreateTime(DateUtils.getNowDate());
        return appUserWatchRecordsMapper.insert(appUserWatchRecords);
    }

    /**
     * 修改用户观看记录
     * 
     * @param appUserWatchRecords 用户观看记录
     * @return 结果
     */
    @Override
    public int update(AppUserWatchRecords appUserWatchRecords)
    {
        appUserWatchRecords.setUpdateTime(DateUtils.getNowDate());
        return appUserWatchRecordsMapper.updateById(appUserWatchRecords);
    }

    /**
     * 批量删除用户观看记录
     * 
     * @param ids 需要删除的用户观看记录主键
     * @return 结果
     */
    @Override
    public int deleteByIds(List<Long> ids)
    {
        return appUserWatchRecordsMapper.deleteBatchIds(ids);
    }

    /**
     * 删除用户观看记录信息
     * 
     * @param id 用户观看记录主键
     * @return 结果
     */
    @Override
    public int deleteById(Long id)
    {
        return appUserWatchRecordsMapper.deleteById(id);
    }
}
