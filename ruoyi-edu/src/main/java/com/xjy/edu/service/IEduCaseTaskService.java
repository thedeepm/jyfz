package com.xjy.edu.service;

import java.util.List;

import com.ruoyi.system.domain.SysFileInfo;
import com.xjy.edu.domain.EduCaseTask;

/**
 * 案例任务关联Service接口
 * 
 * @author wuzh
 * @date 2021-05-29
 */
public interface IEduCaseTaskService 
{
    /**
     * 查询案例任务关联
     * 
     * @param id 案例任务关联ID
     * @return 案例任务关联
     */
        EduCaseTask selectEduCaseTaskById(Long id);

    /**
     * 查询案例任务关联
     *
     * @param eduCaseTask 案例任务关联ID
     * @return 案例任务关联
     */
    SysFileInfo selectFlowFileInfo(EduCaseTask eduCaseTask);

    /**
     * 查询案例任务关联列表
     * 
     * @param eduCaseTask 案例任务关联
     * @return 案例任务关联集合
     */
    List<EduCaseTask> selectEduCaseTaskList(EduCaseTask eduCaseTask);

    /**
     * 新增案例任务关联
     * 
     * @param eduCaseTask 案例任务关联
     * @return 结果
     */
    int insertEduCaseTask(EduCaseTask eduCaseTask);

    /**
     * 修改案例任务关联
     * 
     * @param eduCaseTask 案例任务关联
     * @return 结果
     */
    int updateEduCaseTask(EduCaseTask eduCaseTask);

    /**
     * 批量删除案例任务关联
     * 
     * @param ids 需要删除的案例任务关联ID
     * @return 结果
     */
    int deleteEduCaseTaskByIds(Long[] ids);

    /**
     * 删除案例任务关联信息
     * 
     * @param id 案例任务关联ID
     * @return 结果
     */
    int deleteEduCaseTaskById(Long id);
}
