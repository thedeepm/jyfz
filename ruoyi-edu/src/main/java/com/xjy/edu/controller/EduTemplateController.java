package com.xjy.edu.controller;

import java.lang.reflect.Array;
import java.util.*;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.HttpStatus;
import com.xjy.edu.domain.EduPartition;
import com.xjy.edu.domain.EduSeat;
import com.xjy.edu.domain.vo.EduTemplateRequestVo;
import com.xjy.edu.service.IEduGroupService;
import com.xjy.edu.service.IEduPartitionService;
import com.xjy.edu.service.IEduSeatService;
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
@Api(value = "/edu/case", description = "模板管理")
@RestController
@RequestMapping("/edu/template")
public class EduTemplateController extends BaseController
{
    @Autowired
    private IEduTemplateService eduTemplateService;

    @Autowired
    private IEduPartitionService eduPartitionService;

    @Autowired
    private IEduSeatService eduSeatService;
    /**
     * 查询模板列表
     */
    @ApiOperation("获取模板列表")
    //@PreAuthorize("@ss.hasPermi('edu:template:list')")
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
    @ApiOperation("导出模板")
    //@PreAuthorize("@ss.hasPermi('edu:template:export')")
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
    @ApiOperation("获取模板详细信息")
    //@PreAuthorize("@ss.hasPermi('edu:template:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(eduTemplateService.selectEduTemplateById(id));
    }

    /**
     * 新增模板
     */
    @ApiOperation("新增模板")
    //@PreAuthorize("@ss.hasPermi('edu:template:add')")
    @Log(title = "模板", businessType = BusinessType.INSERT)
    @PostMapping
    public Map<String,Object> add(@RequestBody EduTemplateRequestVo eduTemplateVo)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        List<EduPartition> eduPartitionList = new ArrayList<>();
        if(eduTemplateService.insertEduTemplate(eduTemplateVo) != 0){
            EduPartition eduPartition = new EduPartition();
            EduTemplate eduTemplate = eduTemplateService.getLastEduTemplate();
            eduPartition.setTemplateId(eduTemplate.getId());
            eduPartitionList = eduPartitionService.selectEduPartitionList(eduPartition);
            map.put("templateId", eduTemplate.getId());
        }
        map.put("code", HttpStatus.SUCCESS);
        map.put("eduPartitionList",eduPartitionList);
        map.put("total", new PageInfo(eduPartitionList).getTotal());
        map.put("message", "新增成功");
        return map;
    }

    /**
     * 修改模板
     */
    @ApiOperation("修改模板")
    //@PreAuthorize("@ss.hasPermi('edu:template:edit')")
    @Log(title = "模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduTemplateRequestVo eduTemplateVo)
    {
        AjaxResult ajax = AjaxResult.success();
//        Map<String,Object> map = new HashMap<String,Object>();
        EduTemplate eduTemplate;
        List<EduPartition> eduPartitionList = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        //eduTemplate = eduTemplateVo.getTemplate();
        EduPartition eduPartition = new EduPartition();
        eduPartition.setTemplateId(eduTemplateVo.getId());
        if(eduTemplateVo.getPartitionsList() != null && eduTemplateVo.getPartitionsList().size()>0){
            eduPartitionList = eduPartitionService.selectEduPartitionList(eduPartition);
            for (int i = 0;i < eduPartitionList.size();i++){
                ids.add(eduPartitionList.get(i).getId());
            }
            //delete partition that relate to the template
            if(ids.size() > 0){
                eduPartitionService.deleteEduPartitionByIds(ids.toArray(new Long[ids.size()]));
            }
        }
        if(eduTemplateService.updateEduTemplate(eduTemplateVo) != 0){
            eduPartition = new EduPartition();
            eduTemplate = eduTemplateService.selectEduTemplateById(eduTemplateVo.getId());
            eduPartition.setTemplateId(eduTemplate.getId());
            eduPartitionList = eduPartitionService.selectEduPartitionList(eduPartition);
            ajax.put("templateId",eduTemplate.getId());
            ajax.put("template",eduTemplate);
        }
        ajax.put(AjaxResult.CODE_TAG,HttpStatus.SUCCESS);
        ajax.put(AjaxResult.MSG_TAG,"修改成功");
        ajax.put("eduPartitionList",eduPartitionList);
        ajax.put("total",new PageInfo(eduPartitionList).getTotal());
        return ajax;
    }

    /**
     * 删除模板
     */
    @ApiOperation("删除模板")
    //@PreAuthorize("@ss.hasPermi('edu:template:remove')")
    @Log(title = "模板", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eduTemplateService.deleteEduTemplateByIds(ids));
    }
}
