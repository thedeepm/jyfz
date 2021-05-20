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
import com.xjy.edu.domain.EduPartition;
import com.xjy.edu.service.IEduPartitionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 分区Controller
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@RestController
@RequestMapping("/edu/partition")
public class EduPartitionController extends BaseController
{
    @Autowired
    private IEduPartitionService eduPartitionService;

    /**
     * 查询分区列表
     */
    @PreAuthorize("@ss.hasPermi('edu:partition:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduPartition eduPartition)
    {
        startPage();
        List<EduPartition> list = eduPartitionService.selectEduPartitionList(eduPartition);
        return getDataTable(list);
    }

    /**
     * 导出分区列表
     */
    @PreAuthorize("@ss.hasPermi('edu:partition:export')")
    @Log(title = "分区", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EduPartition eduPartition)
    {
        List<EduPartition> list = eduPartitionService.selectEduPartitionList(eduPartition);
        ExcelUtil<EduPartition> util = new ExcelUtil<EduPartition>(EduPartition.class);
        return util.exportExcel(list, "分区数据");
    }

    /**
     * 获取分区详细信息
     */
    @PreAuthorize("@ss.hasPermi('edu:partition:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(eduPartitionService.selectEduPartitionById(id));
    }

    /**
     * 新增分区
     */
    @PreAuthorize("@ss.hasPermi('edu:partition:add')")
    @Log(title = "分区", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduPartition eduPartition)
    {
        return toAjax(eduPartitionService.insertEduPartition(eduPartition));
    }

    /**
     * 修改分区
     */
    @PreAuthorize("@ss.hasPermi('edu:partition:edit')")
    @Log(title = "分区", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduPartition eduPartition)
    {
        return toAjax(eduPartitionService.updateEduPartition(eduPartition));
    }

    /**
     * 删除分区
     */
    @PreAuthorize("@ss.hasPermi('edu:partition:remove')")
    @Log(title = "分区", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eduPartitionService.deleteEduPartitionByIds(ids));
    }
}
