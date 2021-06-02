package com.xjy.edu.service;

import java.util.List;
import com.xjy.edu.domain.City;

/**
 * 国内主要城市信息Service接口
 * 
 * @author wuzh
 * @date 2021-05-31
 */
public interface ICityService 
{
    /**
     * 查询国内主要城市信息
     * 
     * @param cityId 国内主要城市信息ID
     * @return 国内主要城市信息
     */
        City selectCityById(Long cityId);

    /**
     * 查询国内主要城市信息列表
     * 
     * @param city 国内主要城市信息
     * @return 国内主要城市信息集合
     */
    List<City> selectCityList(City city);

    /**
     * 查询国内主要城市信息列表
     *
     * @param keyword 国内主要城市信息
     * @return 国内主要城市信息集合
     */
    List<City> selectCityListByKeyword(String keyword);
    /**
     * 新增国内主要城市信息
     * 
     * @param city 国内主要城市信息
     * @return 结果
     */
    int insertCity(City city);

    /**
     * 修改国内主要城市信息
     * 
     * @param city 国内主要城市信息
     * @return 结果
     */
    int updateCity(City city);

    /**
     * 批量删除国内主要城市信息
     * 
     * @param cityIds 需要删除的国内主要城市信息ID
     * @return 结果
     */
    int deleteCityByIds(Long[] cityIds);

    /**
     * 删除国内主要城市信息信息
     * 
     * @param cityId 国内主要城市信息ID
     * @return 结果
     */
    int deleteCityById(Long cityId);
}
