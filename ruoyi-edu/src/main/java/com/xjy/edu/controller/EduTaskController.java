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
import com.xjy.edu.domain.EduTask;
import com.xjy.edu.service.IEduTaskService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务Controller
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@RestController
@RequestMapping("/edu/task")
public class EduTaskController extends BaseController
{
    @Autowired
    private IEduTaskService eduTaskService;

    /**
     * 查询任务列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduTask eduTask)
    {
        startPage();
        List<EduTask> list = eduTaskService.selectEduTaskList(eduTask);
        return getDataTable(list);
    }

    /**
     * 导出任务列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:task:export')")
    @Log(title = "任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EduTask eduTask)
    {
        List<EduTask> list = eduTaskService.selectEduTaskList(eduTask);
        ExcelUtil<EduTask> util = new ExcelUtil<EduTask>(EduTask.class);
        return util.exportExcel(list, "任务数据");
    }

    /**
     * 获取任务详细信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:task:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(eduTaskService.selectEduTaskById(id));
    }

    /**
     * 新增任务
     */
    //@PreAuthorize("@ss.hasPermi('edu:task:add')")
    @Log(title = "任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduTask eduTask)
    {
        return toAjax(eduTaskService.insertEduTask(eduTask));
    }

    /**
     * 修改任务
     */
    //@PreAuthorize("@ss.hasPermi('edu:task:edit')")
    @Log(title = "任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduTask eduTask)
    {
        return toAjax(eduTaskService.updateEduTask(eduTask));
    }

    /**
     * 删除任务
     */
    //@PreAuthorize("@ss.hasPermi('edu:task:remove')")
    @Log(title = "任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eduTaskService.deleteEduTaskByIds(ids));
    }
}
