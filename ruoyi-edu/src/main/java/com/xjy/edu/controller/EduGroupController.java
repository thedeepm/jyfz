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
    @PreAuthorize("@ss.hasPermi('edu:group:list')")
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
    @PreAuthorize("@ss.hasPermi('edu:group:export')")
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
    @PreAuthorize("@ss.hasPermi('edu:group:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(eduGroupService.selectEduGroupById(id));
    }

    /**
     * 新增分组
     */
    @ApiOperation("新增分组")
    @PreAuthorize("@ss.hasPermi('edu:group:add')")
    @Log(title = "分组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<EduGroupRequestVo> eduGroupRequestVoList)
    {
        //新增分组数据
        AjaxResult ajax;
        EduGroup eduGroup = new EduGroup();
        int rows = eduGroupService.insertEduGroup(eduGroupRequestVoList);
        if(rows != 1){
            return rows == 0 ? AjaxResult.error("区间有问题，请重新填写！") : AjaxResult.error("席位数量填写有问题，请重新填写！");
        }
        for(int i = 0; i < eduGroupRequestVoList.size(); i++){
            eduGroup.setPartitionId(eduGroupRequestVoList.get(i).getId());
            List<EduGroup> eduGroupList = eduGroupService.selectEduGroupList(eduGroup);
            eduGroupRequestVoList.get(i).setEduGroupList(eduGroupList);
        }
        ajax = AjaxResult.success();
        ajax.put(AjaxResult.CODE_TAG,HttpStatus.SUCCESS);
        ajax.put(AjaxResult.MSG_TAG,"新增成功");
        ajax.put("rows",eduGroupRequestVoList);
        ajax.put("total",new PageInfo(eduGroupRequestVoList).getTotal());
        return ajax;
    }

    /**
     * 修改分组
     */
    @ApiOperation("修改分组")
    @PreAuthorize("@ss.hasPermi('edu:group:edit')")
    @Log(title = "分组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody List<EduGroupRequestVo> eduGroupRequestVoList)
    {
        //修改分组数据
        AjaxResult ajax;
        EduGroup eduGroup = new EduGroup();
        int rows = eduGroupService.updateEduGroup(eduGroupRequestVoList);
        if(rows == 0){
            return AjaxResult.error("数据校验失败！");
        }
        for(int i = 0; i < eduGroupRequestVoList.size(); i++){
            eduGroup.setPartitionId(eduGroupRequestVoList.get(i).getId());
            List<EduGroup> eduGroupList = eduGroupService.selectEduGroupList(eduGroup);
            eduGroupRequestVoList.get(i).setEduGroupList(eduGroupList);
        }
        ajax = AjaxResult.success();
        ajax.put(AjaxResult.CODE_TAG,HttpStatus.SUCCESS);
        ajax.put(AjaxResult.MSG_TAG,"新增成功");
        ajax.put("rows",eduGroupRequestVoList);
        ajax.put("total",new PageInfo(eduGroupRequestVoList).getTotal());
        return ajax;
    }

    /**
     * 删除分组
     */
    @ApiOperation("删除分组")
    @PreAuthorize("@ss.hasPermi('edu:group:remove')")
    @Log(title = "分组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eduGroupService.deleteEduGroupByIds(ids));
    }
}
