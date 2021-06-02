package com.xjy.edu.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.xjy.edu.domain.EduTask;

/**
 * 任务Service接口
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public interface IEduTaskService 
{
    /**
     * 查询任务
     * 
     * @param id 任务ID
     * @return 任务
     */
        EduTask selectEduTaskById(Long id);

    /**
     * 查询任务列表
     * 
     * @param eduTask 任务
     * @return 任务集合
     */
    List<EduTask> selectEduTaskList(EduTask eduTask);

    /**
     * 新增任务
     * 
     * @param eduTask 任务
     * @return 结果
     */
    EduTask insertEduTask(EduTask eduTask, AjaxResult ajaxResult);

    /**
     * 修改任务
     * 
     * @param eduTask 任务
     * @return 结果
     */
    int updateEduTask(EduTask eduTask);

    /**
     * 批量删除任务
     * 
     * @param ids 需要删除的任务ID
     * @return 结果
     */
    int deleteEduTaskByIds(Long[] ids);

    /**
     * 删除任务信息
     * 
     * @param id 任务ID
     * @return 结果
     */
    int deleteEduTaskById(Long id);
}
