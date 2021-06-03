package com.xjy.edu.controller;

import java.util.ArrayList;
import java.util.List;

import com.xjy.edu.domain.EduCaseTask;
import com.xjy.edu.domain.EduPersonInfo;
import com.xjy.edu.domain.EduTask;
import com.xjy.edu.service.IEduCaseTaskService;
import com.xjy.edu.service.IEduPersonInfoService;
import com.xjy.edu.service.IEduTaskService;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Case;
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
import com.xjy.edu.domain.EduCase;
import com.xjy.edu.service.IEduCaseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.servlet.ModelAndView;

/**
 * 案例Controller
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@Api(value = "/edu/case", description = "案例管理")
@RestController
@RequestMapping("/edu/case")
public class EduCaseController extends BaseController
{
    @Autowired
    private IEduCaseService eduCaseService;

    @Autowired
    private IEduPersonInfoService eduPersonInfoService;

    @Autowired
    private IEduTaskService eduTaskService;

    @Autowired
    private IEduCaseTaskService eduCaseTaskService;

    /**
     * 查询案例列表
     */
    @ApiOperation("获取案例列表")
    @PreAuthorize("@ss.hasPermi('edu:case:list')")
    @GetMapping("/list")
    public TableDataInfo list(@ApiParam(value = "查询案例列表", required = true) EduCase eduCase)
    {
        startPage();
        List<EduCase> list = eduCaseService.selectEduCaseList(eduCase);
        return getDataTable(list);
    }

    /**
     * 查询我的案例列表
     */
    @ApiOperation("查询我的案例列表")
    @PreAuthorize("@ss.hasPermi('edu:case:myList')")
    @GetMapping("/myList")
    public TableDataInfo myList(@ApiParam(value = "查询我的案例列表", required = true) Long userId)
    {
        startPage();
        EduPersonInfo eduPersonInfo = new EduPersonInfo();
        EduTask eduTask = new EduTask();
        EduCaseTask eduCaseTask = new EduCaseTask();
        eduPersonInfo.setUserId(userId);
        List<EduPersonInfo> personInfoList = eduPersonInfoService.selectEduPersonInfoList(eduPersonInfo);
        if(personInfoList != null && !personInfoList.isEmpty()){
            eduPersonInfo = personInfoList.get(0);
        }
        eduTask.setSeatId(eduPersonInfo.getSeatId());
        List<EduTask> eduTaskList = eduTaskService.selectEduTaskList(eduTask);
        if(eduTaskList != null && !eduTaskList.isEmpty()){
            eduTask = eduTaskList.get(0);
        }
        eduCaseTask.setTaskId(eduTask.getId());
        List<EduCaseTask> eduCaseTaskList = eduCaseTaskService.selectEduCaseTaskList(eduCaseTask);
        List<Long> caseIds = new ArrayList<>();
        if(eduCaseTaskList != null && !eduCaseTaskList.isEmpty()){
            for (EduCaseTask caseTask: eduCaseTaskList) {
                caseIds.add(caseTask.getCaseId());
            }
        }
        List<EduCase> caseList = new ArrayList<>();
        for ( Long id: caseIds) {
           caseList.add(eduCaseService.selectEduCaseById(id));
        }
        return getDataTable(caseList);
    }

    /**
     * 导出案例列表
     */
    @PreAuthorize("@ss.hasPermi('edu:case:export')")
    @Log(title = "案例", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(@ApiParam(value = "查询案例列表", required = true) EduCase eduCase)
    {
        List<EduCase> list = eduCaseService.selectEduCaseList(eduCase);
        ExcelUtil<EduCase> util = new ExcelUtil<EduCase>(EduCase.class);
        return util.exportExcel(list, "案例数据");
    }

    /**
     * 获取案例详细信息
     */
    @ApiOperation("获取案例详细信息")
    @PreAuthorize("@ss.hasPermi('edu:case:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@ApiParam(value = "案例id", required = true) @PathVariable("id") Long id)
    {
        return AjaxResult.success(eduCaseService.selectEduCaseById(id));
    }

    /**
     * 新增案例
     */
    @ApiOperation("新增案例")
    @PreAuthorize("@ss.hasPermi('edu:case:add')")
    @Log(title = "案例", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduCase eduCase)
    {
        return toAjax(eduCaseService.insertEduCase(eduCase));
    }

    /**
     * 修改案例
     */
    @ApiOperation("修改案例")
    @PreAuthorize("@ss.hasPermi('edu:case:edit')")
    @Log(title = "案例", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduCase eduCase)
    {
        return toAjax(eduCaseService.updateEduCase(eduCase));
    }

    /**
     * 删除案例
     */
    @ApiOperation("删除案例")
    @PreAuthorize("@ss.hasPermi('edu:case:remove')")
    @Log(title = "案例", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eduCaseService.deleteEduCaseByIds(ids));
    }
}
