package com.xjy.edu.service.impl;

import java.util.List;

import com.xjy.edu.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjy.edu.mapper.ProvinceMapper;
import com.xjy.edu.domain.Province;
import com.xjy.edu.service.IProvinceService;

/**
 * 国内主要省份信息Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-31
 */
@Service
public class ProvinceServiceImpl implements IProvinceService 
{
    @Autowired
    private ProvinceMapper provinceMapper;

    /**
     * 查询国内主要省份信息
     * 
     * @param provinceId 国内主要省份信息ID
     * @return 国内主要省份信息
     */
    @Override
    public Province selectProvinceById(Long provinceId)
    {
        return provinceMapper.selectProvinceById(provinceId);
    }

    /**
     * 查询国内主要省份信息列表
     * 
     * @param province 国内主要省份信息
     * @return 国内主要省份信息
     */
    @Override
    public List<Province> selectProvinceList(Province province)
    {
        return provinceMapper.selectProvinceList(province);
    }

    @Override
    public List<Province> selectProvinceListByKeyword(String keyword) {
        return null;
    }

    /**
     * 新增国内主要省份信息
     * 
     * @param province 国内主要省份信息
     * @return 结果
     */
    @Override
    public int insertProvince(Province province)
    {
        return provinceMapper.insertProvince(province);
    }

    /**
     * 修改国内主要省份信息
     * 
     * @param province 国内主要省份信息
     * @return 结果
     */
    @Override
    public int updateProvince(Province province)
    {
        return provinceMapper.updateProvince(province);
    }

    /**
     * 批量删除国内主要省份信息
     * 
     * @param provinceIds 需要删除的国内主要省份信息ID
     * @return 结果
     */
    @Override
    public int deleteProvinceByIds(Long[] provinceIds)
    {
        return provinceMapper.deleteProvinceByIds(provinceIds);
    }

    /**
     * 删除国内主要省份信息信息
     * 
     * @param provinceId 国内主要省份信息ID
     * @return 结果
     */
    @Override
    public int deleteProvinceById(Long provinceId)
    {
        return provinceMapper.deleteProvinceById(provinceId);
    }
}
