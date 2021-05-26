package com.xjy.edu.controller;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.HttpStatus;
import com.xjy.edu.domain.vo.EduGroupRequestVo;
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
import com.xjy.edu.domain.EduGroup;
import com.xjy.edu.service.IEduGroupService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 分组Controller
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@Api(value = "/edu/group", description = "分组管理")
@RestController
@RequestMapping("/edu/group")
public class EduGroupController extends BaseController
{
    @Autowired
    private IEduGroupService eduGroupService;

    /**
     * 查询分组列表
     */
    @ApiOperation("查询分组列表")
    //@PreAuthorize("@ss.hasPermi('edu:group:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduGroup eduGroup)
    {
        startPage();
        List<EduGroup> list = eduGroupService.selectEduGroupList(eduGroup);
        return getDataTable(list);
    }

    /**
     * 导出分组列表
     */
    @ApiOperation("导出分组列表")
    //@PreAuthorize("@ss.hasPermi('edu:group:export')")
    @Log(title = "分组", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EduGroup eduGroup)
    {
        List<EduGroup> list = eduGroupService.selectEduGroupList(eduGroup);
        ExcelUtil<EduGroup> util = new ExcelUtil<EduGroup>(EduGroup.class);
        return util.exportExcel(list, "分组数据");
    }

    /**
     * 获取分组详细信息
     */
    @ApiOperation("获取分组详细信息")
    //@PreAuthorize("@ss.hasPermi('edu:group:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(eduGroupService.selectEduGroupById(id));
    }

    /**
     * 新增分组
     */
    @ApiOperation("新增分组")
    //@PreAuthorize("@ss.hasPermi('edu:group:add')")
    @Log(title = "分组", businessType = BusinessType.INSERT)
    @PostMapping
    public TableDataInfo add(@RequestBody List<EduGroupRequestVo> eduGroupRequestVoList)
    {
        //新增分组数据
        EduGroup eduGroup = new EduGroup();
        eduGroupService.insertEduGroup(eduGroupRequestVoList);
        for(int i = 0; i < eduGroupRequestVoList.size(); i++){
            eduGroup.setPartitionId(eduGroupRequestVoList.get(i).getId());
            List<EduGroup> eduGroupList = eduGroupService.selectEduGroupList(eduGroup);
            eduGroupRequestVoList.get(i).setEduGroupList(eduGroupList);
        }
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("新增成功");
        rspData.setRows(eduGroupRequestVoList);
        rspData.setTotal(new PageInfo(eduGroupRequestVoList).getTotal());
        return rspData;
    }

    /**
     * 修改分组
     */
    @ApiOperation("修改分组")
    //@PreAuthorize("@ss.hasPermi('edu:group:edit')")
    @Log(title = "分组", businessType = BusinessType.UPDATE)
    @PutMapping
    public TableDataInfo edit(@RequestBody List<EduGroupRequestVo> eduGroupRequestVoList)
    {
        //修改分组数据
        EduGroup eduGroup = new EduGroup();
        eduGroupService.updateEduGroup(eduGroupRequestVoList);
        for(int i = 0; i < eduGroupRequestVoList.size(); i++){
            eduGroup.setPartitionId(eduGroupRequestVoList.get(i).getId());
            List<EduGroup> eduGroupList = eduGroupService.selectEduGroupList(eduGroup);
            eduGroupRequestVoList.get(i).setEduGroupList(eduGroupList);
        }
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("修改成功");
        rspData.setRows(eduGroupRequestVoList);
        rspData.setTotal(new PageInfo(eduGroupRequestVoList).getTotal());
        return rspData;
    }

    /**
     * 删除分组
     */
    @ApiOperation("删除分组")
    //@PreAuthorize("@ss.hasPermi('edu:group:remove')")
    @Log(title = "分组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eduGroupService.deleteEduGroupByIds(ids));
    }
}
