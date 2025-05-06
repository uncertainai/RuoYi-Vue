package com.ruoyi.system.service;

import com.ruoyi.system.mapper.AppLoginRecordsMapper;
import com.ruoyi.common.core.domain.SummaryRequest;
import java.util.List;
import com.ruoyi.system.domain.AppLoginRecords;
import com.ruoyi.common.core.service.IBaseService;

/**
 * 用户登录记录Service接口
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
public interface IAppLoginRecordsService extends IBaseService<AppLoginRecords>
{
    /**
     * 查询用户登录记录
     * 
     * @param id 用户登录记录主键
     * @return 用户登录记录
     */
    public AppLoginRecords getById(Long id);

    /**
     * 查询用户登录记录数据汇总
     *
     * @param query 用户登录记录
     * @return 用户登录记录数据汇总
     */
    public AppLoginRecords getSummary(AppLoginRecords query);

    /**
     * 查询用户登录记录列表
     * 
     * @param query 用户登录记录
     * @return 用户登录记录集合
     */
    public List<AppLoginRecords> selectList(AppLoginRecords query);

    /**
     * 新增用户登录记录
     * 
     * @param appLoginRecords 用户登录记录
     * @return 结果
     */
    public int insert(AppLoginRecords appLoginRecords);

    /**
     * 修改用户登录记录
     * 
     * @param appLoginRecords 用户登录记录
     * @return 结果
     */
    public int update(AppLoginRecords appLoginRecords);

    /**
     * 批量删除用户登录记录
     * 
     * @param ids 需要删除的用户登录记录主键集合
     * @return 结果
     */
    public int deleteByIds(List<Long> ids);

    /**
     * 删除用户登录记录信息
     * 
     * @param id 用户登录记录主键
     * @return 结果
     */
    public int deleteById(Long id);


    /**
     * 查询自定义分析数据
     *
     * @param query 用户登录记录
     * @return 用户登录记录集合
     */
    public List<AppLoginRecords> summary(SummaryRequest query);

    AppLoginRecords allSummary(SummaryRequest query);

    AppLoginRecordsMapper getMapper();
}
