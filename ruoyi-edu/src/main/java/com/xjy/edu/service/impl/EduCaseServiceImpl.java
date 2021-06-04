package com.xjy.edu.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.xjy.edu.domain.EduCaseTask;
import com.xjy.edu.domain.EduTask;
import com.xjy.edu.domain.EduTemplate;
import com.xjy.edu.mapper.EduCaseTaskMapper;
import com.xjy.edu.mapper.EduTaskMapper;
import com.xjy.edu.mapper.EduTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjy.edu.mapper.EduCaseMapper;
import com.xjy.edu.domain.EduCase;
import com.xjy.edu.service.IEduCaseService;

/**
 * 案例Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@Service
public class EduCaseServiceImpl implements IEduCaseService 
{
    @Autowired
    private EduCaseMapper eduCaseMapper;

    @Autowired
    private EduTemplateMapper eduTemplateMapper;

    @Autowired
    private EduTaskMapper eduTaskMapper;

    @Autowired
    private EduCaseTaskMapper eduCaseTaskMapper;

    /**
     * 查询案例
     * 
     * @param id 案例ID
     * @return 案例
     */
    @Override
    public EduCase selectEduCaseById(Long id)
    {
        return eduCaseMapper.selectEduCaseById(id);
    }

    /**
     * 查询案例列表
     * 
     * @param eduCase 案例
     * @return 案例
     */
    @Override
    public List<EduCase> selectEduCaseList(EduCase eduCase)
    {
        return eduCaseMapper.selectEduCaseList(eduCase);
    }

    /**
     * 新增案例
     * 
     * @param eduCase 案例
     * @return 结果
     */
    @Override
    public int insertEduCase(EduCase eduCase)
    {
        eduCase.setCreateTime(DateUtils.getNowDate());
        EduTemplate eduTemplate = eduTemplateMapper.selectEduTemplateById(eduCase.getTemplateId());
        EduTask eduTask = new EduTask();
        EduCaseTask eduCaseTask = new EduCaseTask();
        int rows = eduCaseMapper.insertEduCase(eduCase);
        if(rows != 0){
            eduTask.setFlowId(eduTemplate.getFlowId());
            List<EduTask> eduTaskList = eduTaskMapper.selectEduTaskList(eduTask);
            for (int i = 0; i < eduTaskList.size();i++){
                eduCaseTask.setCaseId(eduCase.getId());
                eduCaseTask.setTaskId(eduTaskList.get(i).getId());
                eduCaseTask.setCreateTime(DateUtils.getNowDate());
                eduCaseTaskMapper.insertEduCaseTask(eduCaseTask);
            }
        }
        return rows;
    }

    /**
     * 修改案例
     * 
     * @param eduCase 案例
     * @return 结果
     */
    @Override
    public int updateEduCase(EduCase eduCase)
    {
        eduCase.setUpdateTime(DateUtils.getNowDate());
        return eduCaseMapper.updateEduCase(eduCase);
    }

    /**
     * 批量删除案例
     * 
     * @param ids 需要删除的案例ID
     * @return 结果
     */
    @Override
    public int deleteEduCaseByIds(Long[] ids)
    {
        return eduCaseMapper.deleteEduCaseByIds(ids);
    }

    /**
     * 删除案例信息
     * 
     * @param id 案例ID
     * @return 结果
     */
    @Override
    public int deleteEduCaseById(Long id)
    {
        return eduCaseMapper.deleteEduCaseById(id);
    }
}
