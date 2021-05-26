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
import com.xjy.edu.domain.EduFlowRefrence;
import com.xjy.edu.service.IEduFlowRefrenceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 流程图Controller
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@RestController
@RequestMapping("/edu/refrence")
public class EduFlowRefrenceController extends BaseController
{
    @Autowired
    private IEduFlowRefrenceService eduFlowRefrenceService;

    /**
     * 查询流程图列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:refrence:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduFlowRefrence eduFlowRefrence)
    {
        startPage();
        List<EduFlowRefrence> list = eduFlowRefrenceService.selectEduFlowRefrenceList(eduFlowRefrence);
        return getDataTable(list);
    }

    /**
     * 导出流程图列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:refrence:export')")
    @Log(title = "流程图", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EduFlowRefrence eduFlowRefrence)
    {
        List<EduFlowRefrence> list = eduFlowRefrenceService.selectEduFlowRefrenceList(eduFlowRefrence);
        ExcelUtil<EduFlowRefrence> util = new ExcelUtil<EduFlowRefrence>(EduFlowRefrence.class);
        return util.exportExcel(list, "流程图数据");
    }

    /**
     * 获取流程图详细信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:refrence:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(eduFlowRefrenceService.selectEduFlowRefrenceById(id));
    }

    /**
     * 新增流程图
     */
    //@PreAuthorize("@ss.hasPermi('edu:refrence:add')")
    @Log(title = "流程图", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduFlowRefrence eduFlowRefrence)
    {
        return toAjax(eduFlowRefrenceService.insertEduFlowRefrence(eduFlowRefrence));
    }

    /**
     * 修改流程图
     */
    //@PreAuthorize("@ss.hasPermi('edu:refrence:edit')")
    @Log(title = "流程图", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduFlowRefrence eduFlowRefrence)
    {
        return toAjax(eduFlowRefrenceService.updateEduFlowRefrence(eduFlowRefrence));
    }

    /**
     * 删除流程图
     */
    //@PreAuthorize("@ss.hasPermi('edu:refrence:remove')")
    @Log(title = "流程图", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eduFlowRefrenceService.deleteEduFlowRefrenceByIds(ids));
    }
}
