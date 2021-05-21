package com.xjy.edu.mapper;

import java.util.List;
import com.xjy.edu.domain.EduGroup;
import com.xjy.edu.domain.EduSeat;

/**
 * 分组Mapper接口
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public interface EduGroupMapper 
{
    /**
     * 查询分组
     * 
     * @param id 分组ID
     * @return 分组
     */
        EduGroup selectEduGroupById(Long id);

    /**
     * 获取最新分组
     *
     * @return 分组
     */
    EduGroup getLastEduGroup();

    /**
     * 查询分组列表
     * 
     * @param eduGroup 分组
     * @return 分组集合
     */
    List<EduGroup> selectEduGroupList(EduGroup eduGroup);

    /**
     * 新增分组
     * 
     * @param eduGroup 分组
     * @return 结果
     */
    int insertEduGroup(EduGroup eduGroup);

    /**
     * 修改分组
     * 
     * @param eduGroup 分组
     * @return 结果
     */
    int updateEduGroup(EduGroup eduGroup);

    /**
     * 删除分组
     * 
     * @param id 分组ID
     * @return 结果
     */
    int deleteEduGroupById(Long id);

    /**
     * 批量删除分组
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteEduGroupByIds(Long[] ids);
}
