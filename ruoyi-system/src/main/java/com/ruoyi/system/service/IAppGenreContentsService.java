package com.ruoyi.system.service;

import com.ruoyi.system.mapper.AppGenreContentsMapper;
import com.ruoyi.common.core.domain.SummaryRequest;
import java.util.List;
import com.ruoyi.system.domain.AppGenreContents;
import com.ruoyi.common.core.service.IBaseService;

/**
 * 剧目类型内容Service接口
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
public interface IAppGenreContentsService extends IBaseService<AppGenreContents>
{
    /**
     * 查询剧目类型内容
     * 
     * @param id 剧目类型内容主键
     * @return 剧目类型内容
     */
    public AppGenreContents getById(Long id);

    /**
     * 查询剧目类型内容数据汇总
     *
     * @param query 剧目类型内容
     * @return 剧目类型内容数据汇总
     */
    public AppGenreContents getSummary(AppGenreContents query);

    /**
     * 查询剧目类型内容列表
     * 
     * @param query 剧目类型内容
     * @return 剧目类型内容集合
     */
    public List<AppGenreContents> selectList(AppGenreContents query);

    /**
     * 新增剧目类型内容
     * 
     * @param appGenreContents 剧目类型内容
     * @return 结果
     */
    public int insert(AppGenreContents appGenreContents);

    /**
     * 修改剧目类型内容
     * 
     * @param appGenreContents 剧目类型内容
     * @return 结果
     */
    public int update(AppGenreContents appGenreContents);

    /**
     * 批量删除剧目类型内容
     * 
     * @param ids 需要删除的剧目类型内容主键集合
     * @return 结果
     */
    public int deleteByIds(List<Long> ids);

    /**
     * 删除剧目类型内容信息
     * 
     * @param id 剧目类型内容主键
     * @return 结果
     */
    public int deleteById(Long id);


    /**
     * 查询自定义分析数据
     *
     * @param query 剧目类型内容
     * @return 剧目类型内容集合
     */
    public List<AppGenreContents> summary(SummaryRequest query);

    AppGenreContents allSummary(SummaryRequest query);

    AppGenreContentsMapper getMapper();
}
