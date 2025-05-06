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
import com.ruoyi.system.domain.AppUserWatchRecords;
import com.ruoyi.system.service.IAppUserWatchRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户观看记录Controller
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@RestController
@RequestMapping("/app/watch_records")
public class AppUserWatchRecordsController extends BaseController
{
    @Autowired
    private IAppUserWatchRecordsService appUserWatchRecordsService;

    /**
     * 查询用户观看记录列表
     */
    @PreAuthorize("@ss.hasPermi('app:watch_records:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppUserWatchRecords appUserWatchRecords)
    {
        startPage();
        List<AppUserWatchRecords> list = appUserWatchRecordsService.selectList(appUserWatchRecords);
        return getDataTable(list);
    }

    /**
     * 获取用户观看记录数据汇总
     */
    @PreAuthorize("@ss.hasPermi('app:watch_records:query')")
    @GetMapping(value = "/getSummary")
    public AjaxResult getSummary(AppUserWatchRecords appUserWatchRecords)
    {
        return AjaxResult.success(appUserWatchRecordsService.getSummary(appUserWatchRecords));
    }

    /**
     * 导出用户观看记录列表
     */
    @PreAuthorize("@ss.hasPermi('app:watch_records:export')")
    @Log(title = "用户观看记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppUserWatchRecords appUserWatchRecords)
    {
        List<AppUserWatchRecords> list = appUserWatchRecordsService.selectList(appUserWatchRecords);
        ExcelUtil<AppUserWatchRecords> util = new ExcelUtil<>(AppUserWatchRecords.class);
        util.exportExcel(response, list, "用户观看记录数据");
    }

    /**
     * 获取用户观看记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('app:watch_records:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(appUserWatchRecordsService.getById(id));
    }

    /**
     * 新增用户观看记录
     */
    @PreAuthorize("@ss.hasPermi('app:watch_records:add')")
    @Log(title = "用户观看记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AppUserWatchRecords appUserWatchRecords)
    {
        return toAjax(appUserWatchRecordsService.insert(appUserWatchRecords));
    }

    /**
     * 修改用户观看记录
     */
    @PreAuthorize("@ss.hasPermi('app:watch_records:edit')")
    @Log(title = "用户观看记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AppUserWatchRecords appUserWatchRecords)
    {
        return toAjax(appUserWatchRecordsService.update(appUserWatchRecords));
    }

    /**
     * 删除用户观看记录
     */
    @PreAuthorize("@ss.hasPermi('app:watch_records:remove')")
    @Log(title = "用户观看记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariableList List<Long> ids)
    {
        return toAjax(appUserWatchRecordsService.deleteByIds(ids));
    }


    /**
     * 查询自定义分析列
     */
    @PreAuthorize("@ss.hasPermi('app:watch_records:list')")
    @GetMapping("/columns")
    public AjaxResult columns()
    {
        return AjaxResult.success(SummaryColumnUtils.get(AppUserWatchRecords.class));
    }

    /**
     * 查询自定义分析数据
     */
    @PreAuthorize("@ss.hasPermi('app:watch_records:list')")
    @RequestMapping(value = "/summary")
    public TableDataInfo summary(@RequestBody SummaryRequest query)
    {
        startPage();
        List<AppUserWatchRecords> summary = appUserWatchRecordsService.summary(query);
        return getDataTable(summary);
    }

    /**
     * 导出自定义分析数据
     */
    @PreAuthorize("@ss.hasPermi('app:watch_records:export')")
    @Log(title = "导出自定义分析数据", businessType = BusinessType.EXPORT)
    @PostMapping("/summaryExport")
    public void summaryExport(HttpServletResponse response, @RequestBody SummaryRequest query)
    {
        PageHelper.startPage(1, Integer.MAX_VALUE);
        if (null == query.getQuery()) {
            query.setQuery(new ArrayList<>());
        }
        List<AppUserWatchRecords> list = appUserWatchRecordsService.summary(query);
        query.getGroupBy().addAll(query.getColumns());
        ExcelUtil<AppUserWatchRecords> util = new ExcelUtil<>(AppUserWatchRecords.class, query.getGroupBy());
        util.exportExcel(response, list, "导出数据");
    }



    /**
     * 下载导入模板
     */
    @PreAuthorize("@ss.hasPermi('app:watch_records:export')")
    @Log(title = "下载导入模板", businessType = BusinessType.EXPORT)
    @PostMapping("/downloadImportModule")
    public void downloadImportModule()
    {
        appUserWatchRecordsService.downloadImportModule();
    }

    /**
     * 导入数据
     */
    @PreAuthorize("@ss.hasPermi('app:watch_records:export')")
    @Log(title = "导入数据", businessType = BusinessType.EXPORT)
    @PostMapping("/importData")
    public void importData(MultipartFile file)
    {

        appUserWatchRecordsService.importData(file);
    }


    /**
     * 查询自定义分析数据-汇总
     */
    @PreAuthorize("@ss.hasPermi('app:watch_records:list')")
    @RequestMapping(value = "/allSummary")
    public AjaxResult allSummary(@RequestBody SummaryRequest query)
    {
        PageHelper.startPage(1, 1);
        return AjaxResult.success(appUserWatchRecordsService.allSummary(query));
    }
}
