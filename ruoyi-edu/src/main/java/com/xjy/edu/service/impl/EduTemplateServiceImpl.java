package com.xjy.edu.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.xjy.edu.domain.EduPartition;
import com.xjy.edu.domain.EduSeat;
import com.xjy.edu.domain.vo.EduTemplateRequestVo;
import com.xjy.edu.mapper.EduPartitionMapper;
import com.xjy.edu.mapper.EduSeatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjy.edu.mapper.EduTemplateMapper;
import com.xjy.edu.domain.EduTemplate;
import com.xjy.edu.service.IEduTemplateService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 模板Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@Service
public class EduTemplateServiceImpl implements IEduTemplateService 
{
    @Autowired
    private EduTemplateMapper eduTemplateMapper;

    @Autowired
    private EduSeatMapper eduSeatMapper;

    @Autowired
    private EduPartitionMapper eduPartitionMapper;
    /**
     * 查询最新模板
     *
     * @return 模板
     */
    @Override
    public EduTemplate getLastEduTemplate()
    {
        return eduTemplateMapper.getLastEduTemplate();
    }

    /**
     * 查询模板
     *
     * @param id 模板ID
     * @return 模板
     */
    @Override
    public EduTemplate selectEduTemplateById(Long id)
    {
        return eduTemplateMapper.selectEduTemplateById(id);
    }

    /**
     * 查询模板列表
     * 
     * @param eduTemplate 模板
     * @return 模板
     */
    @Override
    public List<EduTemplate> selectEduTemplateList(EduTemplate eduTemplate)
    {
        return eduTemplateMapper.selectEduTemplateList(eduTemplate);
    }

    /**
     * 新增模板
     * 
     * @param eduTemplateVo 模板
     * @return 结果
     */
    @Override
    @Transactional
    public int insertEduTemplate(EduTemplateRequestVo eduTemplateVo)
    {
        eduTemplateVo.setCreateTime(DateUtils.getNowDate());
        EduTemplate eduTemplate = eduTemplateVo.getTemplate();
        int rows = eduTemplateMapper.insertEduTemplate(eduTemplate);
        if(rows != 0){
            eduTemplate = this.getLastEduTemplate();
            EduPartition eduPartition;
            EduSeat eduSeat = new EduSeat();
            //创建分区
            for(int i = 0; i < eduTemplate.getPartitionNumber(); i++){
                eduPartition = eduTemplateVo.getPartitionsList().get(i);
                eduPartition.setTemplateId(eduTemplate.getId());
                eduPartitionMapper.insertEduPartition(eduPartition);
            }
            //创建席位
            for(int i = 0; i < eduTemplate.getTotalSeats(); i++){
                eduSeat.setCreateTime(DateUtils.getNowDate());
                eduSeat.setTemplateId(eduTemplate.getId());
                eduSeatMapper.insertEduSeat(eduSeat);
            }
        };
        return rows;
    }

    /**
     * 修改模板
     * 
     * @param eduTemplate 模板
     * @return 结果
     */
    @Override
    public int updateEduTemplate(EduTemplate eduTemplate)
    {
        eduTemplate.setUpdateTime(DateUtils.getNowDate());
        return eduTemplateMapper.updateEduTemplate(eduTemplate);
    }

    /**
     * 批量删除模板
     * 
     * @param ids 需要删除的模板ID
     * @return 结果
     */
    @Override
    public int deleteEduTemplateByIds(Long[] ids)
    {
        return eduTemplateMapper.deleteEduTemplateByIds(ids);
    }

    /**
     * 删除模板信息
     * 
     * @param id 模板ID
     * @return 结果
     */
    @Override
    public int deleteEduTemplateById(Long id)
    {
        return eduTemplateMapper.deleteEduTemplateById(id);
    }
}
