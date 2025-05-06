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
import com.ruoyi.system.domain.AppDramaContents;
import com.ruoyi.system.service.IAppDramaContentsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 剧目内容Controller
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@RestController
@RequestMapping("/app/drama_contents")
public class AppDramaContentsController extends BaseController
{
    @Autowired
    private IAppDramaContentsService appDramaContentsService;

    /**
     * 查询剧目内容列表
     */
    @PreAuthorize("@ss.hasPermi('app:drama_contents:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppDramaContents appDramaContents)
    {
        startPage();
        List<AppDramaContents> list = appDramaContentsService.selectList(appDramaContents);
        return getDataTable(list);
    }

    /**
     * 获取剧目内容数据汇总
     */
    @PreAuthorize("@ss.hasPermi('app:drama_contents:query')")
    @GetMapping(value = "/getSummary")
    public AjaxResult getSummary(AppDramaContents appDramaContents)
    {
        return AjaxResult.success(appDramaContentsService.getSummary(appDramaContents));
    }

    /**
     * 导出剧目内容列表
     */
    @PreAuthorize("@ss.hasPermi('app:drama_contents:export')")
    @Log(title = "剧目内容", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppDramaContents appDramaContents)
    {
        List<AppDramaContents> list = appDramaContentsService.selectList(appDramaContents);
        ExcelUtil<AppDramaContents> util = new ExcelUtil<>(AppDramaContents.class);
        util.exportExcel(response, list, "剧目内容数据");
    }

    /**
     * 获取剧目内容详细信息
     */
    @PreAuthorize("@ss.hasPermi('app:drama_contents:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(appDramaContentsService.getById(id));
    }

    /**
     * 新增剧目内容
     */
    @PreAuthorize("@ss.hasPermi('app:drama_contents:add')")
    @Log(title = "剧目内容", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AppDramaContents appDramaContents)
    {
        return toAjax(appDramaContentsService.insert(appDramaContents));
    }

    /**
     * 修改剧目内容
     */
    @PreAuthorize("@ss.hasPermi('app:drama_contents:edit')")
    @Log(title = "剧目内容", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AppDramaContents appDramaContents)
    {
        return toAjax(appDramaContentsService.update(appDramaContents));
    }

    /**
     * 删除剧目内容
     */
    @PreAuthorize("@ss.hasPermi('app:drama_contents:remove')")
    @Log(title = "剧目内容", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariableList List<Long> ids)
    {
        return toAjax(appDramaContentsService.deleteByIds(ids));
    }


    /**
     * 查询自定义分析列
     */
    @PreAuthorize("@ss.hasPermi('app:drama_contents:list')")
    @GetMapping("/columns")
    public AjaxResult columns()
    {
        return AjaxResult.success(SummaryColumnUtils.get(AppDramaContents.class));
    }

    /**
     * 查询自定义分析数据
     */
    @PreAuthorize("@ss.hasPermi('app:drama_contents:list')")
    @RequestMapping(value = "/summary")
    public TableDataInfo summary(@RequestBody SummaryRequest query)
    {
        startPage();
        List<AppDramaContents> summary = appDramaContentsService.summary(query);
        return getDataTable(summary);
    }

    /**
     * 导出自定义分析数据
     */
    @PreAuthorize("@ss.hasPermi('app:drama_contents:export')")
    @Log(title = "导出自定义分析数据", businessType = BusinessType.EXPORT)
    @PostMapping("/summaryExport")
    public void summaryExport(HttpServletResponse response, @RequestBody SummaryRequest query)
    {
        PageHelper.startPage(1, Integer.MAX_VALUE);
        if (null == query.getQuery()) {
            query.setQuery(new ArrayList<>());
        }
        List<AppDramaContents> list = appDramaContentsService.summary(query);
        query.getGroupBy().addAll(query.getColumns());
        ExcelUtil<AppDramaContents> util = new ExcelUtil<>(AppDramaContents.class, query.getGroupBy());
        util.exportExcel(response, list, "导出数据");
    }



    /**
     * 下载导入模板
     */
    @PreAuthorize("@ss.hasPermi('app:drama_contents:export')")
    @Log(title = "下载导入模板", businessType = BusinessType.EXPORT)
    @PostMapping("/downloadImportModule")
    public void downloadImportModule()
    {
        appDramaContentsService.downloadImportModule();
    }

    /**
     * 导入数据
     */
    @PreAuthorize("@ss.hasPermi('app:drama_contents:export')")
    @Log(title = "导入数据", businessType = BusinessType.EXPORT)
    @PostMapping("/importData")
    public void importData(MultipartFile file)
    {

        appDramaContentsService.importData(file);
    }


    /**
     * 查询自定义分析数据-汇总
     */
    @PreAuthorize("@ss.hasPermi('app:drama_contents:list')")
    @RequestMapping(value = "/allSummary")
    public AjaxResult allSummary(@RequestBody SummaryRequest query)
    {
        PageHelper.startPage(1, 1);
        return AjaxResult.success(appDramaContentsService.allSummary(query));
    }
}
