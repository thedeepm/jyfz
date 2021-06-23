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
import com.xjy.edu.domain.EduCaseTask;
import com.xjy.edu.service.IEduCaseTaskService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 案例任务关联Controller
 * 
 * @author wuzh
 * @date 2021-05-29
 */
@RestController
@RequestMapping("/edu/caseTask")
public class EduCaseTaskController extends BaseController
{
    @Autowired
    private IEduCaseTaskService eduCaseTaskService;

    /**
     * 查询案例任务关联列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:caseTask:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduCaseTask eduCaseTask)
    {
        startPage();
        List<EduCaseTask> list = eduCaseTaskService.selectEduCaseTaskList(eduCaseTask);
        return getDataTable(list);
    }

    /**
     * 导出案例任务关联列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:caseTask:export')")
    @Log(title = "案例任务关联", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EduCaseTask eduCaseTask)
    {
        List<EduCaseTask> list = eduCaseTaskService.selectEduCaseTaskList(eduCaseTask);
        ExcelUtil<EduCaseTask> util = new ExcelUtil<EduCaseTask>(EduCaseTask.class);
        return util.exportExcel(list, "案例任务关联数据");
    }

    /**
     * 获取案例任务关联详细信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:caseTask:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(eduCaseTaskService.selectEduCaseTaskById(id));
    }

    /**
     * 新增案例任务关联
     */
    //@PreAuthorize("@ss.hasPermi('edu:caseTask:add')")
    @Log(title = "案例任务关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduCaseTask eduCaseTask)
    {
        return toAjax(eduCaseTaskService.insertEduCaseTask(eduCaseTask));
    }

    /**
     * 修改案例任务关联
     */
    //@PreAuthorize("@ss.hasPermi('edu:caseTask:edit')")
    @Log(title = "案例任务关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduCaseTask eduCaseTask)
    {
        return toAjax(eduCaseTaskService.updateEduCaseTask(eduCaseTask));
    }

    /**
     * 删除案例任务关联
     */
    //@PreAuthorize("@ss.hasPermi('edu:caseTask:remove')")
    @Log(title = "案例任务关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eduCaseTaskService.deleteEduCaseTaskByIds(ids));
    }
}
