package com.xjy.edu.controller;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.xjy.edu.domain.EduFlowRefrence;
import com.xjy.edu.domain.EduPersonInfo;
import com.xjy.edu.domain.EduSeat;
import com.xjy.edu.domain.vo.SearchRequestVo;
import com.xjy.edu.service.IEduFlowRefrenceService;
import com.xjy.edu.service.IEduPersonInfoService;
import com.xjy.edu.service.IEduSeatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 案例Controller
 *
 * @author wuzh
 * @date 2021-05-19
 */
@Api(value = "/edu/search", description = "搜索")
@RestController
@RequestMapping("/edu/search")
public class SearchController extends BaseController
{
//    @Autowired
//    private IEduSeatService eduSeatService;

    @Autowired
    private IEduFlowRefrenceService eduFlowRefrenceService;

    @Autowired
    private IEduPersonInfoService eduPersonInfoService;

    /**
     * 查询列表
     * 搜索
     */
    @ApiOperation("查询列表")
    //@PreAuthorize("@ss.hasPermi('edu:case:list')")
    @GetMapping("/list")
    public AjaxResult list(@ApiParam(value = "查询列表", required = true) SearchRequestVo searchRequestVo)
    {
        startPage();
        String keyword = searchRequestVo.getKeyword();;
        String category = searchRequestVo.getCategory();
        //EduSeat eduSeat = new EduSeat();
        EduPersonInfo eduPersonInfo = new EduPersonInfo();
        EduFlowRefrence eduFlowRefrence = new EduFlowRefrence();
        List<EduPersonInfo> personInfoList;
        List<EduFlowRefrence> flowRefrenceList;
        List<Object> responseList = new ArrayList<>();
        eduFlowRefrence.setSearchValue(keyword);
        eduPersonInfo.setSearchValue(keyword);
        AjaxResult ajaxResult = AjaxResult.success();
        if(StringUtils.isNull(category) || StringUtils.isEmpty(category)){
            personInfoList = eduPersonInfoService.selectEduPersonInfoList(eduPersonInfo);
            responseList = new ArrayList<>(personInfoList);
            flowRefrenceList = eduFlowRefrenceService.selectEduFlowRefrenceList(eduFlowRefrence);
            responseList.addAll(flowRefrenceList);
            ajaxResult.put("personInfoListNum", new PageInfo(personInfoList).getTotal());
            ajaxResult.put("flowRefrenceListNum", new PageInfo(flowRefrenceList).getTotal());
            ajaxResult.put("personInfoList",personInfoList);
            ajaxResult.put("flowRefrenceList",flowRefrenceList);
        } else{
            if("flowRefrence".equals(category)){
                flowRefrenceList = eduFlowRefrenceService.selectEduFlowRefrenceList(eduFlowRefrence);
                responseList.addAll(flowRefrenceList);
                ajaxResult.put("flowRefrenceListNum", new PageInfo(responseList).getTotal());
                ajaxResult.put("flowRefrenceList",flowRefrenceList);
            }else{
                personInfoList = eduPersonInfoService.selectEduPersonInfoList(eduPersonInfo);
                ajaxResult.put("personInfoListNum", new PageInfo(responseList).getTotal());
                responseList.addAll(personInfoList);
                ajaxResult.put("personInfoList",personInfoList);
            }
        }
        ajaxResult.put(AjaxResult.MSG_TAG, "查询成功");
        ajaxResult.put("total", new PageInfo(responseList).getTotal());
        ajaxResult.put("rows", responseList);
        return ajaxResult;
    }

    @GetMapping("/detail")
    public AjaxResult detail(
            @RequestParam Long id,
            @RequestParam(defaultValue = "") String category
            ){
        AjaxResult ajaxResult = AjaxResult.success();
        EduFlowRefrence flowRefrence;
        EduPersonInfo personInfo;
        if("flowRefrence".equals(category)){
            flowRefrence = eduFlowRefrenceService.selectEduFlowRefrenceById(id);
            ajaxResult.put(AjaxResult.DATA_TAG, flowRefrence);
        } else {
            personInfo = eduPersonInfoService.selectEduPersonInfoById(id);
            ajaxResult.put(AjaxResult.DATA_TAG, personInfo);
        }
        return ajaxResult;
    }

}
