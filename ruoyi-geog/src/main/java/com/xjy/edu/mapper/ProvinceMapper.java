package com.xjy.edu.mapper;

import java.util.List;
import com.xjy.edu.domain.Province;

/**
 * 国内主要省份信息Mapper接口
 * 
 * @author wuzh
 * @date 2021-05-31
 */
public interface ProvinceMapper 
{
    /**
     * 查询国内主要省份信息
     * 
     * @param provinceId 国内主要省份信息ID
     * @return 国内主要省份信息
     */
        Province selectProvinceById(Long provinceId);

    /**
     * 查询国内主要省份信息列表
     * 
     * @param province 国内主要省份信息
     * @return 国内主要省份信息集合
     */
    List<Province> selectProvinceList(Province province);

    /**
     * 查询国内主要省份信息列表
     *
     * @param keyword 国内主要省份信息
     * @return 国内主要省份信息集合
     */
    List<Province> selectProvinceListByKeyword(String keyword);
    /**
     * 新增国内主要省份信息
     * 
     * @param province 国内主要省份信息
     * @return 结果
     */
    int insertProvince(Province province);

    /**
     * 修改国内主要省份信息
     * 
     * @param province 国内主要省份信息
     * @return 结果
     */
    int updateProvince(Province province);

    /**
     * 删除国内主要省份信息
     * 
     * @param provinceId 国内主要省份信息ID
     * @return 结果
     */
    int deleteProvinceById(Long provinceId);

    /**
     * 批量删除国内主要省份信息
     * 
     * @param provinceIds 需要删除的数据ID
     * @return 结果
     */
    int deleteProvinceByIds(Long[] provinceIds);
}
