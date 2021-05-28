package com.xjy.edu.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.xjy.edu.domain.EduFlowRefrence;
import com.xjy.edu.domain.EduSeat;
import com.xjy.edu.domain.vo.SearchRequestVo;
import com.xjy.edu.service.IEduFlowRefrenceService;
import com.xjy.edu.service.IEduSeatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private IEduSeatService eduSeatService;

    @Autowired
    private IEduFlowRefrenceService eduFlowRefrenceService;

    /**
     * 查询列表
     * 搜索
     */
    @ApiOperation("查询列表")
    //@PreAuthorize("@ss.hasPermi('edu:case:list')")
    @GetMapping("/list")
    public TableDataInfo list(@ApiParam(value = "查询列表", required = true) SearchRequestVo searchRequestVo)
    {
        startPage();
        String keyword = searchRequestVo.getKeyword();;
        String category = searchRequestVo.getCategory();
        EduSeat eduSeat = new EduSeat();
        EduFlowRefrence eduFlowRefrence = new EduFlowRefrence();
        List<EduSeat> seatList;
        List<EduFlowRefrence> flowRefrenceList;
        List<Object> responseList = new ArrayList<>();
        eduFlowRefrence.setSearchValue(keyword);
        eduSeat.setSearchValue(keyword);
        if(StringUtils.isNull(category) || StringUtils.isEmpty(category)){
            seatList = eduSeatService.selectEduSeatList(eduSeat);
            responseList = new ArrayList<>(seatList);
            flowRefrenceList = eduFlowRefrenceService.selectEduFlowRefrenceList(eduFlowRefrence);
            responseList.addAll(flowRefrenceList);
        } else{
            if("flowRefrence".equals(category)){
                flowRefrenceList = eduFlowRefrenceService.selectEduFlowRefrenceList(eduFlowRefrence);
                responseList.addAll(flowRefrenceList);
            }else{
                seatList = eduSeatService.selectEduSeatList(eduSeat);
                responseList.addAll(seatList);
            }
        }
        return getDataTable(responseList);
    }

}
