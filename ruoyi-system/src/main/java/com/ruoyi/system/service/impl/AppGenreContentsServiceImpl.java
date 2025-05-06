package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.SummaryRequest;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AppGenreContentsMapper;
import com.ruoyi.system.domain.AppGenreContents;
import com.ruoyi.system.service.IAppGenreContentsService;
import com.ruoyi.common.core.service.BaseService;

/**
 * 剧目类型内容Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@Service
public class AppGenreContentsServiceImpl extends BaseService<AppGenreContents> implements IAppGenreContentsService
{
    @Autowired
    private AppGenreContentsMapper appGenreContentsMapper;

    @Override
    public AppGenreContentsMapper getMapper() {
        return appGenreContentsMapper;
    }

    /**
     * 查询剧目类型内容
     * 
     * @param id 剧目类型内容主键
     * @return 剧目类型内容
     */
    @Override
    public AppGenreContents getById(Long id)
    {
        return appGenreContentsMapper.selectById(id);
    }

    /**
     * 查询剧目类型内容列表
     * 
     * @param query 剧目类型内容
     * @return 剧目类型内容
     */
    @Override
    public List<AppGenreContents> selectList(AppGenreContents query)
    {
        return appGenreContentsMapper.selectList(new QueryWrapper<>(query));
    }


    /**
     * 查询剧目类型内容数据汇总
     *
     * @param query 剧目类型内容
     * @return 剧目类型内容
     */
    @Override
    public AppGenreContents getSummary(AppGenreContents query)
    {
        return appGenreContentsMapper.getSummary(query);
    }



    /**
     * 查询自定义分析数据
     *
     * @param query 剧目类型内容
     * @return 剧目类型内容
     */
    @Override
    public List<AppGenreContents> summary(SummaryRequest query)
    {
        return appGenreContentsMapper.summary(query);
    }

    @Override
    public AppGenreContents allSummary(SummaryRequest query)
    {
        return appGenreContentsMapper.allSummary(query);
    }

    /**
     * 新增剧目类型内容
     * 
     * @param appGenreContents 剧目类型内容
     * @return 结果
     */
    @Override
    public int insert(AppGenreContents appGenreContents)
    {
        appGenreContents.setCreateTime(DateUtils.getNowDate());
        return appGenreContentsMapper.insert(appGenreContents);
    }

    /**
     * 修改剧目类型内容
     * 
     * @param appGenreContents 剧目类型内容
     * @return 结果
     */
    @Override
    public int update(AppGenreContents appGenreContents)
    {
        appGenreContents.setUpdateTime(DateUtils.getNowDate());
        return appGenreContentsMapper.updateById(appGenreContents);
    }

    /**
     * 批量删除剧目类型内容
     * 
     * @param ids 需要删除的剧目类型内容主键
     * @return 结果
     */
    @Override
    public int deleteByIds(List<Long> ids)
    {
        return appGenreContentsMapper.deleteBatchIds(ids);
    }

    /**
     * 删除剧目类型内容信息
     * 
     * @param id 剧目类型内容主键
     * @return 结果
     */
    @Override
    public int deleteById(Long id)
    {
        return appGenreContentsMapper.deleteById(id);
    }
}
