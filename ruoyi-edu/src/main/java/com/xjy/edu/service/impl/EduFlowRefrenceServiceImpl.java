package com.xjy.edu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjy.edu.mapper.EduFlowRefrenceMapper;
import com.xjy.edu.domain.EduFlowRefrence;
import com.xjy.edu.service.IEduFlowRefrenceService;

/**
 * 流程图Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@Service
public class EduFlowRefrenceServiceImpl implements IEduFlowRefrenceService 
{
    @Autowired
    private EduFlowRefrenceMapper eduFlowRefrenceMapper;

    /**
     * 查询流程图
     * 
     * @param id 流程图ID
     * @return 流程图
     */
    @Override
    public EduFlowRefrence selectEduFlowRefrenceById(Long id)
    {
        return eduFlowRefrenceMapper.selectEduFlowRefrenceById(id);
    }

    /**
     * 查询流程图列表
     * 
     * @param eduFlowRefrence 流程图
     * @return 流程图
     */
    @Override
    public List<EduFlowRefrence> selectEduFlowRefrenceList(EduFlowRefrence eduFlowRefrence)
    {
        return eduFlowRefrenceMapper.selectEduFlowRefrenceList(eduFlowRefrence);
    }

    /**
     * 新增流程图
     * 
     * @param eduFlowRefrence 流程图
     * @return 结果
     */
    @Override
    public int insertEduFlowRefrence(EduFlowRefrence eduFlowRefrence)
    {
        return eduFlowRefrenceMapper.insertEduFlowRefrence(eduFlowRefrence);
    }

    /**
     * 修改流程图
     * 
     * @param eduFlowRefrence 流程图
     * @return 结果
     */
    @Override
    public int updateEduFlowRefrence(EduFlowRefrence eduFlowRefrence)
    {
        return eduFlowRefrenceMapper.updateEduFlowRefrence(eduFlowRefrence);
    }

    /**
     * 批量删除流程图
     * 
     * @param ids 需要删除的流程图ID
     * @return 结果
     */
    @Override
    public int deleteEduFlowRefrenceByIds(Long[] ids)
    {
        return eduFlowRefrenceMapper.deleteEduFlowRefrenceByIds(ids);
    }

    /**
     * 删除流程图信息
     * 
     * @param id 流程图ID
     * @return 结果
     */
    @Override
    public int deleteEduFlowRefrenceById(Long id)
    {
        return eduFlowRefrenceMapper.deleteEduFlowRefrenceById(id);
    }
}
