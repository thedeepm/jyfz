package com.xjy.edu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjy.edu.mapper.EduGroupMapper;
import com.xjy.edu.domain.EduGroup;
import com.xjy.edu.service.IEduGroupService;

/**
 * 分组Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@Service
public class EduGroupServiceImpl implements IEduGroupService 
{
    @Autowired(required = false)
    private EduGroupMapper eduGroupMapper;

    /**
     * 查询分组
     * 
     * @param id 分组ID
     * @return 分组
     */
    @Override
    public EduGroup selectEduGroupById(Long id)
    {
        return eduGroupMapper.selectEduGroupById(id);
    }

    /**
     * 查询分组列表
     * 
     * @param eduGroup 分组
     * @return 分组
     */
    @Override
    public List<EduGroup> selectEduGroupList(EduGroup eduGroup)
    {
        return eduGroupMapper.selectEduGroupList(eduGroup);
    }

    /**
     * 新增分组
     * 
     * @param eduGroup 分组
     * @return 结果
     */
    @Override
    public int insertEduGroup(EduGroup eduGroup)
    {
        return eduGroupMapper.insertEduGroup(eduGroup);
    }

    /**
     * 修改分组
     * 
     * @param eduGroup 分组
     * @return 结果
     */
    @Override
    public int updateEduGroup(EduGroup eduGroup)
    {
        return eduGroupMapper.updateEduGroup(eduGroup);
    }

    /**
     * 批量删除分组
     * 
     * @param ids 需要删除的分组ID
     * @return 结果
     */
    @Override
    public int deleteEduGroupByIds(Long[] ids)
    {
        return eduGroupMapper.deleteEduGroupByIds(ids);
    }

    /**
     * 删除分组信息
     * 
     * @param id 分组ID
     * @return 结果
     */
    @Override
    public int deleteEduGroupById(Long id)
    {
        return eduGroupMapper.deleteEduGroupById(id);
    }
}
