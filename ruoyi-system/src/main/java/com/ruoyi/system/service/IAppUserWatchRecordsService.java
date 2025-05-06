package com.ruoyi.system.service;

import com.ruoyi.system.mapper.AppUserWatchRecordsMapper;
import com.ruoyi.common.core.domain.SummaryRequest;
import java.util.List;
import com.ruoyi.system.domain.AppUserWatchRecords;
import com.ruoyi.common.core.service.IBaseService;

/**
 * 用户观看记录Service接口
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
public interface IAppUserWatchRecordsService extends IBaseService<AppUserWatchRecords>
{
    /**
     * 查询用户观看记录
     * 
     * @param id 用户观看记录主键
     * @return 用户观看记录
     */
    public AppUserWatchRecords getById(Long id);

    /**
     * 查询用户观看记录数据汇总
     *
     * @param query 用户观看记录
     * @return 用户观看记录数据汇总
     */
    public AppUserWatchRecords getSummary(AppUserWatchRecords query);

    /**
     * 查询用户观看记录列表
     * 
     * @param query 用户观看记录
     * @return 用户观看记录集合
     */
    public List<AppUserWatchRecords> selectList(AppUserWatchRecords query);

    /**
     * 新增用户观看记录
     * 
     * @param appUserWatchRecords 用户观看记录
     * @return 结果
     */
    public int insert(AppUserWatchRecords appUserWatchRecords);

    /**
     * 修改用户观看记录
     * 
     * @param appUserWatchRecords 用户观看记录
     * @return 结果
     */
    public int update(AppUserWatchRecords appUserWatchRecords);

    /**
     * 批量删除用户观看记录
     * 
     * @param ids 需要删除的用户观看记录主键集合
     * @return 结果
     */
    public int deleteByIds(List<Long> ids);

    /**
     * 删除用户观看记录信息
     * 
     * @param id 用户观看记录主键
     * @return 结果
     */
    public int deleteById(Long id);


    /**
     * 查询自定义分析数据
     *
     * @param query 用户观看记录
     * @return 用户观看记录集合
     */
    public List<AppUserWatchRecords> summary(SummaryRequest query);

    AppUserWatchRecords allSummary(SummaryRequest query);

    AppUserWatchRecordsMapper getMapper();
}
