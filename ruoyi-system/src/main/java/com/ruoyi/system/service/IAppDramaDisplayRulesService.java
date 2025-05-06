package com.ruoyi.system.service;

import com.ruoyi.system.mapper.AppDramaDisplayRulesMapper;
import com.ruoyi.common.core.domain.SummaryRequest;
import java.util.List;
import com.ruoyi.system.domain.AppDramaDisplayRules;
import com.ruoyi.common.core.service.IBaseService;

/**
 * 剧目显示规则Service接口
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
public interface IAppDramaDisplayRulesService extends IBaseService<AppDramaDisplayRules>
{
    /**
     * 查询剧目显示规则
     * 
     * @param id 剧目显示规则主键
     * @return 剧目显示规则
     */
    public AppDramaDisplayRules getById(Long id);

    /**
     * 查询剧目显示规则数据汇总
     *
     * @param query 剧目显示规则
     * @return 剧目显示规则数据汇总
     */
    public AppDramaDisplayRules getSummary(AppDramaDisplayRules query);

    /**
     * 查询剧目显示规则列表
     * 
     * @param query 剧目显示规则
     * @return 剧目显示规则集合
     */
    public List<AppDramaDisplayRules> selectList(AppDramaDisplayRules query);

    /**
     * 新增剧目显示规则
     * 
     * @param appDramaDisplayRules 剧目显示规则
     * @return 结果
     */
    public int insert(AppDramaDisplayRules appDramaDisplayRules);

    /**
     * 修改剧目显示规则
     * 
     * @param appDramaDisplayRules 剧目显示规则
     * @return 结果
     */
    public int update(AppDramaDisplayRules appDramaDisplayRules);

    /**
     * 批量删除剧目显示规则
     * 
     * @param ids 需要删除的剧目显示规则主键集合
     * @return 结果
     */
    public int deleteByIds(List<Long> ids);

    /**
     * 删除剧目显示规则信息
     * 
     * @param id 剧目显示规则主键
     * @return 结果
     */
    public int deleteById(Long id);


    /**
     * 查询自定义分析数据
     *
     * @param query 剧目显示规则
     * @return 剧目显示规则集合
     */
    public List<AppDramaDisplayRules> summary(SummaryRequest query);

    AppDramaDisplayRules allSummary(SummaryRequest query);

    AppDramaDisplayRulesMapper getMapper();
}
