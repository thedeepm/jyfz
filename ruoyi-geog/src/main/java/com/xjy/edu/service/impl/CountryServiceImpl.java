package com.xjy.edu.service.impl;

import java.util.List;

import com.xjy.edu.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjy.edu.mapper.CountryMapper;
import com.xjy.edu.domain.Country;
import com.xjy.edu.service.ICountryService;

/**
 * 我国周边国家信息Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-31
 */
@Service
public class CountryServiceImpl implements ICountryService 
{
    @Autowired
    private CountryMapper countryMapper;

    /**
     * 查询我国周边国家信息
     * 
     * @param countryId 我国周边国家信息ID
     * @return 我国周边国家信息
     */
    @Override
    public Country selectCountryById(Long countryId)
    {
        return countryMapper.selectCountryById(countryId);
    }

    /**
     * 查询我国周边国家信息列表
     * 
     * @param country 我国周边国家信息
     * @return 我国周边国家信息
     */
    @Override
    public List<Country> selectCountryList(Country country)
    {
        return countryMapper.selectCountryList(country);
    }

    @Override
    public List<Country> selectCountryListByKeyword(String keyword) {
        return countryMapper.selectCountryListByKeyword(keyword);
    }

    /**
     * 新增我国周边国家信息
     * 
     * @param country 我国周边国家信息
     * @return 结果
     */
    @Override
    public int insertCountry(Country country)
    {
        return countryMapper.insertCountry(country);
    }

    /**
     * 修改我国周边国家信息
     * 
     * @param country 我国周边国家信息
     * @return 结果
     */
    @Override
    public int updateCountry(Country country)
    {
        return countryMapper.updateCountry(country);
    }

    /**
     * 批量删除我国周边国家信息
     * 
     * @param countryIds 需要删除的我国周边国家信息ID
     * @return 结果
     */
    @Override
    public int deleteCountryByIds(Long[] countryIds)
    {
        return countryMapper.deleteCountryByIds(countryIds);
    }

    /**
     * 删除我国周边国家信息信息
     * 
     * @param countryId 我国周边国家信息ID
     * @return 结果
     */
    @Override
    public int deleteCountryById(Long countryId)
    {
        return countryMapper.deleteCountryById(countryId);
    }
}
