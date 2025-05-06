package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.SummaryRequest;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AppDramaContentsMapper;
import com.ruoyi.system.domain.AppDramaContents;
import com.ruoyi.system.service.IAppDramaContentsService;
import com.ruoyi.common.core.service.BaseService;

/**
 * 剧目内容Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@Service
public class AppDramaContentsServiceImpl extends BaseService<AppDramaContents> implements IAppDramaContentsService
{
    @Autowired
    private AppDramaContentsMapper appDramaContentsMapper;

    @Override
    public AppDramaContentsMapper getMapper() {
        return appDramaContentsMapper;
    }

    /**
     * 查询剧目内容
     * 
     * @param id 剧目内容主键
     * @return 剧目内容
     */
    @Override
    public AppDramaContents getById(Long id)
    {
        return appDramaContentsMapper.selectById(id);
    }

    /**
     * 查询剧目内容列表
     * 
     * @param query 剧目内容
     * @return 剧目内容
     */
    @Override
    public List<AppDramaContents> selectList(AppDramaContents query)
    {
        return appDramaContentsMapper.selectList(new QueryWrapper<>(query));
    }


    /**
     * 查询剧目内容数据汇总
     *
     * @param query 剧目内容
     * @return 剧目内容
     */
    @Override
    public AppDramaContents getSummary(AppDramaContents query)
    {
        return appDramaContentsMapper.getSummary(query);
    }



    /**
     * 查询自定义分析数据
     *
     * @param query 剧目内容
     * @return 剧目内容
     */
    @Override
    public List<AppDramaContents> summary(SummaryRequest query)
    {
        return appDramaContentsMapper.summary(query);
    }

    @Override
    public AppDramaContents allSummary(SummaryRequest query)
    {
        return appDramaContentsMapper.allSummary(query);
    }

    /**
     * 新增剧目内容
     * 
     * @param appDramaContents 剧目内容
     * @return 结果
     */
    @Override
    public int insert(AppDramaContents appDramaContents)
    {
        appDramaContents.setCreateTime(DateUtils.getNowDate());
        return appDramaContentsMapper.insert(appDramaContents);
    }

    /**
     * 修改剧目内容
     * 
     * @param appDramaContents 剧目内容
     * @return 结果
     */
    @Override
    public int update(AppDramaContents appDramaContents)
    {
        appDramaContents.setUpdateTime(DateUtils.getNowDate());
        return appDramaContentsMapper.updateById(appDramaContents);
    }

    /**
     * 批量删除剧目内容
     * 
     * @param ids 需要删除的剧目内容主键
     * @return 结果
     */
    @Override
    public int deleteByIds(List<Long> ids)
    {
        return appDramaContentsMapper.deleteBatchIds(ids);
    }

    /**
     * 删除剧目内容信息
     * 
     * @param id 剧目内容主键
     * @return 结果
     */
    @Override
    public int deleteById(Long id)
    {
        return appDramaContentsMapper.deleteById(id);
    }
}
