package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.SummaryRequest;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AppDramaDisplayRulesMapper;
import com.ruoyi.system.domain.AppDramaDisplayRules;
import com.ruoyi.system.service.IAppDramaDisplayRulesService;
import com.ruoyi.common.core.service.BaseService;

/**
 * 剧目显示规则Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@Service
public class AppDramaDisplayRulesServiceImpl extends BaseService<AppDramaDisplayRules> implements IAppDramaDisplayRulesService
{
    @Autowired
    private AppDramaDisplayRulesMapper appDramaDisplayRulesMapper;

    @Override
    public AppDramaDisplayRulesMapper getMapper() {
        return appDramaDisplayRulesMapper;
    }

    /**
     * 查询剧目显示规则
     * 
     * @param id 剧目显示规则主键
     * @return 剧目显示规则
     */
    @Override
    public AppDramaDisplayRules getById(Long id)
    {
        return appDramaDisplayRulesMapper.selectById(id);
    }

    /**
     * 查询剧目显示规则列表
     * 
     * @param query 剧目显示规则
     * @return 剧目显示规则
     */
    @Override
    public List<AppDramaDisplayRules> selectList(AppDramaDisplayRules query)
    {
        return appDramaDisplayRulesMapper.selectList(new QueryWrapper<>(query));
    }


    /**
     * 查询剧目显示规则数据汇总
     *
     * @param query 剧目显示规则
     * @return 剧目显示规则
     */
    @Override
    public AppDramaDisplayRules getSummary(AppDramaDisplayRules query)
    {
        return appDramaDisplayRulesMapper.getSummary(query);
    }



    /**
     * 查询自定义分析数据
     *
     * @param query 剧目显示规则
     * @return 剧目显示规则
     */
    @Override
    public List<AppDramaDisplayRules> summary(SummaryRequest query)
    {
        return appDramaDisplayRulesMapper.summary(query);
    }

    @Override
    public AppDramaDisplayRules allSummary(SummaryRequest query)
    {
        return appDramaDisplayRulesMapper.allSummary(query);
    }

    /**
     * 新增剧目显示规则
     * 
     * @param appDramaDisplayRules 剧目显示规则
     * @return 结果
     */
    @Override
    public int insert(AppDramaDisplayRules appDramaDisplayRules)
    {
        appDramaDisplayRules.setCreateTime(DateUtils.getNowDate());
        return appDramaDisplayRulesMapper.insert(appDramaDisplayRules);
    }

    /**
     * 修改剧目显示规则
     * 
     * @param appDramaDisplayRules 剧目显示规则
     * @return 结果
     */
    @Override
    public int update(AppDramaDisplayRules appDramaDisplayRules)
    {
        appDramaDisplayRules.setUpdateTime(DateUtils.getNowDate());
        return appDramaDisplayRulesMapper.updateById(appDramaDisplayRules);
    }

    /**
     * 批量删除剧目显示规则
     * 
     * @param ids 需要删除的剧目显示规则主键
     * @return 结果
     */
    @Override
    public int deleteByIds(List<Long> ids)
    {
        return appDramaDisplayRulesMapper.deleteBatchIds(ids);
    }

    /**
     * 删除剧目显示规则信息
     * 
     * @param id 剧目显示规则主键
     * @return 结果
     */
    @Override
    public int deleteById(Long id)
    {
        return appDramaDisplayRulesMapper.deleteById(id);
    }
}
