package com.xjy.edu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.xjy.edu.domain.EduTask;
import com.xjy.edu.domain.EduTemplate;
import com.xjy.edu.domain.vo.EduFlowRequestVo;
import com.xjy.edu.mapper.EduTaskMapper;
import com.xjy.edu.mapper.EduTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjy.edu.mapper.EduFlowMapper;
import com.xjy.edu.domain.EduFlow;
import com.xjy.edu.service.IEduFlowService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 流程Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@Service
public class EduFlowServiceImpl implements IEduFlowService 
{
    @Autowired
    private EduFlowMapper eduFlowMapper;

    @Autowired
    private EduTaskMapper eduTaskMapper;


    @Autowired
    private EduTemplateMapper eduTemplateMapper ;
    /**
     * 查询流程
     * 
     * @param id 流程ID
     * @return 流程
     */
    @Override
    public EduFlow selectEduFlowById(Long id)
    {
        return eduFlowMapper.selectEduFlowById(id);
    }

    /**
     * 查询流程
     *
     * @return 流程
     */
    @Override
    public EduFlow getLastEduFlow()
    {
        return eduFlowMapper.getLastEduFlow();
    }

    /**
     * 查询流程列表
     * 
     * @param eduFlow 流程
     * @return 流程
     */
    @Override
    public List<EduFlow> selectEduFlowList(EduFlow eduFlow)
    {
        return eduFlowMapper.selectEduFlowList(eduFlow);
    }

    /**
     * 新增流程
     * 
     * @param eduFlowRequestVo 流程
     * @return 结果
     */
    @Override
    @Transactional
    public EduFlow insertEduFlow(EduFlowRequestVo eduFlowRequestVo)
    {
        EduFlow eduFlow = eduFlowRequestVo.getEduflow();
        List<EduTask> eduTaskList = eduFlowRequestVo.getEduTaskList();
        EduTask eduTask;
        if(eduFlow.getStepNumber() == null){
            eduFlow.setStepNumber(0L);
        };
        int rows = eduFlowMapper.insertEduFlow(eduFlow);
        if(rows != 0){
            eduFlow = this.getLastEduFlow();
            if(eduTaskList != null){
                for (int i = 0; i < eduTaskList.size(); i++){
                    eduTask = eduTaskList.get(i);
                    eduTask.setFlowId(eduFlow.getId());
                    //创建任务
                    eduTaskMapper.insertEduTask(eduTask);
                }
            }
            //创建任务后需将模板绑定流程
            EduTemplate eduTemplate = new EduTemplate();
            eduTemplate.setId(eduFlowRequestVo.getTemplateId());
            eduTemplate = eduTemplateMapper.selectEduTemplateList(eduTemplate).get(0);
            eduTemplate.setFlowId(eduFlow.getId());
        }

        return eduFlow;
    }

    /**
     * 修改流程
     * 
     * @param eduFlow 流程
     * @return 结果
     */
    @Override
    public int updateEduFlow(EduFlow eduFlow)
    {
        return eduFlowMapper.updateEduFlow(eduFlow);
    }

    /**
     * 批量删除流程
     * 
     * @param ids 需要删除的流程ID
     * @return 结果
     */
    @Override
    public int deleteEduFlowByIds(Long[] ids)
    {
        return eduFlowMapper.deleteEduFlowByIds(ids);
    }

    /**
     * 删除流程信息
     * 
     * @param id 流程ID
     * @return 结果
     */
    @Override
    public int deleteEduFlowById(Long id)
    {
        return eduFlowMapper.deleteEduFlowById(id);
    }
}
