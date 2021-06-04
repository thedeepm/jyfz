package com.xjy.edu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjy.edu.mapper.EduCaseTaskMapper;
import com.xjy.edu.domain.EduCaseTask;
import com.xjy.edu.service.IEduCaseTaskService;

/**
 * 案例任务关联Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-29
 */
@Service
public class EduCaseTaskServiceImpl implements IEduCaseTaskService 
{
    @Autowired
    private EduCaseTaskMapper eduCaseTaskMapper;

    /**
     * 查询案例任务关联
     * 
     * @param id 案例任务关联ID
     * @return 案例任务关联
     */
    @Override
    public EduCaseTask selectEduCaseTaskById(Long id)
    {
        return eduCaseTaskMapper.selectEduCaseTaskById(id);
    }

    /**
     * 查询案例任务关联列表
     * 
     * @param eduCaseTask 案例任务关联
     * @return 案例任务关联
     */
    @Override
    public List<EduCaseTask> selectEduCaseTaskList(EduCaseTask eduCaseTask)
    {
        return eduCaseTaskMapper.selectEduCaseTaskList(eduCaseTask);
    }

    /**
     * 新增案例任务关联
     * 
     * @param eduCaseTask 案例任务关联
     * @return 结果
     */
    @Override
    public int insertEduCaseTask(EduCaseTask eduCaseTask)
    {
        return eduCaseTaskMapper.insertEduCaseTask(eduCaseTask);
    }

    /**
     * 修改案例任务关联
     * 
     * @param eduCaseTask 案例任务关联
     * @return 结果
     */
    @Override
    public int updateEduCaseTask(EduCaseTask eduCaseTask)
    {
        return eduCaseTaskMapper.updateEduCaseTask(eduCaseTask);
    }

    /**
     * 批量删除案例任务关联
     * 
     * @param ids 需要删除的案例任务关联ID
     * @return 结果
     */
    @Override
    public int deleteEduCaseTaskByIds(Long[] ids)
    {
        return eduCaseTaskMapper.deleteEduCaseTaskByIds(ids);
    }

    /**
     * 删除案例任务关联信息
     * 
     * @param id 案例任务关联ID
     * @return 结果
     */
    @Override
    public int deleteEduCaseTaskById(Long id)
    {
        return eduCaseTaskMapper.deleteEduCaseTaskById(id);
    }
}
