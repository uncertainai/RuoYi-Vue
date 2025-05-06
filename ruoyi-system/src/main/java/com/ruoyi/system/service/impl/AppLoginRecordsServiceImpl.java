package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.SummaryRequest;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AppLoginRecordsMapper;
import com.ruoyi.system.domain.AppLoginRecords;
import com.ruoyi.system.service.IAppLoginRecordsService;
import com.ruoyi.common.core.service.BaseService;

/**
 * 用户登录记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@Service
public class AppLoginRecordsServiceImpl extends BaseService<AppLoginRecords> implements IAppLoginRecordsService
{
    @Autowired
    private AppLoginRecordsMapper appLoginRecordsMapper;

    @Override
    public AppLoginRecordsMapper getMapper() {
        return appLoginRecordsMapper;
    }

    /**
     * 查询用户登录记录
     * 
     * @param id 用户登录记录主键
     * @return 用户登录记录
     */
    @Override
    public AppLoginRecords getById(Long id)
    {
        return appLoginRecordsMapper.selectById(id);
    }

    /**
     * 查询用户登录记录列表
     * 
     * @param query 用户登录记录
     * @return 用户登录记录
     */
    @Override
    public List<AppLoginRecords> selectList(AppLoginRecords query)
    {
        return appLoginRecordsMapper.selectList(new QueryWrapper<>(query));
    }


    /**
     * 查询用户登录记录数据汇总
     *
     * @param query 用户登录记录
     * @return 用户登录记录
     */
    @Override
    public AppLoginRecords getSummary(AppLoginRecords query)
    {
        return appLoginRecordsMapper.getSummary(query);
    }



    /**
     * 查询自定义分析数据
     *
     * @param query 用户登录记录
     * @return 用户登录记录
     */
    @Override
    public List<AppLoginRecords> summary(SummaryRequest query)
    {
        return appLoginRecordsMapper.summary(query);
    }

    @Override
    public AppLoginRecords allSummary(SummaryRequest query)
    {
        return appLoginRecordsMapper.allSummary(query);
    }

    /**
     * 新增用户登录记录
     * 
     * @param appLoginRecords 用户登录记录
     * @return 结果
     */
    @Override
    public int insert(AppLoginRecords appLoginRecords)
    {
        appLoginRecords.setCreateTime(DateUtils.getNowDate());
        return appLoginRecordsMapper.insert(appLoginRecords);
    }

    /**
     * 修改用户登录记录
     * 
     * @param appLoginRecords 用户登录记录
     * @return 结果
     */
    @Override
    public int update(AppLoginRecords appLoginRecords)
    {
        appLoginRecords.setUpdateTime(DateUtils.getNowDate());
        return appLoginRecordsMapper.updateById(appLoginRecords);
    }

    /**
     * 批量删除用户登录记录
     * 
     * @param ids 需要删除的用户登录记录主键
     * @return 结果
     */
    @Override
    public int deleteByIds(List<Long> ids)
    {
        return appLoginRecordsMapper.deleteBatchIds(ids);
    }

    /**
     * 删除用户登录记录信息
     * 
     * @param id 用户登录记录主键
     * @return 结果
     */
    @Override
    public int deleteById(Long id)
    {
        return appLoginRecordsMapper.deleteById(id);
    }
}
