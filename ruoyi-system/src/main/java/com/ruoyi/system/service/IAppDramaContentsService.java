package com.ruoyi.system.service;

import com.ruoyi.system.mapper.AppDramaContentsMapper;
import com.ruoyi.common.core.domain.SummaryRequest;
import java.util.List;
import com.ruoyi.system.domain.AppDramaContents;
import com.ruoyi.common.core.service.IBaseService;

/**
 * 剧目内容Service接口
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
public interface IAppDramaContentsService extends IBaseService<AppDramaContents>
{
    /**
     * 查询剧目内容
     * 
     * @param id 剧目内容主键
     * @return 剧目内容
     */
    public AppDramaContents getById(Long id);

    /**
     * 查询剧目内容数据汇总
     *
     * @param query 剧目内容
     * @return 剧目内容数据汇总
     */
    public AppDramaContents getSummary(AppDramaContents query);

    /**
     * 查询剧目内容列表
     * 
     * @param query 剧目内容
     * @return 剧目内容集合
     */
    public List<AppDramaContents> selectList(AppDramaContents query);

    /**
     * 新增剧目内容
     * 
     * @param appDramaContents 剧目内容
     * @return 结果
     */
    public int insert(AppDramaContents appDramaContents);

    /**
     * 修改剧目内容
     * 
     * @param appDramaContents 剧目内容
     * @return 结果
     */
    public int update(AppDramaContents appDramaContents);

    /**
     * 批量删除剧目内容
     * 
     * @param ids 需要删除的剧目内容主键集合
     * @return 结果
     */
    public int deleteByIds(List<Long> ids);

    /**
     * 删除剧目内容信息
     * 
     * @param id 剧目内容主键
     * @return 结果
     */
    public int deleteById(Long id);


    /**
     * 查询自定义分析数据
     *
     * @param query 剧目内容
     * @return 剧目内容集合
     */
    public List<AppDramaContents> summary(SummaryRequest query);

    AppDramaContents allSummary(SummaryRequest query);

    AppDramaContentsMapper getMapper();
}
