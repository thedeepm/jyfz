package com.xjy.edu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjy.edu.mapper.CityMapper;
import com.xjy.edu.domain.City;
import com.xjy.edu.service.ICityService;

/**
 * 国内主要城市信息Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-31
 */
@Service
public class CityServiceImpl implements ICityService 
{
    @Autowired
    private CityMapper cityMapper;

    /**
     * 查询国内主要城市信息
     * 
     * @param cityId 国内主要城市信息ID
     * @return 国内主要城市信息
     */
    @Override
    public City selectCityById(Long cityId)
    {
        return cityMapper.selectCityById(cityId);
    }

    /**
     * 查询国内主要城市信息列表
     * 
     * @param city 国内主要城市信息
     * @return 国内主要城市信息
     */
    @Override
    public List<City> selectCityList(City city)
    {
        return cityMapper.selectCityList(city);
    }

    @Override
    public List<City> selectCityListByKeyword(String keyword) {
        return null;
    }

    /**
     * 新增国内主要城市信息
     * 
     * @param city 国内主要城市信息
     * @return 结果
     */
    @Override
    public int insertCity(City city)
    {
        return cityMapper.insertCity(city);
    }

    /**
     * 修改国内主要城市信息
     * 
     * @param city 国内主要城市信息
     * @return 结果
     */
    @Override
    public int updateCity(City city)
    {
        return cityMapper.updateCity(city);
    }

    /**
     * 批量删除国内主要城市信息
     * 
     * @param cityIds 需要删除的国内主要城市信息ID
     * @return 结果
     */
    @Override
    public int deleteCityByIds(Long[] cityIds)
    {
        return cityMapper.deleteCityByIds(cityIds);
    }

    /**
     * 删除国内主要城市信息信息
     * 
     * @param cityId 国内主要城市信息ID
     * @return 结果
     */
    @Override
    public int deleteCityById(Long cityId)
    {
        return cityMapper.deleteCityById(cityId);
    }
}
