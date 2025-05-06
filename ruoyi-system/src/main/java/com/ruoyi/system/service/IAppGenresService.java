package com.ruoyi.system.service;

import com.ruoyi.system.mapper.AppGenresMapper;
import com.ruoyi.common.core.domain.SummaryRequest;
import java.util.List;
import com.ruoyi.system.domain.AppGenres;
import com.ruoyi.common.core.service.IBaseService;

/**
 * 剧目类型Service接口
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
public interface IAppGenresService extends IBaseService<AppGenres>
{
    /**
     * 查询剧目类型
     * 
     * @param id 剧目类型主键
     * @return 剧目类型
     */
    public AppGenres getById(Long id);

    /**
     * 查询剧目类型数据汇总
     *
     * @param query 剧目类型
     * @return 剧目类型数据汇总
     */
    public AppGenres getSummary(AppGenres query);

    /**
     * 查询剧目类型列表
     * 
     * @param query 剧目类型
     * @return 剧目类型集合
     */
    public List<AppGenres> selectList(AppGenres query);

    /**
     * 新增剧目类型
     * 
     * @param appGenres 剧目类型
     * @return 结果
     */
    public int insert(AppGenres appGenres);

    /**
     * 修改剧目类型
     * 
     * @param appGenres 剧目类型
     * @return 结果
     */
    public int update(AppGenres appGenres);

    /**
     * 批量删除剧目类型
     * 
     * @param ids 需要删除的剧目类型主键集合
     * @return 结果
     */
    public int deleteByIds(List<Long> ids);

    /**
     * 删除剧目类型信息
     * 
     * @param id 剧目类型主键
     * @return 结果
     */
    public int deleteById(Long id);


    /**
     * 查询自定义分析数据
     *
     * @param query 剧目类型
     * @return 剧目类型集合
     */
    public List<AppGenres> summary(SummaryRequest query);

    AppGenres allSummary(SummaryRequest query);

    AppGenresMapper getMapper();
}
