package com.xjy.edu.controller;

import java.util.List;

import com.ruoyi.system.domain.SysFileInfo;
import com.xjy.edu.domain.EduCaseTask;
import com.xjy.edu.domain.vo.EduFlowRequestVo;
import com.xjy.edu.service.IEduCaseTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
import com.xjy.edu.domain.EduFlow;
import com.xjy.edu.service.IEduFlowService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 流程Controller
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@RestController
@RequestMapping("/edu/flow")
@Api(value = "/edu/flow", description = "流程管理")
public class EduFlowController extends BaseController
{
    @Autowired
    private IEduFlowService eduFlowService;
    @Autowired
    private IEduCaseTaskService eduCaseTaskService;


    /**
     * 查询流程列表
     */
    @ApiOperation("查询流程列表")
    //@PreAuthorize("@ss.hasPermi('edu:flow:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduFlow eduFlow)
    {
        startPage();
        List<EduFlow> list = eduFlowService.selectEduFlowList(eduFlow);
        return getDataTable(list);
    }

    /**
     * 查询流程信息
     */
    @ApiOperation("查询流程文件信息")
    //@PreAuthorize("@ss.hasPermi('edu:flow:info')")
    @GetMapping("/info")
    public AjaxResult getFlowInfo(EduCaseTask caseTask)
    {
        SysFileInfo sysFileInfo =  eduCaseTaskService.selectFlowFileInfo(caseTask);
        return AjaxResult.success(sysFileInfo);
    }

    /**
     * 导出流程列表
     */
    @ApiOperation("导出流程列表")
    //@PreAuthorize("@ss.hasPermi('edu:flow:export')")
    @Log(title = "流程", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EduFlow eduFlow)
    {
        List<EduFlow> list = eduFlowService.selectEduFlowList(eduFlow);
        ExcelUtil<EduFlow> util = new ExcelUtil<EduFlow>(EduFlow.class);
        return util.exportExcel(list, "流程数据");
    }

    /**
     * 获取流程详细信息
     */
    @ApiOperation("获取流程详细信息")
    //@PreAuthorize("@ss.hasPermi('edu:flow:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(eduFlowService.selectEduFlowById(id));
    }

    /**
     * 新增流程
     */
    @ApiOperation("新增流程")
    //@PreAuthorize("@ss.hasPermi('edu:flow:add')")
    @Log(title = "流程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduFlowRequestVo eduFlowRequestVo)
    {
        AjaxResult ajax = AjaxResult.success();
        ajax.put(AjaxResult.MSG_TAG, "新增成功！");
        ajax.put(AjaxResult.DATA_TAG, eduFlowService.insertEduFlow(eduFlowRequestVo)) ;
        return ajax;
    }

    /**
     * 修改流程
     */
    @ApiOperation("修改流程")
    //@PreAuthorize("@ss.hasPermi('edu:flow:edit')")
    @Log(title = "流程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody EduFlow eduFlow)
    {
        return toAjax(eduFlowService.updateEduFlow(eduFlow));
    }

    /**
     * 删除流程
     */
    @ApiOperation("删除流程")
    //@PreAuthorize("@ss.hasPermi('edu:flow:remove')")
    @Log(title = "流程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eduFlowService.deleteEduFlowByIds(ids));
    }
}
