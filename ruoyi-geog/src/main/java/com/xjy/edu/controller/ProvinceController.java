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
import com.xjy.edu.domain.Province;
import com.xjy.edu.service.IProvinceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 国内主要省份信息Controller
 * 
 * @author wuzh
 * @date 2021-05-31
 */
@RestController
@RequestMapping("/geog/province")
public class ProvinceController extends BaseController
{
    @Autowired
    private IProvinceService provinceService;

    /**
     * 查询国内主要省份信息列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:province:list')")
    @GetMapping("/list")
    public TableDataInfo list(Province province)
    {
        startPage();
        List<Province> list = provinceService.selectProvinceList(province);
        return getDataTable(list);
    }

    /**
     * 导出国内主要省份信息列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:province:export')")
    @Log(title = "国内主要省份信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Province province)
    {
        List<Province> list = provinceService.selectProvinceList(province);
        ExcelUtil<Province> util = new ExcelUtil<Province>(Province.class);
        return util.exportExcel(list, "国内主要省份信息数据");
    }

    /**
     * 获取国内主要省份信息详细信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:province:query')")
    @GetMapping(value = "/{provinceId}")
    public AjaxResult getInfo(@PathVariable("provinceId") Long provinceId)
    {
        return AjaxResult.success(provinceService.selectProvinceById(provinceId));
    }

    /**
     * 新增国内主要省份信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:province:add')")
    @Log(title = "国内主要省份信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Province province)
    {
        return toAjax(provinceService.insertProvince(province));
    }

    /**
     * 修改国内主要省份信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:province:edit')")
    @Log(title = "国内主要省份信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Province province)
    {
        return toAjax(provinceService.updateProvince(province));
    }

    /**
     * 删除国内主要省份信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:province:remove')")
    @Log(title = "国内主要省份信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{provinceIds}")
    public AjaxResult remove(@PathVariable Long[] provinceIds)
    {
        return toAjax(provinceService.deleteProvinceByIds(provinceIds));
    }
}
