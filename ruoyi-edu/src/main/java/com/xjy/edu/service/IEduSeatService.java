package com.xjy.edu.service;

import java.util.List;
import com.xjy.edu.domain.EduSeat;

/**
 * 席位Service接口
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public interface IEduSeatService 
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
     * 批量删除席位
     * 
     * @param ids 需要删除的席位ID
     * @return 结果
     */
    int deleteEduSeatByIds(Long[] ids);

    /**
     * 删除席位信息
     * 
     * @param id 席位ID
     * @return 结果
     */
    int deleteEduSeatById(Long id);
}
