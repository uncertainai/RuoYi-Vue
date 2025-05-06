package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.SummaryRequest;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AppDramaMapper;
import com.ruoyi.system.domain.AppDrama;
import com.ruoyi.system.service.IAppDramaService;
import com.ruoyi.common.core.service.BaseService;

/**
 * 剧目Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@Service
public class AppDramaServiceImpl extends BaseService<AppDrama> implements IAppDramaService
{
    @Autowired
    private AppDramaMapper appDramaMapper;

    @Override
    public AppDramaMapper getMapper() {
        return appDramaMapper;
    }

    /**
     * 查询剧目
     * 
     * @param id 剧目主键
     * @return 剧目
     */
    @Override
    public AppDrama getById(Long id)
    {
        return appDramaMapper.selectById(id);
    }

    /**
     * 查询剧目列表
     * 
     * @param query 剧目
     * @return 剧目
     */
    @Override
    public List<AppDrama> selectList(AppDrama query)
    {
        return appDramaMapper.selectList(new QueryWrapper<>(query));
    }


    /**
     * 查询剧目数据汇总
     *
     * @param query 剧目
     * @return 剧目
     */
    @Override
    public AppDrama getSummary(AppDrama query)
    {
        return appDramaMapper.getSummary(query);
    }



    /**
     * 查询自定义分析数据
     *
     * @param query 剧目
     * @return 剧目
     */
    @Override
    public List<AppDrama> summary(SummaryRequest query)
    {
        return appDramaMapper.summary(query);
    }

    @Override
    public AppDrama allSummary(SummaryRequest query)
    {
        return appDramaMapper.allSummary(query);
    }

    /**
     * 新增剧目
     * 
     * @param appDrama 剧目
     * @return 结果
     */
    @Override
    public int insert(AppDrama appDrama)
    {
        appDrama.setCreateTime(DateUtils.getNowDate());
        return appDramaMapper.insert(appDrama);
    }

    /**
     * 修改剧目
     * 
     * @param appDrama 剧目
     * @return 结果
     */
    @Override
    public int update(AppDrama appDrama)
    {
        appDrama.setUpdateTime(DateUtils.getNowDate());
        return appDramaMapper.updateById(appDrama);
    }

    /**
     * 批量删除剧目
     * 
     * @param ids 需要删除的剧目主键
     * @return 结果
     */
    @Override
    public int deleteByIds(List<Long> ids)
    {
        return appDramaMapper.deleteBatchIds(ids);
    }

    /**
     * 删除剧目信息
     * 
     * @param id 剧目主键
     * @return 结果
     */
    @Override
    public int deleteById(Long id)
    {
        return appDramaMapper.deleteById(id);
    }
}
