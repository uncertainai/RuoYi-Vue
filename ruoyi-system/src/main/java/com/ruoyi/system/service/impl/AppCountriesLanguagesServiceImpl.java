package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.SummaryRequest;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AppCountriesLanguagesMapper;
import com.ruoyi.system.domain.AppCountriesLanguages;
import com.ruoyi.system.service.IAppCountriesLanguagesService;
import com.ruoyi.common.core.service.BaseService;

/**
 * 国家语言配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@Service
public class AppCountriesLanguagesServiceImpl extends BaseService<AppCountriesLanguages> implements IAppCountriesLanguagesService
{
    @Autowired
    private AppCountriesLanguagesMapper appCountriesLanguagesMapper;

    @Override
    public AppCountriesLanguagesMapper getMapper() {
        return appCountriesLanguagesMapper;
    }

    /**
     * 查询国家语言配置
     * 
     * @param id 国家语言配置主键
     * @return 国家语言配置
     */
    @Override
    public AppCountriesLanguages getById(Long id)
    {
        return appCountriesLanguagesMapper.selectById(id);
    }

    /**
     * 查询国家语言配置列表
     * 
     * @param query 国家语言配置
     * @return 国家语言配置
     */
    @Override
    public List<AppCountriesLanguages> selectList(AppCountriesLanguages query)
    {
        return appCountriesLanguagesMapper.selectList(new QueryWrapper<>(query));
    }


    /**
     * 查询国家语言配置数据汇总
     *
     * @param query 国家语言配置
     * @return 国家语言配置
     */
    @Override
    public AppCountriesLanguages getSummary(AppCountriesLanguages query)
    {
        return appCountriesLanguagesMapper.getSummary(query);
    }



    /**
     * 查询自定义分析数据
     *
     * @param query 国家语言配置
     * @return 国家语言配置
     */
    @Override
    public List<AppCountriesLanguages> summary(SummaryRequest query)
    {
        return appCountriesLanguagesMapper.summary(query);
    }

    @Override
    public AppCountriesLanguages allSummary(SummaryRequest query)
    {
        return appCountriesLanguagesMapper.allSummary(query);
    }

    /**
     * 新增国家语言配置
     * 
     * @param appCountriesLanguages 国家语言配置
     * @return 结果
     */
    @Override
    public int insert(AppCountriesLanguages appCountriesLanguages)
    {
        appCountriesLanguages.setCreateTime(DateUtils.getNowDate());
        return appCountriesLanguagesMapper.insert(appCountriesLanguages);
    }

    /**
     * 修改国家语言配置
     * 
     * @param appCountriesLanguages 国家语言配置
     * @return 结果
     */
    @Override
    public int update(AppCountriesLanguages appCountriesLanguages)
    {
        appCountriesLanguages.setUpdateTime(DateUtils.getNowDate());
        return appCountriesLanguagesMapper.updateById(appCountriesLanguages);
    }

    /**
     * 批量删除国家语言配置
     * 
     * @param ids 需要删除的国家语言配置主键
     * @return 结果
     */
    @Override
    public int deleteByIds(List<Long> ids)
    {
        return appCountriesLanguagesMapper.deleteBatchIds(ids);
    }

    /**
     * 删除国家语言配置信息
     * 
     * @param id 国家语言配置主键
     * @return 结果
     */
    @Override
    public int deleteById(Long id)
    {
        return appCountriesLanguagesMapper.deleteById(id);
    }
}
