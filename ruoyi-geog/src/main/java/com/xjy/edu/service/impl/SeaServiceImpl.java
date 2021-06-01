package com.xjy.edu.service.impl;

import java.util.List;

import com.xjy.edu.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjy.edu.mapper.SeaMapper;
import com.xjy.edu.domain.Sea;
import com.xjy.edu.service.ISeaService;

/**
 * 主要海洋基础信息Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-31
 */
@Service
public class SeaServiceImpl implements ISeaService 
{
    @Autowired
    private SeaMapper seaMapper;

    /**
     * 查询主要海洋基础信息
     * 
     * @param seaId 主要海洋基础信息ID
     * @return 主要海洋基础信息
     */
    @Override
    public Sea selectSeaById(Long seaId)
    {
        return seaMapper.selectSeaById(seaId);
    }

    /**
     * 查询主要海洋基础信息列表
     * 
     * @param sea 主要海洋基础信息
     * @return 主要海洋基础信息
     */
    @Override
    public List<Sea> selectSeaList(Sea sea)
    {
        return seaMapper.selectSeaList(sea);
    }

    @Override
    public List<Sea> selectSeaListByKeyword(String keyword) {
        return seaMapper.selectSeaListByKeyword(keyword);
    }

    /**
     * 新增主要海洋基础信息
     * 
     * @param sea 主要海洋基础信息
     * @return 结果
     */
    @Override
    public int insertSea(Sea sea)
    {
        return seaMapper.insertSea(sea);
    }

    /**
     * 修改主要海洋基础信息
     * 
     * @param sea 主要海洋基础信息
     * @return 结果
     */
    @Override
    public int updateSea(Sea sea)
    {
        return seaMapper.updateSea(sea);
    }

    /**
     * 批量删除主要海洋基础信息
     * 
     * @param seaIds 需要删除的主要海洋基础信息ID
     * @return 结果
     */
    @Override
    public int deleteSeaByIds(Long[] seaIds)
    {
        return seaMapper.deleteSeaByIds(seaIds);
    }

    /**
     * 删除主要海洋基础信息信息
     * 
     * @param seaId 主要海洋基础信息ID
     * @return 结果
     */
    @Override
    public int deleteSeaById(Long seaId)
    {
        return seaMapper.deleteSeaById(seaId);
    }
}
