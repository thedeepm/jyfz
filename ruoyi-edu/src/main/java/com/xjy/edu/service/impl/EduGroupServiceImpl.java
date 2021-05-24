package com.xjy.edu.service.impl;

import java.util.List;

import com.ruoyi.common.utils.Arith;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.xjy.edu.domain.EduPartition;
import com.xjy.edu.domain.EduSeat;
import com.xjy.edu.domain.vo.EduGroupRequestVo;
import com.xjy.edu.mapper.EduSeatMapper;
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
        int rows = 0;
        EduGroup eduGroup = new EduGroup();
        List<EduGroup> eduGroupList;
        EduGroupRequestVo eduGroupRequestVo = new EduGroupRequestVo();
        EduSeat seat = new EduSeat();
        //List<EduSeat> seatList;
//        int count = 0;
//        Double index = 1d, groupIndex = 1d;
        for(int i = 0; i < eduGroupRequestVoList.size(); i++){
            eduGroupRequestVo = eduGroupRequestVoList.get(i);
            eduGroupList = eduGroupRequestVo.getEduGroupList();
            for(int j = 0; j< eduGroupList.size(); j++){
                eduGroup = eduGroupList.get(j);
                eduGroup.setPartitionId(eduGroupRequestVo.getId());
                //创建分组后 修改席位关联起分组id 并计算出index
                //TODO 更新分区中的分组数量
                rows = eduGroupMapper.insertEduGroup(eduGroup);
                if(rows != 0){
                    eduGroup = this.getLastEduGroup();
                    //修改席位及index
//                    seat.setTemplateId(eduGroupRequestVo.getTemplateId());
//                    seatList = eduSeatMapper.selectEduSeatList(seat);
//                    for (int k = count; k < seatList.size(); k++){
//                        if(k!=0){
//                            index = Math.ceil(Arith.div((eduGroupList.size()*eduGroupRequestVoList.size())*(k), seatList.size()));
//                        }else{
//                            index = Math.ceil(Arith.div((eduGroupList.size()*eduGroupRequestVoList.size())*(k+1), seatList.size()));
//                        }
//                        groupIndex = Math.ceil(Arith.div((eduGroupList.size()*eduGroupRequestVoList.size())*(k+1), seatList.size()));
//                        seat = seatList.get(k);
//                        seat.setGroupId(eduGroupList.get(j).getId());
//                        seat.setGroupIndex(groupIndex.longValue());
//                        eduSeatMapper.updateEduSeat(seat);
//                        if(!index.equals(groupIndex)){
//                            count = k;
//                            //break;
//                        }
//                    }
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
     * @param eduGroup 分组
     * @return 结果
     */
    @Override
    public int updateEduGroup(EduGroup eduGroup)
    {
        return eduGroupMapper.updateEduGroup(eduGroup);
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

//    public void setGroupIndex(GenTable table)
//    {
//        String subTableName = table.getSubTableName();
//        if (StringUtils.isNotEmpty(subTableName))
//        {
//            table.setSubTable(genTableMapper.selectGenTableByName(subTableName));
//        }
//    }
}
