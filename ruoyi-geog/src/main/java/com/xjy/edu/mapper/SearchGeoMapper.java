package com.xjy.edu.mapper;

import com.xjy.edu.domain.BaseSearchEntity;
import com.xjy.edu.domain.City;

import java.util.List;

/**
 * 国内主要城市信息Mapper接口
 * 
 * @author wuzh
 * @date 2021-05-31
 */
public interface SearchGeoMapper
{
    /**
     * 查询国内主要城市信息列表
     * 
     * @param baseSearchEntity 国内主要城市信息
     * @return 国内主要城市信息集合
     */
    List<BaseSearchEntity> selectSearchAllList(BaseSearchEntity baseSearchEntity);
}
