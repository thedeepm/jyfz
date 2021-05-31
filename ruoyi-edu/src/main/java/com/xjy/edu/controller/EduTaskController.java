package com.xjy.edu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.service.ISysUserService;
import com.xjy.edu.domain.*;
import com.xjy.edu.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务Controller
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@Api(value = "/edu/case", description = "任务管理")
@RestController
@RequestMapping("/edu/task")
public class EduTaskController extends BaseController
{
    @Autowired
    private IEduTaskService eduTaskService;

    @Autowired
    private IEduGroupService eduGroupService;

    @Autowired
    private IEduPartitionService eduPartitionService;

    @Autowired
    private IEduSeatService eduSeatService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IEduPersonInfoService eduPersonInfoService;

    /**
     * 查询任务列表
     */
    @ApiOperation("获取任务列表")
    @PreAuthorize("@ss.hasPermi('edu:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduTask eduTask)
    {
        startPage();
        List<EduTask> list = eduTaskService.selectEduTaskList(eduTask);
        List<Map<String,Object>> response = new ArrayList<>();
        Map<String,Object> map;
        EduGroup group;
        EduPartition partition;
        EduSeat seat;
        for (int i = 0;i < list.size();i++){
            eduTask = list.get(i);
            group = eduGroupService.selectEduGroupById(eduTask.getGroupId());
            partition = eduPartitionService.selectEduPartitionById(eduTask.getPartitionId());
            seat = eduSeatService.selectEduSeatById(eduTask.getSeatId());
            map = new HashMap<>();
            map.put("group", group);
            map.put("partition", partition);
            map.put("seat", seat);
            map.put("eduTask", eduTask);
            response.add(map);
        }
        return getDataTable(response);
    }

    /**
     * 导出任务列表
     */
    @ApiOperation("导出任务列表")
    @PreAuthorize("@ss.hasPermi('edu:task:export')")
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
    @ApiOperation("获取任务详细信息")
    @PreAuthorize("@ss.hasPermi('edu:task:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(eduTaskService.selectEduTaskById(id));
    }

    /**
     * 新增任务
     */
    @ApiOperation("新增任务")
    @PreAuthorize("@ss.hasPermi('edu:task:add')")
    @Log(title = "任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduTask eduTask)
    {
        //校验seatid是否绑定
        AjaxResult ajax = AjaxResult.success();
        EduSeat seat = eduSeatService.selectEduSeatById(eduTask.getSeatId());
        if(seat.getOccupied()){
            return AjaxResult.error("seat已被占用");
        }
        eduTask = eduTaskService.insertEduTask(eduTask, ajax);
        EduGroup group = eduGroupService.selectEduGroupById(eduTask.getGroupId());
        EduPartition partition = eduPartitionService.selectEduPartitionById(eduTask.getPartitionId());
        seat = eduSeatService.selectEduSeatById(eduTask.getSeatId());
        ajax.put(AjaxResult.CODE_TAG, HttpStatus.SUCCESS);
        ajax.put(AjaxResult.MSG_TAG,"新增成功");
        ajax.put("group",group);
        ajax.put("partition",partition);
        ajax.put("seat",seat);
        ajax.put("eduTask",eduTask);
        return ajax;
    }

    /**
     * 修改任务
     */
    @ApiOperation("修改任务")
    @PreAuthorize("@ss.hasPermi('edu:task:edit')")
    @Log(title = "任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduTask eduTask)
    {
        return toAjax(eduTaskService.updateEduTask(eduTask));
    }

    /**
     * 删除任务
     */
    @ApiOperation("删除任务")
    @PreAuthorize("@ss.hasPermi('edu:task:remove')")
    @Log(title = "任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eduTaskService.deleteEduTaskByIds(ids));
    }
}
