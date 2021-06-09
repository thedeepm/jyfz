package com.xjy.edu.service.impl;

import com.xjy.edu.domain.BaseSearchEntity;
import com.xjy.edu.domain.City;
import com.xjy.edu.mapper.CityMapper;
import com.xjy.edu.mapper.SearchGeoMapper;
import com.xjy.edu.service.ICityService;
import com.xjy.edu.service.ISearchGeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 国内主要城市信息Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-31
 */
@Service
public class SearchGeoServiceImpl implements ISearchGeoService
{
    @Autowired
    private SearchGeoMapper searchGeoMapper;

    /**
     * 查询国内主要城市信息
     * 
     * @param baseSearchEntity 国内主要城市信息ID
     * @return 国内主要城市信息
     */
    @Override
    public List<BaseSearchEntity> selectSearchAllList(BaseSearchEntity baseSearchEntity)
    {
        return searchGeoMapper.selectSearchAllList(baseSearchEntity);
    }
}
