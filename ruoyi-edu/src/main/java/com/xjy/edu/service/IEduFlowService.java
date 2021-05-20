package com.xjy.edu.service;

import java.util.List;
import com.xjy.edu.domain.EduFlow;

/**
 * 流程Service接口
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public interface IEduFlowService 
{
    /**
     * 查询流程
     * 
     * @param id 流程ID
     * @return 流程
     */
        EduFlow selectEduFlowById(Long id);

    /**
     * 查询流程列表
     * 
     * @param eduFlow 流程
     * @return 流程集合
     */
    List<EduFlow> selectEduFlowList(EduFlow eduFlow);

    /**
     * 新增流程
     * 
     * @param eduFlow 流程
     * @return 结果
     */
    int insertEduFlow(EduFlow eduFlow);

    /**
     * 修改流程
     * 
     * @param eduFlow 流程
     * @return 结果
     */
    int updateEduFlow(EduFlow eduFlow);

    /**
     * 批量删除流程
     * 
     * @param ids 需要删除的流程ID
     * @return 结果
     */
    int deleteEduFlowByIds(Long[] ids);

    /**
     * 删除流程信息
     * 
     * @param id 流程ID
     * @return 结果
     */
    int deleteEduFlowById(Long id);
}
