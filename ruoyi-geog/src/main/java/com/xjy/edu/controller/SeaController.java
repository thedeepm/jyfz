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
import com.xjy.edu.domain.Sea;
import com.xjy.edu.service.ISeaService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 主要海洋基础信息Controller
 * 
 * @author wuzh
 * @date 2021-05-31
 */
@RestController
@RequestMapping("/geog/sea")
public class SeaController extends BaseController
{
    @Autowired
    private ISeaService seaService;

    /**
     * 查询主要海洋基础信息列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:sea:list')")
    @GetMapping("/list")
    public TableDataInfo list(Sea sea)
    {
        startPage();
        List<Sea> list = seaService.selectSeaList(sea);
        return getDataTable(list);
    }

    /**
     * 导出主要海洋基础信息列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:sea:export')")
    @Log(title = "主要海洋基础信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Sea sea)
    {
        List<Sea> list = seaService.selectSeaList(sea);
        ExcelUtil<Sea> util = new ExcelUtil<Sea>(Sea.class);
        return util.exportExcel(list, "主要海洋基础信息数据");
    }

    /**
     * 获取主要海洋基础信息详细信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:sea:query')")
    @GetMapping(value = "/{seaId}")
    public AjaxResult getInfo(@PathVariable("seaId") Long seaId)
    {
        return AjaxResult.success(seaService.selectSeaById(seaId));
    }

    /**
     * 新增主要海洋基础信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:sea:add')")
    @Log(title = "主要海洋基础信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Sea sea)
    {
        return toAjax(seaService.insertSea(sea));
    }

    /**
     * 修改主要海洋基础信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:sea:edit')")
    @Log(title = "主要海洋基础信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Sea sea)
    {
        return toAjax(seaService.updateSea(sea));
    }

    /**
     * 删除主要海洋基础信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:sea:remove')")
    @Log(title = "主要海洋基础信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{seaIds}")
    public AjaxResult remove(@PathVariable Long[] seaIds)
    {
        return toAjax(seaService.deleteSeaByIds(seaIds));
    }
}
