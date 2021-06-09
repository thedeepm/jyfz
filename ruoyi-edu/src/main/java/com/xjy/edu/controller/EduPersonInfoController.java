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
import com.xjy.edu.domain.EduPersonInfo;
import com.xjy.edu.service.IEduPersonInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 人员信息Controller
 * 
 * @author wuzh
 * @date 2021-05-28
 */
@RestController
@RequestMapping("/edu/personInfo")
public class EduPersonInfoController extends BaseController
{
    @Autowired
    private IEduPersonInfoService eduPersonInfoService;

    /**
     * 查询人员信息列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:personInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduPersonInfo eduPersonInfo)
    {
        startPage();
        List<EduPersonInfo> list = eduPersonInfoService.selectEduPersonInfoList(eduPersonInfo);
        return getDataTable(list);
    }

    /**
     * 导出人员信息列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:personInfo:export')")
    @Log(title = "人员信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EduPersonInfo eduPersonInfo)
    {
        List<EduPersonInfo> list = eduPersonInfoService.selectEduPersonInfoList(eduPersonInfo);
        ExcelUtil<EduPersonInfo> util = new ExcelUtil<EduPersonInfo>(EduPersonInfo.class);
        return util.exportExcel(list, "人员信息数据");
    }

    /**
     * 获取人员信息详细信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:personInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(eduPersonInfoService.selectEduPersonInfoById(id));
    }

    /**
     * 新增人员信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:personInfo:add')")
    @Log(title = "人员信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduPersonInfo eduPersonInfo)
    {
        eduPersonInfo.setSeatId(-1L);
        return toAjax(eduPersonInfoService.insertEduPersonInfo(eduPersonInfo));
    }

    /**
     * 修改人员信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:personInfo:edit')")
    @Log(title = "人员信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduPersonInfo eduPersonInfo)
    {
        return toAjax(eduPersonInfoService.updateEduPersonInfo(eduPersonInfo));
    }

    /**
     * 删除人员信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:personInfo:remove')")
    @Log(title = "人员信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eduPersonInfoService.deleteEduPersonInfoByIds(ids));
    }
}
