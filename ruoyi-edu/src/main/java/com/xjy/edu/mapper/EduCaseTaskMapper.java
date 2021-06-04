package com.xjy.edu.mapper;

import java.util.List;
import com.xjy.edu.domain.EduCaseTask;

/**
 * 案例任务关联Mapper接口
 * 
 * @author wuzh
 * @date 2021-05-29
 */
public interface EduCaseTaskMapper 
{
    /**
     * 查询案例任务关联
     * 
     * @param id 案例任务关联ID
     * @return 案例任务关联
     */
        EduCaseTask selectEduCaseTaskById(Long id);

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
     * 删除案例任务关联
     * 
     * @param id 案例任务关联ID
     * @return 结果
     */
    int deleteEduCaseTaskById(Long id);

    /**
     * 批量删除案例任务关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteEduCaseTaskByIds(Long[] ids);
}
