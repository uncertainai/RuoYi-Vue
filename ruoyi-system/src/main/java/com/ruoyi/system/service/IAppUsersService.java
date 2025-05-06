package com.ruoyi.system.service;

import com.ruoyi.system.mapper.AppUsersMapper;
import com.ruoyi.common.core.domain.SummaryRequest;
import java.util.List;
import com.ruoyi.system.domain.AppUsers;
import com.ruoyi.common.core.service.IBaseService;

/**
 * 社交媒体用户Service接口
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
public interface IAppUsersService extends IBaseService<AppUsers>
{
    /**
     * 查询社交媒体用户
     * 
     * @param id 社交媒体用户主键
     * @return 社交媒体用户
     */
    public AppUsers getById(Long id);

    /**
     * 查询社交媒体用户数据汇总
     *
     * @param query 社交媒体用户
     * @return 社交媒体用户数据汇总
     */
    public AppUsers getSummary(AppUsers query);

    /**
     * 查询社交媒体用户列表
     * 
     * @param query 社交媒体用户
     * @return 社交媒体用户集合
     */
    public List<AppUsers> selectList(AppUsers query);

    /**
     * 新增社交媒体用户
     * 
     * @param appUsers 社交媒体用户
     * @return 结果
     */
    public int insert(AppUsers appUsers);

    /**
     * 修改社交媒体用户
     * 
     * @param appUsers 社交媒体用户
     * @return 结果
     */
    public int update(AppUsers appUsers);

    /**
     * 批量删除社交媒体用户
     * 
     * @param ids 需要删除的社交媒体用户主键集合
     * @return 结果
     */
    public int deleteByIds(List<Long> ids);

    /**
     * 删除社交媒体用户信息
     * 
     * @param id 社交媒体用户主键
     * @return 结果
     */
    public int deleteById(Long id);


    /**
     * 查询自定义分析数据
     *
     * @param query 社交媒体用户
     * @return 社交媒体用户集合
     */
    public List<AppUsers> summary(SummaryRequest query);

    AppUsers allSummary(SummaryRequest query);

    AppUsersMapper getMapper();
}
