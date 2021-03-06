package com.xjy.edu.mapper;

import java.util.List;
import com.xjy.edu.domain.EduTask;
import com.xjy.edu.domain.EduTemplate;

/**
 * 任务Mapper接口
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public interface EduTaskMapper 
{
    /**
     * 查询任务
     * 
     * @param id 任务ID
     * @return 任务
     */
        EduTask selectEduTaskById(Long id);


    EduTask getLastEduTask();

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
    int insertEduTask(EduTask eduTask);

    /**
     * 修改任务
     * 
     * @param eduTask 任务
     * @return 结果
     */
    int updateEduTask(EduTask eduTask);

    /**
     * 删除任务
     * 
     * @param id 任务ID
     * @return 结果
     */
    int deleteEduTaskById(Long id);

    /**
     * 批量删除任务
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteEduTaskByIds(Long[] ids);
}
