package com.xjy.edu.mapper;

import java.util.List;
import com.xjy.edu.domain.EduSeat;

/**
 * 席位Mapper接口
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public interface EduSeatMapper 
{
    /**
     * 查询席位
     * 
     * @param id 席位ID
     * @return 席位
     */
        EduSeat selectEduSeatById(Long id);

    /**
     * 查询席位列表
     * 
     * @param eduSeat 席位
     * @return 席位集合
     */
    List<EduSeat> selectEduSeatList(EduSeat eduSeat);

    /**
     * 新增席位
     * 
     * @param eduSeat 席位
     * @return 结果
     */
    int insertEduSeat(EduSeat eduSeat);

    /**
     * 修改席位
     * 
     * @param eduSeat 席位
     * @return 结果
     */
    int updateEduSeat(EduSeat eduSeat);

    /**
     * 删除席位
     * 
     * @param id 席位ID
     * @return 结果
     */
    int deleteEduSeatById(Long id);

    /**
     * 批量删除席位
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteEduSeatByIds(Long[] ids);
}
