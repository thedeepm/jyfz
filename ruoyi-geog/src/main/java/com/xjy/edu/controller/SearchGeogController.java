package com.xjy.edu.controller;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.xjy.edu.domain.*;
import com.xjy.edu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 国内主要城市信息Controller
 * 
 * @author wuzh
 * @date 2021-05-31
 */
@RestController
@RequestMapping("/geog/searchAll")
public class SearchGeogController extends BaseController
{
    @Autowired
    private ISearchGeoService searchGeoService;

    /**
     * 速查地理信息
     */
 //   @PreAuthorize("@ss.hasPermi('edu:searchAll:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseSearchEntity baseEntity)
    {
        startPage();
        List<BaseSearchEntity> results = searchGeoService.selectSearchAllList(baseEntity);
        return getDataTable(results);
    }

}
