package com.xjy.edu.service;

import java.util.List;

import com.xjy.edu.domain.City;
import com.xjy.edu.domain.Country;

/**
 * 我国周边国家信息Service接口
 * 
 * @author wuzh
 * @date 2021-05-31
 */
public interface ICountryService 
{
    /**
     * 查询我国周边国家信息
     * 
     * @param countryId 我国周边国家信息ID
     * @return 我国周边国家信息
     */
        Country selectCountryById(Long countryId);

    /**
     * 查询我国周边国家信息列表
     * 
     * @param country 我国周边国家信息
     * @return 我国周边国家信息集合
     */
    List<Country> selectCountryList(Country country);

    /**
     * 查询我国周边国家信息列表
     *
     * @param keyword 我国周边国家信息
     * @return 我国周边国家信息集合
     */
    List<Country> selectCountryListByKeyword(String keyword);

    /**
     * 新增我国周边国家信息
     * 
     * @param country 我国周边国家信息
     * @return 结果
     */
    int insertCountry(Country country);

    /**
     * 修改我国周边国家信息
     * 
     * @param country 我国周边国家信息
     * @return 结果
     */
    int updateCountry(Country country);

    /**
     * 批量删除我国周边国家信息
     * 
     * @param countryIds 需要删除的我国周边国家信息ID
     * @return 结果
     */
    int deleteCountryByIds(Long[] countryIds);

    /**
     * 删除我国周边国家信息信息
     * 
     * @param countryId 我国周边国家信息ID
     * @return 结果
     */
    int deleteCountryById(Long countryId);
}
