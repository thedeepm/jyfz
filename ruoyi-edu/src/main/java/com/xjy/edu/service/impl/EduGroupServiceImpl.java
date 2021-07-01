package com.xjy.edu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.utils.Arith;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.xjy.edu.domain.EduPartition;
import com.xjy.edu.domain.EduSeat;
import com.xjy.edu.domain.vo.EduGroupRequestVo;
import com.xjy.edu.mapper.EduSeatMapper;
import com.xjy.edu.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjy.edu.mapper.EduGroupMapper;
import com.xjy.edu.domain.EduGroup;
import com.xjy.edu.service.IEduGroupService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 分组Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@Service
public class EduGroupServiceImpl implements IEduGroupService 
{
    @Autowired
    private EduGroupMapper eduGroupMapper;

    @Autowired
    private EduSeatMapper eduSeatMapper;
    /**
     * 查询分组
     * 
     * @param id 分组ID
     * @return 分组
     */
    @Override
    public EduGroup selectEduGroupById(Long id)
    {
        return eduGroupMapper.selectEduGroupById(id);
    }

    /**
     * 获取最新分组
     *
     * @return 分组
     */
    @Override
    public EduGroup getLastEduGroup(){
        return  eduGroupMapper.getLastEduGroup();
    };

    /**
     * 查询分组列表
     * 
     * @param eduGroup 分组
     * @return 分组
     */
    @Override
    public List<EduGroup> selectEduGroupList(EduGroup eduGroup)
    {
        return eduGroupMapper.selectEduGroupList(eduGroup);
    }

    /**
     * 新增分组
     * 
     * @param eduGroupRequestVoList 分组
     * @return 结果
     */
    @Override
    @Transactional
    public int insertEduGroup(List<EduGroupRequestVo> eduGroupRequestVoList)
    {
        int validResult = validateGroupListData(eduGroupRequestVoList);
        if(validResult != 1){
            return validResult;
        }
        int rows = 0;
        EduGroup eduGroup;
        List<EduGroup> eduGroupList;
        EduGroupRequestVo eduGroupRequestVo;
        EduSeat seat = new EduSeat();
        for(int i = 0; i < eduGroupRequestVoList.size(); i++){
            eduGroupRequestVo = eduGroupRequestVoList.get(i);
            eduGroupList = eduGroupRequestVo.getEduGroupList();
            for(int j = 0; j< eduGroupList.size(); j++){
                eduGroup = eduGroupList.get(j);
                eduGroup.setPartitionId(eduGroupRequestVo.getId());
                //创建分组后 修改席位关联起分组id 并计算出index
                rows = eduGroupMapper.insertEduGroup(eduGroup);
                if(rows != 0){
                    eduGroup = this.getLastEduGroup();
                    //创建席位
                    seat.setGroupId(eduGroup.getId());
                    seat.setTemplateId(eduGroupRequestVo.getTemplateId());
                    seat.setCreateTime(DateUtils.getNowDate());
                    for (int k = 0; k < eduGroup.getTotalSeats(); k++){
                        seat.setGroupIndex(new Long(eduGroup.getGroupInterval().split("-")[0])+k);
                        eduSeatMapper.insertEduSeat(seat);
                    }

                }
            }
        }
        return rows;
    }

    /**
     * 修改分组
     * 
     * @param eduGroupRequestVoList 分组
     * @return 结果
     */
    @Override
    @Transactional
    public int updateEduGroup(List<EduGroupRequestVo> eduGroupRequestVoList)
    {
        int validResult = validateGroupListData(eduGroupRequestVoList);
        if(validResult != 1){
            return validResult;
        }
        int rows = 0;
        EduGroup eduGroup;
        List<EduGroup> eduGroupList;
        EduGroupRequestVo eduGroupRequestVo;
        EduSeat seat = new EduSeat();
        for(int i = 0; i < eduGroupRequestVoList.size(); i++){
            eduGroupRequestVo = eduGroupRequestVoList.get(i);
            eduGroupList = eduGroupRequestVo.getEduGroupList();
            for(int j = 0; j< eduGroupList.size(); j++){
                eduGroup = eduGroupList.get(j);
                eduGroup.setPartitionId(eduGroupRequestVo.getId());
                //创建分组后 修改席位关联起分组id 并计算出index
                rows = eduGroupMapper.updateEduGroup(eduGroup);
                if(rows != 0){
                    eduGroup = this.getLastEduGroup();
                    //创建席位
                    seat.setGroupId(eduGroup.getId());
                    seat.setTemplateId(eduGroupRequestVo.getTemplateId());
                    seat.setCreateTime(DateUtils.getNowDate());
                    for (int k = 0; k < eduGroup.getTotalSeats(); k++){
                        seat.setGroupIndex(new Long(eduGroup.getGroupInterval().split("-")[0])+k);
                        eduSeatMapper.insertEduSeat(seat);
                    }

                }
            }
        }
        return rows;
    }

    /**
     * 批量删除分组
     * 
     * @param ids 需要删除的分组ID
     * @return 结果
     */
    @Override
    public int deleteEduGroupByIds(Long[] ids)
    {
        return eduGroupMapper.deleteEduGroupByIds(ids);
    }

    /**
     * 删除分组信息
     * 
     * @param id 分组ID
     * @return 结果
     */
    @Override
    public int deleteEduGroupById(Long id)
    {
        return eduGroupMapper.deleteEduGroupById(id);
    }

    /**
     * 校验分组信息
     *
     * @param eduGroupRequestVoList eduGroupRequestVoList
     * @return int
     */
    public int validateGroupListData(List<EduGroupRequestVo> eduGroupRequestVoList)
    {
        EduGroup eduGroup;
        List<EduGroup> eduGroupList;
        EduGroupRequestVo eduGroupRequestVo;
        List<String> toatalList = new ArrayList<>();
        String groupInterval;
        Long partitionTotalseats = 0L;
        Long countTotalSeats = 0L;
        for(int i = 0; i < eduGroupRequestVoList.size(); i++){
            eduGroupRequestVo = eduGroupRequestVoList.get(i);
            eduGroupList = eduGroupRequestVo.getEduGroupList();
            partitionTotalseats += eduGroupRequestVo.getTotalSeats();
            for(int j = 0; j< eduGroupList.size(); j++){
                eduGroup = eduGroupList.get(j);
                countTotalSeats += eduGroup.getTotalSeats();
                groupInterval = eduGroup.getGroupInterval();
                if(groupInterval.contains("-")){
                    Long start = new Long(groupInterval.split("-")[0]);
                    Long end = new Long(groupInterval.split("-")[1]);
                    for (int k = start.intValue(); k <= end.intValue(); k++){
                        toatalList.add(String.valueOf(k));
                    }
                } else {
                    toatalList.add(groupInterval.split("-")[0]);
                }
                if(eduGroupRequestVo.getTotalSeats().compareTo(eduGroup.getTotalSeats()) < 0){
                    return -1;
                }
            }
        }
        if(partitionTotalseats.compareTo(countTotalSeats) < 0){
            return -1;
        }
        return CommonUtils.cheakIsRepeat(toatalList) ? 1 : 0;
    }
}
