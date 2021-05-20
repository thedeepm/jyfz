package com.xjy.edu.service;

import java.util.List;
import com.xjy.edu.domain.EduFlowRefrence;

/**
 * 流程图Service接口
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public interface IEduFlowRefrenceService 
{
    /**
     * 查询流程图
     * 
     * @param id 流程图ID
     * @return 流程图
     */
        EduFlowRefrence selectEduFlowRefrenceById(Long id);

    /**
     * 查询流程图列表
     * 
     * @param eduFlowRefrence 流程图
     * @return 流程图集合
     */
    List<EduFlowRefrence> selectEduFlowRefrenceList(EduFlowRefrence eduFlowRefrence);

    /**
     * 新增流程图
     * 
     * @param eduFlowRefrence 流程图
     * @return 结果
     */
    int insertEduFlowRefrence(EduFlowRefrence eduFlowRefrence);

    /**
     * 修改流程图
     * 
     * @param eduFlowRefrence 流程图
     * @return 结果
     */
    int updateEduFlowRefrence(EduFlowRefrence eduFlowRefrence);

    /**
     * 批量删除流程图
     * 
     * @param ids 需要删除的流程图ID
     * @return 结果
     */
    int deleteEduFlowRefrenceByIds(Long[] ids);

    /**
     * 删除流程图信息
     * 
     * @param id 流程图ID
     * @return 结果
     */
    int deleteEduFlowRefrenceById(Long id);
}
