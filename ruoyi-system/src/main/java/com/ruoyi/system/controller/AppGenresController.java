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
import com.ruoyi.system.domain.AppGenres;
import com.ruoyi.system.service.IAppGenresService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 剧目类型Controller
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@RestController
@RequestMapping("/app/genres")
public class AppGenresController extends BaseController
{
    @Autowired
    private IAppGenresService appGenresService;

    /**
     * 查询剧目类型列表
     */
    @PreAuthorize("@ss.hasPermi('app:genres:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppGenres appGenres)
    {
        startPage();
        List<AppGenres> list = appGenresService.selectList(appGenres);
        return getDataTable(list);
    }

    /**
     * 获取剧目类型数据汇总
     */
    @PreAuthorize("@ss.hasPermi('app:genres:query')")
    @GetMapping(value = "/getSummary")
    public AjaxResult getSummary(AppGenres appGenres)
    {
        return AjaxResult.success(appGenresService.getSummary(appGenres));
    }

    /**
     * 导出剧目类型列表
     */
    @PreAuthorize("@ss.hasPermi('app:genres:export')")
    @Log(title = "剧目类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppGenres appGenres)
    {
        List<AppGenres> list = appGenresService.selectList(appGenres);
        ExcelUtil<AppGenres> util = new ExcelUtil<>(AppGenres.class);
        util.exportExcel(response, list, "剧目类型数据");
    }

    /**
     * 获取剧目类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('app:genres:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(appGenresService.getById(id));
    }

    /**
     * 新增剧目类型
     */
    @PreAuthorize("@ss.hasPermi('app:genres:add')")
    @Log(title = "剧目类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AppGenres appGenres)
    {
        return toAjax(appGenresService.insert(appGenres));
    }

    /**
     * 修改剧目类型
     */
    @PreAuthorize("@ss.hasPermi('app:genres:edit')")
    @Log(title = "剧目类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AppGenres appGenres)
    {
        return toAjax(appGenresService.update(appGenres));
    }

    /**
     * 删除剧目类型
     */
    @PreAuthorize("@ss.hasPermi('app:genres:remove')")
    @Log(title = "剧目类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariableList List<Long> ids)
    {
        return toAjax(appGenresService.deleteByIds(ids));
    }


    /**
     * 查询自定义分析列
     */
    @PreAuthorize("@ss.hasPermi('app:genres:list')")
    @GetMapping("/columns")
    public AjaxResult columns()
    {
        return AjaxResult.success(SummaryColumnUtils.get(AppGenres.class));
    }

    /**
     * 查询自定义分析数据
     */
    @PreAuthorize("@ss.hasPermi('app:genres:list')")
    @RequestMapping(value = "/summary")
    public TableDataInfo summary(@RequestBody SummaryRequest query)
    {
        startPage();
        List<AppGenres> summary = appGenresService.summary(query);
        return getDataTable(summary);
    }

    /**
     * 导出自定义分析数据
     */
    @PreAuthorize("@ss.hasPermi('app:genres:export')")
    @Log(title = "导出自定义分析数据", businessType = BusinessType.EXPORT)
    @PostMapping("/summaryExport")
    public void summaryExport(HttpServletResponse response, @RequestBody SummaryRequest query)
    {
        PageHelper.startPage(1, Integer.MAX_VALUE);
        if (null == query.getQuery()) {
            query.setQuery(new ArrayList<>());
        }
        List<AppGenres> list = appGenresService.summary(query);
        query.getGroupBy().addAll(query.getColumns());
        ExcelUtil<AppGenres> util = new ExcelUtil<>(AppGenres.class, query.getGroupBy());
        util.exportExcel(response, list, "导出数据");
    }



    /**
     * 下载导入模板
     */
    @PreAuthorize("@ss.hasPermi('app:genres:export')")
    @Log(title = "下载导入模板", businessType = BusinessType.EXPORT)
    @PostMapping("/downloadImportModule")
    public void downloadImportModule()
    {
        appGenresService.downloadImportModule();
    }

    /**
     * 导入数据
     */
    @PreAuthorize("@ss.hasPermi('app:genres:export')")
    @Log(title = "导入数据", businessType = BusinessType.EXPORT)
    @PostMapping("/importData")
    public void importData(MultipartFile file)
    {

        appGenresService.importData(file);
    }


    /**
     * 查询自定义分析数据-汇总
     */
    @PreAuthorize("@ss.hasPermi('app:genres:list')")
    @RequestMapping(value = "/allSummary")
    public AjaxResult allSummary(@RequestBody SummaryRequest query)
    {
        PageHelper.startPage(1, 1);
        return AjaxResult.success(appGenresService.allSummary(query));
    }
}
