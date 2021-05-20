package com.xjy.edu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjy.edu.mapper.EduFlowMapper;
import com.xjy.edu.domain.EduFlow;
import com.xjy.edu.service.IEduFlowService;

/**
 * 流程Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@Service
public class EduFlowServiceImpl implements IEduFlowService 
{
    @Autowired(required = false)
    private EduFlowMapper eduFlowMapper;

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
     * @param eduFlow 流程
     * @return 结果
     */
    @Override
    public int insertEduFlow(EduFlow eduFlow)
    {
        return eduFlowMapper.insertEduFlow(eduFlow);
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
