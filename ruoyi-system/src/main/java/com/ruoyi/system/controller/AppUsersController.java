package com.ruoyi.system.controller;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.config.PathVariableList;
import com.ruoyi.common.core.domain.SummaryRequest;
import com.ruoyi.common.utils.SummaryColumnUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.AppUsers;
import com.ruoyi.system.service.IAppUsersService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 社交媒体用户Controller
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@RestController
@RequestMapping("/app/users")
public class AppUsersController extends BaseController
{
    @Autowired
    private IAppUsersService appUsersService;

    /**
     * 查询社交媒体用户列表
     */
    @PreAuthorize("@ss.hasPermi('app:users:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppUsers appUsers)
    {
        startPage();
        List<AppUsers> list = appUsersService.selectList(appUsers);
        return getDataTable(list);
    }

    /**
     * 获取社交媒体用户数据汇总
     */
    @PreAuthorize("@ss.hasPermi('app:users:query')")
    @GetMapping(value = "/getSummary")
    public AjaxResult getSummary(AppUsers appUsers)
    {
        return AjaxResult.success(appUsersService.getSummary(appUsers));
    }

    /**
     * 导出社交媒体用户列表
     */
    @PreAuthorize("@ss.hasPermi('app:users:export')")
    @Log(title = "社交媒体用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppUsers appUsers)
    {
        List<AppUsers> list = appUsersService.selectList(appUsers);
        ExcelUtil<AppUsers> util = new ExcelUtil<>(AppUsers.class);
        util.exportExcel(response, list, "社交媒体用户数据");
    }

    /**
     * 获取社交媒体用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('app:users:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(appUsersService.getById(id));
    }

    /**
     * 新增社交媒体用户
     */
    @PreAuthorize("@ss.hasPermi('app:users:add')")
    @Log(title = "社交媒体用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AppUsers appUsers)
    {
        return toAjax(appUsersService.insert(appUsers));
    }

    /**
     * 修改社交媒体用户
     */
    @PreAuthorize("@ss.hasPermi('app:users:edit')")
    @Log(title = "社交媒体用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AppUsers appUsers)
    {
        return toAjax(appUsersService.update(appUsers));
    }

    /**
     * 删除社交媒体用户
     */
    @PreAuthorize("@ss.hasPermi('app:users:remove')")
    @Log(title = "社交媒体用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariableList List<Long> ids)
    {
        return toAjax(appUsersService.deleteByIds(ids));
    }


    /**
     * 查询自定义分析列
     */
    @PreAuthorize("@ss.hasPermi('app:users:list')")
    @GetMapping("/columns")
    public AjaxResult columns()
    {
        return AjaxResult.success(SummaryColumnUtils.get(AppUsers.class));
    }

    /**
     * 查询自定义分析数据
     */
    @PreAuthorize("@ss.hasPermi('app:users:list')")
    @RequestMapping(value = "/summary")
    public TableDataInfo summary(@RequestBody SummaryRequest query)
    {
        startPage();
        List<AppUsers> summary = appUsersService.summary(query);
        return getDataTable(summary);
    }

    /**
     * 导出自定义分析数据
     */
    @PreAuthorize("@ss.hasPermi('app:users:export')")
    @Log(title = "导出自定义分析数据", businessType = BusinessType.EXPORT)
    @PostMapping("/summaryExport")
    public void summaryExport(HttpServletResponse response, @RequestBody SummaryRequest query)
    {
        PageHelper.startPage(1, Integer.MAX_VALUE);
        if (null == query.getQuery()) {
            query.setQuery(new ArrayList<>());
        }
        List<AppUsers> list = appUsersService.summary(query);
        query.getGroupBy().addAll(query.getColumns());
        ExcelUtil<AppUsers> util = new ExcelUtil<>(AppUsers.class, query.getGroupBy());
        util.exportExcel(response, list, "导出数据");
    }



    /**
     * 下载导入模板
     */
    @PreAuthorize("@ss.hasPermi('app:users:export')")
    @Log(title = "下载导入模板", businessType = BusinessType.EXPORT)
    @PostMapping("/downloadImportModule")
    public void downloadImportModule()
    {
        appUsersService.downloadImportModule();
    }

    /**
     * 导入数据
     */
    @PreAuthorize("@ss.hasPermi('app:users:export')")
    @Log(title = "导入数据", businessType = BusinessType.EXPORT)
    @PostMapping("/importData")
    public void importData(MultipartFile file)
    {

        appUsersService.importData(file);
    }


    /**
     * 查询自定义分析数据-汇总
     */
    @PreAuthorize("@ss.hasPermi('app:users:list')")
    @RequestMapping(value = "/allSummary")
    public AjaxResult allSummary(@RequestBody SummaryRequest query)
    {
        PageHelper.startPage(1, 1);
        return AjaxResult.success(appUsersService.allSummary(query));
    }
}
