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
import com.xjy.edu.domain.Country;
import com.xjy.edu.service.ICountryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 我国周边国家信息Controller
 * 
 * @author wuzh
 * @date 2021-05-31
 */
@RestController
@RequestMapping("/geog/country")
public class CountryController extends BaseController
{
    @Autowired
    private ICountryService countryService;

    /**
     * 查询我国周边国家信息列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:country:list')")
    @GetMapping("/list")
    public TableDataInfo list(Country country)
    {
        startPage();
        List<Country> list = countryService.selectCountryList(country);
        return getDataTable(list);
    }

    /**
     * 导出我国周边国家信息列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:country:export')")
    @Log(title = "我国周边国家信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Country country)
    {
        List<Country> list = countryService.selectCountryList(country);
        ExcelUtil<Country> util = new ExcelUtil<Country>(Country.class);
        return util.exportExcel(list, "我国周边国家信息数据");
    }

    /**
     * 获取我国周边国家信息详细信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:country:query')")
    @GetMapping(value = "/{countryId}")
    public AjaxResult getInfo(@PathVariable("countryId") Long countryId)
    {
        return AjaxResult.success(countryService.selectCountryById(countryId));
    }

    /**
     * 新增我国周边国家信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:country:add')")
    @Log(title = "我国周边国家信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Country country)
    {
        return toAjax(countryService.insertCountry(country));
    }

    /**
     * 修改我国周边国家信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:country:edit')")
    @Log(title = "我国周边国家信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Country country)
    {
        return toAjax(countryService.updateCountry(country));
    }

    /**
     * 删除我国周边国家信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:country:remove')")
    @Log(title = "我国周边国家信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{countryIds}")
    public AjaxResult remove(@PathVariable Long[] countryIds)
    {
        return toAjax(countryService.deleteCountryByIds(countryIds));
    }
}
