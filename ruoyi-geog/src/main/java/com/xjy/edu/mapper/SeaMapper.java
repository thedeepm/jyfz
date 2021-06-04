package com.xjy.edu.mapper;

import java.util.List;
import com.xjy.edu.domain.Sea;

/**
 * 主要海洋基础信息Mapper接口
 * 
 * @author wuzh
 * @date 2021-05-31
 */
public interface SeaMapper 
{
    /**
     * 查询主要海洋基础信息
     * 
     * @param seaId 主要海洋基础信息ID
     * @return 主要海洋基础信息
     */
        Sea selectSeaById(Long seaId);

    /**
     * 查询主要海洋基础信息列表
     * 
     * @param sea 主要海洋基础信息
     * @return 主要海洋基础信息集合
     */
    List<Sea> selectSeaList(Sea sea);

    /**
     * 查询主要海洋基础信息列表
     *
     * @param keyword 主要海洋基础信息
     * @return 主要海洋基础信息集合
     */
    List<Sea> selectSeaListByKeyword(String keyword);
    /**
     * 新增主要海洋基础信息
     * 
     * @param sea 主要海洋基础信息
     * @return 结果
     */
    int insertSea(Sea sea);

    /**
     * 修改主要海洋基础信息
     * 
     * @param sea 主要海洋基础信息
     * @return 结果
     */
    int updateSea(Sea sea);

    /**
     * 删除主要海洋基础信息
     * 
     * @param seaId 主要海洋基础信息ID
     * @return 结果
     */
    int deleteSeaById(Long seaId);

    /**
     * 批量删除主要海洋基础信息
     * 
     * @param seaIds 需要删除的数据ID
     * @return 结果
     */
    int deleteSeaByIds(Long[] seaIds);
}
