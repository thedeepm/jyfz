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
import com.xjy.edu.domain.Port;
import com.xjy.edu.service.IPortService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 国内外主要港口信息Controller
 * 
 * @author wuzh
 * @date 2021-05-31
 */
@RestController
@RequestMapping("/geog/port")
public class PortController extends BaseController
{
    @Autowired
    private IPortService portService;

    /**
     * 查询国内外主要港口信息列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:port:list')")
    @GetMapping("/list")
    public TableDataInfo list(Port port)
    {
        startPage();
        List<Port> list = portService.selectPortList(port);
        return getDataTable(list);
    }

    /**
     * 导出国内外主要港口信息列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:port:export')")
    @Log(title = "国内外主要港口信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Port port)
    {
        List<Port> list = portService.selectPortList(port);
        ExcelUtil<Port> util = new ExcelUtil<Port>(Port.class);
        return util.exportExcel(list, "国内外主要港口信息数据");
    }

    /**
     * 获取国内外主要港口信息详细信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:port:query')")
    @GetMapping(value = "/{portId}")
    public AjaxResult getInfo(@PathVariable("portId") Long portId)
    {
        return AjaxResult.success(portService.selectPortById(portId));
    }

    /**
     * 新增国内外主要港口信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:port:add')")
    @Log(title = "国内外主要港口信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Port port)
    {
        return toAjax(portService.insertPort(port));
    }

    /**
     * 修改国内外主要港口信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:port:edit')")
    @Log(title = "国内外主要港口信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Port port)
    {
        return toAjax(portService.updatePort(port));
    }

    /**
     * 删除国内外主要港口信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:port:remove')")
    @Log(title = "国内外主要港口信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{portIds}")
    public AjaxResult remove(@PathVariable Long[] portIds)
    {
        return toAjax(portService.deletePortByIds(portIds));
    }
}
