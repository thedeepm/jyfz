package com.xjy.edu.service.impl;

import java.util.List;

import com.xjy.edu.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjy.edu.mapper.PortMapper;
import com.xjy.edu.domain.Port;
import com.xjy.edu.service.IPortService;

/**
 * 国内外主要港口信息Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-31
 */
@Service
public class PortServiceImpl implements IPortService 
{
    @Autowired
    private PortMapper portMapper;

    /**
     * 查询国内外主要港口信息
     * 
     * @param portId 国内外主要港口信息ID
     * @return 国内外主要港口信息
     */
    @Override
    public Port selectPortById(Long portId)
    {
        return portMapper.selectPortById(portId);
    }

    /**
     * 查询国内外主要港口信息列表
     * 
     * @param port 国内外主要港口信息
     * @return 国内外主要港口信息
     */
    @Override
    public List<Port> selectPortList(Port port)
    {
        return portMapper.selectPortList(port);
    }

    @Override
    public List<Port> selectPortListByKeyword(String keyword) {
        return null;
    }

    /**
     * 新增国内外主要港口信息
     * 
     * @param port 国内外主要港口信息
     * @return 结果
     */
    @Override
    public int insertPort(Port port)
    {
        return portMapper.insertPort(port);
    }

    /**
     * 修改国内外主要港口信息
     * 
     * @param port 国内外主要港口信息
     * @return 结果
     */
    @Override
    public int updatePort(Port port)
    {
        return portMapper.updatePort(port);
    }

    /**
     * 批量删除国内外主要港口信息
     * 
     * @param portIds 需要删除的国内外主要港口信息ID
     * @return 结果
     */
    @Override
    public int deletePortByIds(Long[] portIds)
    {
        return portMapper.deletePortByIds(portIds);
    }

    /**
     * 删除国内外主要港口信息信息
     * 
     * @param portId 国内外主要港口信息ID
     * @return 结果
     */
    @Override
    public int deletePortById(Long portId)
    {
        return portMapper.deletePortById(portId);
    }
}
