package com.ruoyi.system.service;

import com.ruoyi.system.mapper.AppDramaMapper;
import com.ruoyi.common.core.domain.SummaryRequest;
import java.util.List;
import com.ruoyi.system.domain.AppDrama;
import com.ruoyi.common.core.service.IBaseService;

/**
 * 剧目Service接口
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
public interface IAppDramaService extends IBaseService<AppDrama>
{
    /**
     * 查询剧目
     * 
     * @param id 剧目主键
     * @return 剧目
     */
    public AppDrama getById(Long id);

    /**
     * 查询剧目数据汇总
     *
     * @param query 剧目
     * @return 剧目数据汇总
     */
    public AppDrama getSummary(AppDrama query);

    /**
     * 查询剧目列表
     * 
     * @param query 剧目
     * @return 剧目集合
     */
    public List<AppDrama> selectList(AppDrama query);

    /**
     * 新增剧目
     * 
     * @param appDrama 剧目
     * @return 结果
     */
    public int insert(AppDrama appDrama);

    /**
     * 修改剧目
     * 
     * @param appDrama 剧目
     * @return 结果
     */
    public int update(AppDrama appDrama);

    /**
     * 批量删除剧目
     * 
     * @param ids 需要删除的剧目主键集合
     * @return 结果
     */
    public int deleteByIds(List<Long> ids);

    /**
     * 删除剧目信息
     * 
     * @param id 剧目主键
     * @return 结果
     */
    public int deleteById(Long id);


    /**
     * 查询自定义分析数据
     *
     * @param query 剧目
     * @return 剧目集合
     */
    public List<AppDrama> summary(SummaryRequest query);

    AppDrama allSummary(SummaryRequest query);

    AppDramaMapper getMapper();
}
