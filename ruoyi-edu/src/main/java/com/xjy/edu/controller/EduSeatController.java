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
import com.xjy.edu.domain.EduSeat;
import com.xjy.edu.service.IEduSeatService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 席位Controller
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@RestController
@RequestMapping("/edu/seat")
public class EduSeatController extends BaseController
{
    @Autowired
    private IEduSeatService eduSeatService;

    /**
     * 查询席位列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:seat:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduSeat eduSeat)
    {
        startPage();
        List<EduSeat> list = eduSeatService.selectEduSeatList(eduSeat);
        return getDataTable(list);
    }

    /**
     * 导出席位列表
     */
    //@PreAuthorize("@ss.hasPermi('edu:seat:export')")
    @Log(title = "席位", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EduSeat eduSeat)
    {
        List<EduSeat> list = eduSeatService.selectEduSeatList(eduSeat);
        ExcelUtil<EduSeat> util = new ExcelUtil<EduSeat>(EduSeat.class);
        return util.exportExcel(list, "席位数据");
    }

    /**
     * 获取席位详细信息
     */
    //@PreAuthorize("@ss.hasPermi('edu:seat:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(eduSeatService.selectEduSeatById(id));
    }

    /**
     * 新增席位
     */
    //@PreAuthorize("@ss.hasPermi('edu:seat:add')")
    @Log(title = "席位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduSeat eduSeat)
    {
        return toAjax(eduSeatService.insertEduSeat(eduSeat));
    }

    /**
     * 修改席位
     */
    //@PreAuthorize("@ss.hasPermi('edu:seat:edit')")
    @Log(title = "席位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduSeat eduSeat)
    {
        return toAjax(eduSeatService.updateEduSeat(eduSeat));
    }

    /**
     * 删除席位
     */
    //@PreAuthorize("@ss.hasPermi('edu:seat:remove')")
    @Log(title = "席位", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eduSeatService.deleteEduSeatByIds(ids));
    }
}
