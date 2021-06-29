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
import com.xjy.edu.domain.City;
import com.xjy.edu.service.ICityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 国内主要城市信息Controller
 * 
 * @author wuzh
 * @date 2021-05-31
 */
@RestController
@RequestMapping("/geog/city")
public class CityController extends BaseController
{
    @Autowired
    private ICityService cityService;

    /**
     * 查询国内主要城市信息列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:city:list')")
    @GetMapping("/list")
    public TableDataInfo list(City city)
    {
        startPage();
        List<City> list = cityService.selectCityListByKeyword(city.getKeyword());
        return getDataTable(list);
    }

    /**
     * 导出国内主要城市信息列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:city:export')")
    @Log(title = "国内主要城市信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(City city)
    {
        List<City> list = cityService.selectCityList(city);
        ExcelUtil<City> util = new ExcelUtil<City>(City.class);
        return util.exportExcel(list, "国内主要城市信息数据");
    }

    /**
     * 获取国内主要城市信息详细信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:city:query')")
    @GetMapping(value = "/{cityId}")
    public AjaxResult getInfo(@PathVariable("cityId") Long cityId)
    {
        return AjaxResult.success(cityService.selectCityById(cityId));
    }

    /**
     * 新增国内主要城市信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:city:add')")
    @Log(title = "国内主要城市信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody City city)
    {
        return toAjax(cityService.insertCity(city));
    }

    /**
     * 修改国内主要城市信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:city:edit')")
    @Log(title = "国内主要城市信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody City city)
    {
        return toAjax(cityService.updateCity(city));
    }

    /**
     * 删除国内主要城市信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:city:remove')")
    @Log(title = "国内主要城市信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cityIds}")
    public AjaxResult remove(@PathVariable Long[] cityIds)
    {
        return toAjax(cityService.deleteCityByIds(cityIds));
    }
}
