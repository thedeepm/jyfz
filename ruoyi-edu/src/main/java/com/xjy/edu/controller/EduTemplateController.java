package com.xjy.edu.controller;

import java.util.List;
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
import com.xjy.edu.domain.EduTemplate;
import com.xjy.edu.service.IEduTemplateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 模板Controller
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@RestController
@RequestMapping("/edu/template")
public class EduTemplateController extends BaseController
{
    @Autowired
    private IEduTemplateService eduTemplateService;

    /**
     * 查询模板列表
     */
    @PreAuthorize("@ss.hasPermi('edu:template:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduTemplate eduTemplate)
    {
        startPage();
        List<EduTemplate> list = eduTemplateService.selectEduTemplateList(eduTemplate);
        return getDataTable(list);
    }

    /**
     * 导出模板列表
     */
    @PreAuthorize("@ss.hasPermi('edu:template:export')")
    @Log(title = "模板", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EduTemplate eduTemplate)
    {
        List<EduTemplate> list = eduTemplateService.selectEduTemplateList(eduTemplate);
        ExcelUtil<EduTemplate> util = new ExcelUtil<EduTemplate>(EduTemplate.class);
        return util.exportExcel(list, "模板数据");
    }

    /**
     * 获取模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('edu:template:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(eduTemplateService.selectEduTemplateById(id));
    }

    /**
     * 新增模板
     */
    @PreAuthorize("@ss.hasPermi('edu:template:add')")
    @Log(title = "模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduTemplate eduTemplate)
    {
        return toAjax(eduTemplateService.insertEduTemplate(eduTemplate));
    }

    /**
     * 修改模板
     */
    @PreAuthorize("@ss.hasPermi('edu:template:edit')")
    @Log(title = "模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduTemplate eduTemplate)
    {
        return toAjax(eduTemplateService.updateEduTemplate(eduTemplate));
    }

    /**
     * 删除模板
     */
    @PreAuthorize("@ss.hasPermi('edu:template:remove')")
    @Log(title = "模板", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eduTemplateService.deleteEduTemplateByIds(ids));
    }
}
