package com.xjy.edu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjy.edu.mapper.EduPartitionMapper;
import com.xjy.edu.domain.EduPartition;
import com.xjy.edu.service.IEduPartitionService;

/**
 * 分区Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@Service
public class EduPartitionServiceImpl implements IEduPartitionService 
{
    @Autowired(required = false)
    private EduPartitionMapper eduPartitionMapper;

    /**
     * 查询分区
     * 
     * @param id 分区ID
     * @return 分区
     */
    @Override
    public EduPartition selectEduPartitionById(Long id)
    {
        return eduPartitionMapper.selectEduPartitionById(id);
    }

    /**
     * 查询分区列表
     * 
     * @param eduPartition 分区
     * @return 分区
     */
    @Override
    public List<EduPartition> selectEduPartitionList(EduPartition eduPartition)
    {
        return eduPartitionMapper.selectEduPartitionList(eduPartition);
    }

    /**
     * 新增分区
     * 
     * @param eduPartition 分区
     * @return 结果
     */
    @Override
    public int insertEduPartition(EduPartition eduPartition)
    {
        return eduPartitionMapper.insertEduPartition(eduPartition);
    }

    /**
     * 修改分区
     * 
     * @param eduPartition 分区
     * @return 结果
     */
    @Override
    public int updateEduPartition(EduPartition eduPartition)
    {
        return eduPartitionMapper.updateEduPartition(eduPartition);
    }

    /**
     * 批量删除分区
     * 
     * @param ids 需要删除的分区ID
     * @return 结果
     */
    @Override
    public int deleteEduPartitionByIds(Long[] ids)
    {
        return eduPartitionMapper.deleteEduPartitionByIds(ids);
    }

    /**
     * 删除分区信息
     * 
     * @param id 分区ID
     * @return 结果
     */
    @Override
    public int deleteEduPartitionById(Long id)
    {
        return eduPartitionMapper.deleteEduPartitionById(id);
    }
}
