package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.SummaryRequest;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AppGenresMapper;
import com.ruoyi.system.domain.AppGenres;
import com.ruoyi.system.service.IAppGenresService;
import com.ruoyi.common.core.service.BaseService;

/**
 * 剧目类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@Service
public class AppGenresServiceImpl extends BaseService<AppGenres> implements IAppGenresService
{
    @Autowired
    private AppGenresMapper appGenresMapper;

    @Override
    public AppGenresMapper getMapper() {
        return appGenresMapper;
    }

    /**
     * 查询剧目类型
     * 
     * @param id 剧目类型主键
     * @return 剧目类型
     */
    @Override
    public AppGenres getById(Long id)
    {
        return appGenresMapper.selectById(id);
    }

    /**
     * 查询剧目类型列表
     * 
     * @param query 剧目类型
     * @return 剧目类型
     */
    @Override
    public List<AppGenres> selectList(AppGenres query)
    {
        return appGenresMapper.selectList(new QueryWrapper<>(query));
    }


    /**
     * 查询剧目类型数据汇总
     *
     * @param query 剧目类型
     * @return 剧目类型
     */
    @Override
    public AppGenres getSummary(AppGenres query)
    {
        return appGenresMapper.getSummary(query);
    }



    /**
     * 查询自定义分析数据
     *
     * @param query 剧目类型
     * @return 剧目类型
     */
    @Override
    public List<AppGenres> summary(SummaryRequest query)
    {
        return appGenresMapper.summary(query);
    }

    @Override
    public AppGenres allSummary(SummaryRequest query)
    {
        return appGenresMapper.allSummary(query);
    }

    /**
     * 新增剧目类型
     * 
     * @param appGenres 剧目类型
     * @return 结果
     */
    @Override
    public int insert(AppGenres appGenres)
    {
        appGenres.setCreateTime(DateUtils.getNowDate());
        return appGenresMapper.insert(appGenres);
    }

    /**
     * 修改剧目类型
     * 
     * @param appGenres 剧目类型
     * @return 结果
     */
    @Override
    public int update(AppGenres appGenres)
    {
        appGenres.setUpdateTime(DateUtils.getNowDate());
        return appGenresMapper.updateById(appGenres);
    }

    /**
     * 批量删除剧目类型
     * 
     * @param ids 需要删除的剧目类型主键
     * @return 结果
     */
    @Override
    public int deleteByIds(List<Long> ids)
    {
        return appGenresMapper.deleteBatchIds(ids);
    }

    /**
     * 删除剧目类型信息
     * 
     * @param id 剧目类型主键
     * @return 结果
     */
    @Override
    public int deleteById(Long id)
    {
        return appGenresMapper.deleteById(id);
    }
}
