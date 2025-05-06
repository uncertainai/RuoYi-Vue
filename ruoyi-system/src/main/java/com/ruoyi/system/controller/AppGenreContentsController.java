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
import com.ruoyi.system.domain.AppGenreContents;
import com.ruoyi.system.service.IAppGenreContentsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 剧目类型内容Controller
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@RestController
@RequestMapping("/app/gnere_contents")
public class AppGenreContentsController extends BaseController
{
    @Autowired
    private IAppGenreContentsService appGenreContentsService;

    /**
     * 查询剧目类型内容列表
     */
    @PreAuthorize("@ss.hasPermi('app:gnere_contents:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppGenreContents appGenreContents)
    {
        startPage();
        List<AppGenreContents> list = appGenreContentsService.selectList(appGenreContents);
        return getDataTable(list);
    }

    /**
     * 获取剧目类型内容数据汇总
     */
    @PreAuthorize("@ss.hasPermi('app:gnere_contents:query')")
    @GetMapping(value = "/getSummary")
    public AjaxResult getSummary(AppGenreContents appGenreContents)
    {
        return AjaxResult.success(appGenreContentsService.getSummary(appGenreContents));
    }

    /**
     * 导出剧目类型内容列表
     */
    @PreAuthorize("@ss.hasPermi('app:gnere_contents:export')")
    @Log(title = "剧目类型内容", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppGenreContents appGenreContents)
    {
        List<AppGenreContents> list = appGenreContentsService.selectList(appGenreContents);
        ExcelUtil<AppGenreContents> util = new ExcelUtil<>(AppGenreContents.class);
        util.exportExcel(response, list, "剧目类型内容数据");
    }

    /**
     * 获取剧目类型内容详细信息
     */
    @PreAuthorize("@ss.hasPermi('app:gnere_contents:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(appGenreContentsService.getById(id));
    }

    /**
     * 新增剧目类型内容
     */
    @PreAuthorize("@ss.hasPermi('app:gnere_contents:add')")
    @Log(title = "剧目类型内容", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AppGenreContents appGenreContents)
    {
        return toAjax(appGenreContentsService.insert(appGenreContents));
    }

    /**
     * 修改剧目类型内容
     */
    @PreAuthorize("@ss.hasPermi('app:gnere_contents:edit')")
    @Log(title = "剧目类型内容", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AppGenreContents appGenreContents)
    {
        return toAjax(appGenreContentsService.update(appGenreContents));
    }

    /**
     * 删除剧目类型内容
     */
    @PreAuthorize("@ss.hasPermi('app:gnere_contents:remove')")
    @Log(title = "剧目类型内容", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariableList List<Long> ids)
    {
        return toAjax(appGenreContentsService.deleteByIds(ids));
    }


    /**
     * 查询自定义分析列
     */
    @PreAuthorize("@ss.hasPermi('app:gnere_contents:list')")
    @GetMapping("/columns")
    public AjaxResult columns()
    {
        return AjaxResult.success(SummaryColumnUtils.get(AppGenreContents.class));
    }

    /**
     * 查询自定义分析数据
     */
    @PreAuthorize("@ss.hasPermi('app:gnere_contents:list')")
    @RequestMapping(value = "/summary")
    public TableDataInfo summary(@RequestBody SummaryRequest query)
    {
        startPage();
        List<AppGenreContents> summary = appGenreContentsService.summary(query);
        return getDataTable(summary);
    }

    /**
     * 导出自定义分析数据
     */
    @PreAuthorize("@ss.hasPermi('app:gnere_contents:export')")
    @Log(title = "导出自定义分析数据", businessType = BusinessType.EXPORT)
    @PostMapping("/summaryExport")
    public void summaryExport(HttpServletResponse response, @RequestBody SummaryRequest query)
    {
        PageHelper.startPage(1, Integer.MAX_VALUE);
        if (null == query.getQuery()) {
            query.setQuery(new ArrayList<>());
        }
        List<AppGenreContents> list = appGenreContentsService.summary(query);
        query.getGroupBy().addAll(query.getColumns());
        ExcelUtil<AppGenreContents> util = new ExcelUtil<>(AppGenreContents.class, query.getGroupBy());
        util.exportExcel(response, list, "导出数据");
    }



    /**
     * 下载导入模板
     */
    @PreAuthorize("@ss.hasPermi('app:gnere_contents:export')")
    @Log(title = "下载导入模板", businessType = BusinessType.EXPORT)
    @PostMapping("/downloadImportModule")
    public void downloadImportModule()
    {
        appGenreContentsService.downloadImportModule();
    }

    /**
     * 导入数据
     */
    @PreAuthorize("@ss.hasPermi('app:gnere_contents:export')")
    @Log(title = "导入数据", businessType = BusinessType.EXPORT)
    @PostMapping("/importData")
    public void importData(MultipartFile file)
    {

        appGenreContentsService.importData(file);
    }


    /**
     * 查询自定义分析数据-汇总
     */
    @PreAuthorize("@ss.hasPermi('app:gnere_contents:list')")
    @RequestMapping(value = "/allSummary")
    public AjaxResult allSummary(@RequestBody SummaryRequest query)
    {
        PageHelper.startPage(1, 1);
        return AjaxResult.success(appGenreContentsService.allSummary(query));
    }
}
