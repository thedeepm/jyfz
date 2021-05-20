package com.xjy.edu.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjy.edu.mapper.EduTaskMapper;
import com.xjy.edu.domain.EduTask;
import com.xjy.edu.service.IEduTaskService;

/**
 * 任务Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@Service
public class EduTaskServiceImpl implements IEduTaskService 
{
    @Autowired(required = false)
    private EduTaskMapper eduTaskMapper;

    /**
     * 查询任务
     * 
     * @param id 任务ID
     * @return 任务
     */
    @Override
    public EduTask selectEduTaskById(Long id)
    {
        return eduTaskMapper.selectEduTaskById(id);
    }

    /**
     * 查询任务列表
     * 
     * @param eduTask 任务
     * @return 任务
     */
    @Override
    public List<EduTask> selectEduTaskList(EduTask eduTask)
    {
        return eduTaskMapper.selectEduTaskList(eduTask);
    }

    /**
     * 新增任务
     * 
     * @param eduTask 任务
     * @return 结果
     */
    @Override
    public int insertEduTask(EduTask eduTask)
    {
        eduTask.setCreateTime(DateUtils.getNowDate());
        return eduTaskMapper.insertEduTask(eduTask);
    }

    /**
     * 修改任务
     * 
     * @param eduTask 任务
     * @return 结果
     */
    @Override
    public int updateEduTask(EduTask eduTask)
    {
        return eduTaskMapper.updateEduTask(eduTask);
    }

    /**
     * 批量删除任务
     * 
     * @param ids 需要删除的任务ID
     * @return 结果
     */
    @Override
    public int deleteEduTaskByIds(Long[] ids)
    {
        return eduTaskMapper.deleteEduTaskByIds(ids);
    }

    /**
     * 删除任务信息
     * 
     * @param id 任务ID
     * @return 结果
     */
    @Override
    public int deleteEduTaskById(Long id)
    {
        return eduTaskMapper.deleteEduTaskById(id);
    }
}
