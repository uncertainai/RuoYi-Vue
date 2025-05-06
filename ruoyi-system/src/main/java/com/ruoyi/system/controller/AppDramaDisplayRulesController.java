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
import com.ruoyi.system.domain.AppDramaDisplayRules;
import com.ruoyi.system.service.IAppDramaDisplayRulesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 剧目显示规则Controller
 * 
 * @author ruoyi
 * @date 2025-05-06
 */
@RestController
@RequestMapping("/app/drama_rules")
public class AppDramaDisplayRulesController extends BaseController
{
    @Autowired
    private IAppDramaDisplayRulesService appDramaDisplayRulesService;

    /**
     * 查询剧目显示规则列表
     */
    @PreAuthorize("@ss.hasPermi('app:drama_rules:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppDramaDisplayRules appDramaDisplayRules)
    {
        startPage();
        List<AppDramaDisplayRules> list = appDramaDisplayRulesService.selectList(appDramaDisplayRules);
        return getDataTable(list);
    }

    /**
     * 获取剧目显示规则数据汇总
     */
    @PreAuthorize("@ss.hasPermi('app:drama_rules:query')")
    @GetMapping(value = "/getSummary")
    public AjaxResult getSummary(AppDramaDisplayRules appDramaDisplayRules)
    {
        return AjaxResult.success(appDramaDisplayRulesService.getSummary(appDramaDisplayRules));
    }

    /**
     * 导出剧目显示规则列表
     */
    @PreAuthorize("@ss.hasPermi('app:drama_rules:export')")
    @Log(title = "剧目显示规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppDramaDisplayRules appDramaDisplayRules)
    {
        List<AppDramaDisplayRules> list = appDramaDisplayRulesService.selectList(appDramaDisplayRules);
        ExcelUtil<AppDramaDisplayRules> util = new ExcelUtil<>(AppDramaDisplayRules.class);
        util.exportExcel(response, list, "剧目显示规则数据");
    }

    /**
     * 获取剧目显示规则详细信息
     */
    @PreAuthorize("@ss.hasPermi('app:drama_rules:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(appDramaDisplayRulesService.getById(id));
    }

    /**
     * 新增剧目显示规则
     */
    @PreAuthorize("@ss.hasPermi('app:drama_rules:add')")
    @Log(title = "剧目显示规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AppDramaDisplayRules appDramaDisplayRules)
    {
        return toAjax(appDramaDisplayRulesService.insert(appDramaDisplayRules));
    }

    /**
     * 修改剧目显示规则
     */
    @PreAuthorize("@ss.hasPermi('app:drama_rules:edit')")
    @Log(title = "剧目显示规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AppDramaDisplayRules appDramaDisplayRules)
    {
        return toAjax(appDramaDisplayRulesService.update(appDramaDisplayRules));
    }

    /**
     * 删除剧目显示规则
     */
    @PreAuthorize("@ss.hasPermi('app:drama_rules:remove')")
    @Log(title = "剧目显示规则", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariableList List<Long> ids)
    {
        return toAjax(appDramaDisplayRulesService.deleteByIds(ids));
    }


    /**
     * 查询自定义分析列
     */
    @PreAuthorize("@ss.hasPermi('app:drama_rules:list')")
    @GetMapping("/columns")
    public AjaxResult columns()
    {
        return AjaxResult.success(SummaryColumnUtils.get(AppDramaDisplayRules.class));
    }

    /**
     * 查询自定义分析数据
     */
    @PreAuthorize("@ss.hasPermi('app:drama_rules:list')")
    @RequestMapping(value = "/summary")
    public TableDataInfo summary(@RequestBody SummaryRequest query)
    {
        startPage();
        List<AppDramaDisplayRules> summary = appDramaDisplayRulesService.summary(query);
        return getDataTable(summary);
    }

    /**
     * 导出自定义分析数据
     */
    @PreAuthorize("@ss.hasPermi('app:drama_rules:export')")
    @Log(title = "导出自定义分析数据", businessType = BusinessType.EXPORT)
    @PostMapping("/summaryExport")
    public void summaryExport(HttpServletResponse response, @RequestBody SummaryRequest query)
    {
        PageHelper.startPage(1, Integer.MAX_VALUE);
        if (null == query.getQuery()) {
            query.setQuery(new ArrayList<>());
        }
        List<AppDramaDisplayRules> list = appDramaDisplayRulesService.summary(query);
        query.getGroupBy().addAll(query.getColumns());
        ExcelUtil<AppDramaDisplayRules> util = new ExcelUtil<>(AppDramaDisplayRules.class, query.getGroupBy());
        util.exportExcel(response, list, "导出数据");
    }



    /**
     * 下载导入模板
     */
    @PreAuthorize("@ss.hasPermi('app:drama_rules:export')")
    @Log(title = "下载导入模板", businessType = BusinessType.EXPORT)
    @PostMapping("/downloadImportModule")
    public void downloadImportModule()
    {
        appDramaDisplayRulesService.downloadImportModule();
    }

    /**
     * 导入数据
     */
    @PreAuthorize("@ss.hasPermi('app:drama_rules:export')")
    @Log(title = "导入数据", businessType = BusinessType.EXPORT)
    @PostMapping("/importData")
    public void importData(MultipartFile file)
    {

        appDramaDisplayRulesService.importData(file);
    }


    /**
     * 查询自定义分析数据-汇总
     */
    @PreAuthorize("@ss.hasPermi('app:drama_rules:list')")
    @RequestMapping(value = "/allSummary")
    public AjaxResult allSummary(@RequestBody SummaryRequest query)
    {
        PageHelper.startPage(1, 1);
        return AjaxResult.success(appDramaDisplayRulesService.allSummary(query));
    }
}
