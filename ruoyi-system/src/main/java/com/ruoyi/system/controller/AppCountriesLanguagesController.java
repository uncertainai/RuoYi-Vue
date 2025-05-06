package com.ruoyi.system.controller;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.domain.SummaryRequest;
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
import com.ruoyi.common.config.PathVariableList;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.AppCountriesLanguages;
import com.ruoyi.system.service.IAppCountriesLanguagesService;
import com.ruoyi.common.utils.SummaryColumnUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 国家语言配置Controller
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@RestController
@RequestMapping("/app/languages")
public class AppCountriesLanguagesController extends BaseController
{
    @Autowired
    private IAppCountriesLanguagesService appCountriesLanguagesService;

    /**
     * 查询国家语言配置列表
     */
    @PreAuthorize("@ss.hasPermi('app:languages:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppCountriesLanguages appCountriesLanguages)
    {
        startPage();
        List<AppCountriesLanguages> list = appCountriesLanguagesService.selectList(appCountriesLanguages);
        return getDataTable(list);
    }

    /**
     * 获取国家语言配置数据汇总
     */
    @PreAuthorize("@ss.hasPermi('app:languages:query')")
    @GetMapping(value = "/getSummary")
    public AjaxResult getSummary(AppCountriesLanguages appCountriesLanguages)
    {
        return AjaxResult.success(appCountriesLanguagesService.getSummary(appCountriesLanguages));
    }

    /**
     * 导出国家语言配置列表
     */
    @PreAuthorize("@ss.hasPermi('app:languages:export')")
    @Log(title = "国家语言配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppCountriesLanguages appCountriesLanguages)
    {
        List<AppCountriesLanguages> list = appCountriesLanguagesService.selectList(appCountriesLanguages);
        ExcelUtil<AppCountriesLanguages> util = new ExcelUtil<>(AppCountriesLanguages.class);
        util.exportExcel(response, list, "国家语言配置数据");
    }

    /**
     * 获取国家语言配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('app:languages:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(appCountriesLanguagesService.getById(id));
    }

    /**
     * 新增国家语言配置
     */
    @PreAuthorize("@ss.hasPermi('app:languages:add')")
    @Log(title = "国家语言配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AppCountriesLanguages appCountriesLanguages)
    {
        return toAjax(appCountriesLanguagesService.insert(appCountriesLanguages));
    }

    /**
     * 修改国家语言配置
     */
    @PreAuthorize("@ss.hasPermi('app:languages:edit')")
    @Log(title = "国家语言配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AppCountriesLanguages appCountriesLanguages)
    {
        return toAjax(appCountriesLanguagesService.update(appCountriesLanguages));
    }

    /**
     * 删除国家语言配置
     */
    @PreAuthorize("@ss.hasPermi('app:languages:remove')")
    @Log(title = "国家语言配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariableList List<Long> ids)
    {
        return toAjax(appCountriesLanguagesService.deleteByIds(ids));
    }


    /**
     * 查询自定义分析列
     */
    @PreAuthorize("@ss.hasPermi('app:languages:list')")
    @GetMapping("/columns")
    public AjaxResult columns()
    {
        return AjaxResult.success(SummaryColumnUtils.get(AppCountriesLanguages.class));
    }

    /**
     * 查询自定义分析数据
     */
    @PreAuthorize("@ss.hasPermi('app:languages:list')")
    @RequestMapping(value = "/summary")
    public TableDataInfo summary(@RequestBody SummaryRequest query)
    {
        startPage();
        List<AppCountriesLanguages> summary = appCountriesLanguagesService.summary(query);
        return getDataTable(summary);
    }

    /**
     * 导出自定义分析数据
     */
    @PreAuthorize("@ss.hasPermi('app:languages:export')")
    @Log(title = "导出自定义分析数据", businessType = BusinessType.EXPORT)
    @PostMapping("/summaryExport")
    public void summaryExport(HttpServletResponse response, @RequestBody SummaryRequest query)
    {
        PageHelper.startPage(1, Integer.MAX_VALUE);
        if (null == query.getQuery()) {
            query.setQuery(new ArrayList<>());
        }
        List<AppCountriesLanguages> list = appCountriesLanguagesService.summary(query);
        query.getGroupBy().addAll(query.getColumns());
        ExcelUtil<AppCountriesLanguages> util = new ExcelUtil<>(AppCountriesLanguages.class, query.getGroupBy());
        util.exportExcel(response, list, "导出数据");
    }



    /**
     * 下载导入模板
     */
    @PreAuthorize("@ss.hasPermi('app:languages:export')")
    @Log(title = "下载导入模板", businessType = BusinessType.EXPORT)
    @PostMapping("/downloadImportModule")
    public void downloadImportModule()
    {
        appCountriesLanguagesService.downloadImportModule();
    }

    /**
     * 导入数据
     */
    @PreAuthorize("@ss.hasPermi('app:languages:export')")
    @Log(title = "导入数据", businessType = BusinessType.EXPORT)
    @PostMapping("/importData")
    public void importData(MultipartFile file)
    {

        appCountriesLanguagesService.importData(file);
    }


    /**
     * 查询自定义分析数据-汇总
     */
    @PreAuthorize("@ss.hasPermi('app:languages:list')")
    @RequestMapping(value = "/allSummary")
    public AjaxResult allSummary(@RequestBody SummaryRequest query)
    {
        PageHelper.startPage(1, 1);
        return AjaxResult.success(appCountriesLanguagesService.allSummary(query));
    }
}
