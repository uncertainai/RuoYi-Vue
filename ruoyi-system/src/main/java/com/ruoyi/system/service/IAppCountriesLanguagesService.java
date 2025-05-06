package com.ruoyi.system.service;

import com.ruoyi.system.mapper.AppCountriesLanguagesMapper;
import com.ruoyi.common.core.domain.SummaryRequest;
import java.util.List;
import com.ruoyi.system.domain.AppCountriesLanguages;
import com.ruoyi.common.core.service.IBaseService;

/**
 * 国家语言配置Service接口
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
public interface IAppCountriesLanguagesService extends IBaseService<AppCountriesLanguages>
{
    /**
     * 查询国家语言配置
     * 
     * @param id 国家语言配置主键
     * @return 国家语言配置
     */
    public AppCountriesLanguages getById(Long id);

    /**
     * 查询国家语言配置数据汇总
     *
     * @param query 国家语言配置
     * @return 国家语言配置数据汇总
     */
    public AppCountriesLanguages getSummary(AppCountriesLanguages query);

    /**
     * 查询国家语言配置列表
     * 
     * @param query 国家语言配置
     * @return 国家语言配置集合
     */
    public List<AppCountriesLanguages> selectList(AppCountriesLanguages query);

    /**
     * 新增国家语言配置
     * 
     * @param appCountriesLanguages 国家语言配置
     * @return 结果
     */
    public int insert(AppCountriesLanguages appCountriesLanguages);

    /**
     * 修改国家语言配置
     * 
     * @param appCountriesLanguages 国家语言配置
     * @return 结果
     */
    public int update(AppCountriesLanguages appCountriesLanguages);

    /**
     * 批量删除国家语言配置
     * 
     * @param ids 需要删除的国家语言配置主键集合
     * @return 结果
     */
    public int deleteByIds(List<Long> ids);

    /**
     * 删除国家语言配置信息
     * 
     * @param id 国家语言配置主键
     * @return 结果
     */
    public int deleteById(Long id);


    /**
     * 查询自定义分析数据
     *
     * @param query 国家语言配置
     * @return 国家语言配置集合
     */
    public List<AppCountriesLanguages> summary(SummaryRequest query);

    AppCountriesLanguages allSummary(SummaryRequest query);

    AppCountriesLanguagesMapper getMapper();
}
