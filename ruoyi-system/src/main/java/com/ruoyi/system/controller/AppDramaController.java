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
import com.ruoyi.system.domain.AppDrama;
import com.ruoyi.system.service.IAppDramaService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 剧目Controller
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@RestController
@RequestMapping("/app/drama")
public class AppDramaController extends BaseController
{
    @Autowired
    private IAppDramaService appDramaService;

    /**
     * 查询剧目列表
     */
    @PreAuthorize("@ss.hasPermi('app:drama:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppDrama appDrama)
    {
        startPage();
        List<AppDrama> list = appDramaService.selectList(appDrama);
        return getDataTable(list);
    }

    /**
     * 获取剧目数据汇总
     */
    @PreAuthorize("@ss.hasPermi('app:drama:query')")
    @GetMapping(value = "/getSummary")
    public AjaxResult getSummary(AppDrama appDrama)
    {
        return AjaxResult.success(appDramaService.getSummary(appDrama));
    }

    /**
     * 导出剧目列表
     */
    @PreAuthorize("@ss.hasPermi('app:drama:export')")
    @Log(title = "剧目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppDrama appDrama)
    {
        List<AppDrama> list = appDramaService.selectList(appDrama);
        ExcelUtil<AppDrama> util = new ExcelUtil<>(AppDrama.class);
        util.exportExcel(response, list, "剧目数据");
    }

    /**
     * 获取剧目详细信息
     */
    @PreAuthorize("@ss.hasPermi('app:drama:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(appDramaService.getById(id));
    }

    /**
     * 新增剧目
     */
    @PreAuthorize("@ss.hasPermi('app:drama:add')")
    @Log(title = "剧目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AppDrama appDrama)
    {
        return toAjax(appDramaService.insert(appDrama));
    }

    /**
     * 修改剧目
     */
    @PreAuthorize("@ss.hasPermi('app:drama:edit')")
    @Log(title = "剧目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AppDrama appDrama)
    {
        return toAjax(appDramaService.update(appDrama));
    }

    /**
     * 删除剧目
     */
    @PreAuthorize("@ss.hasPermi('app:drama:remove')")
    @Log(title = "剧目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariableList List<Long> ids)
    {
        return toAjax(appDramaService.deleteByIds(ids));
    }


    /**
     * 查询自定义分析列
     */
    @PreAuthorize("@ss.hasPermi('app:drama:list')")
    @GetMapping("/columns")
    public AjaxResult columns()
    {
        return AjaxResult.success(SummaryColumnUtils.get(AppDrama.class));
    }

    /**
     * 查询自定义分析数据
     */
    @PreAuthorize("@ss.hasPermi('app:drama:list')")
    @RequestMapping(value = "/summary")
    public TableDataInfo summary(@RequestBody SummaryRequest query)
    {
        startPage();
        List<AppDrama> summary = appDramaService.summary(query);
        return getDataTable(summary);
    }

    /**
     * 导出自定义分析数据
     */
    @PreAuthorize("@ss.hasPermi('app:drama:export')")
    @Log(title = "导出自定义分析数据", businessType = BusinessType.EXPORT)
    @PostMapping("/summaryExport")
    public void summaryExport(HttpServletResponse response, @RequestBody SummaryRequest query)
    {
        PageHelper.startPage(1, Integer.MAX_VALUE);
        if (null == query.getQuery()) {
            query.setQuery(new ArrayList<>());
        }
        List<AppDrama> list = appDramaService.summary(query);
        query.getGroupBy().addAll(query.getColumns());
        ExcelUtil<AppDrama> util = new ExcelUtil<>(AppDrama.class, query.getGroupBy());
        util.exportExcel(response, list, "导出数据");
    }



    /**
     * 下载导入模板
     */
    @PreAuthorize("@ss.hasPermi('app:drama:export')")
    @Log(title = "下载导入模板", businessType = BusinessType.EXPORT)
    @PostMapping("/downloadImportModule")
    public void downloadImportModule()
    {
        appDramaService.downloadImportModule();
    }

    /**
     * 导入数据
     */
    @PreAuthorize("@ss.hasPermi('app:drama:export')")
    @Log(title = "导入数据", businessType = BusinessType.EXPORT)
    @PostMapping("/importData")
    public void importData(MultipartFile file)
    {

        appDramaService.importData(file);
    }


    /**
     * 查询自定义分析数据-汇总
     */
    @PreAuthorize("@ss.hasPermi('app:drama:list')")
    @RequestMapping(value = "/allSummary")
    public AjaxResult allSummary(@RequestBody SummaryRequest query)
    {
        PageHelper.startPage(1, 1);
        return AjaxResult.success(appDramaService.allSummary(query));
    }
}
