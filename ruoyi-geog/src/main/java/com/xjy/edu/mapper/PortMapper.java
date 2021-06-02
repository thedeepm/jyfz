package com.xjy.edu.mapper;

import java.util.List;
import com.xjy.edu.domain.Port;

/**
 * 国内外主要港口信息Mapper接口
 * 
 * @author wuzh
 * @date 2021-05-31
 */
public interface PortMapper 
{
    /**
     * 查询国内外主要港口信息
     * 
     * @param portId 国内外主要港口信息ID
     * @return 国内外主要港口信息
     */
        Port selectPortById(Long portId);

    /**
     * 查询国内外主要港口信息列表
     * 
     * @param port 国内外主要港口信息
     * @return 国内外主要港口信息集合
     */
    List<Port> selectPortList(Port port);

    /**
     * 查询国内外主要港口信息列表
     *
     * @param keyword 国内外主要港口信息
     * @return 国内外主要港口信息集合
     */
    List<Port> selectPortListByKeyword(String keyword);
    /**
     * 新增国内外主要港口信息
     * 
     * @param port 国内外主要港口信息
     * @return 结果
     */
    int insertPort(Port port);

    /**
     * 修改国内外主要港口信息
     * 
     * @param port 国内外主要港口信息
     * @return 结果
     */
    int updatePort(Port port);

    /**
     * 删除国内外主要港口信息
     * 
     * @param portId 国内外主要港口信息ID
     * @return 结果
     */
    int deletePortById(Long portId);

    /**
     * 批量删除国内外主要港口信息
     * 
     * @param portIds 需要删除的数据ID
     * @return 结果
     */
    int deletePortByIds(Long[] portIds);
}
