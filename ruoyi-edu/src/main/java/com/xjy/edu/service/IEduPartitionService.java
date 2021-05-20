package com.xjy.edu.service;

import java.util.List;
import com.xjy.edu.domain.EduPartition;

/**
 * 分区Service接口
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public interface IEduPartitionService 
{
    /**
     * 查询分区
     * 
     * @param id 分区ID
     * @return 分区
     */
        EduPartition selectEduPartitionById(Long id);

    /**
     * 查询分区列表
     * 
     * @param eduPartition 分区
     * @return 分区集合
     */
    List<EduPartition> selectEduPartitionList(EduPartition eduPartition);

    /**
     * 新增分区
     * 
     * @param eduPartition 分区
     * @return 结果
     */
    int insertEduPartition(EduPartition eduPartition);

    /**
     * 修改分区
     * 
     * @param eduPartition 分区
     * @return 结果
     */
    int updateEduPartition(EduPartition eduPartition);

    /**
     * 批量删除分区
     * 
     * @param ids 需要删除的分区ID
     * @return 结果
     */
    int deleteEduPartitionByIds(Long[] ids);

    /**
     * 删除分区信息
     * 
     * @param id 分区ID
     * @return 结果
     */
    int deleteEduPartitionById(Long id);
}
