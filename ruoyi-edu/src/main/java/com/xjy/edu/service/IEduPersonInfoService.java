package com.xjy.edu.service;

import java.util.List;
import com.xjy.edu.domain.EduPersonInfo;

/**
 * 人员信息Service接口
 * 
 * @author wuzh
 * @date 2021-05-28
 */
public interface IEduPersonInfoService 
{
    /**
     * 查询人员信息
     * 
     * @param id 人员信息ID
     * @return 人员信息
     */
        EduPersonInfo selectEduPersonInfoById(Long id);

    /**
     * 查询人员信息列表
     * 
     * @param eduPersonInfo 人员信息
     * @return 人员信息集合
     */
    List<EduPersonInfo> selectEduPersonInfoList(EduPersonInfo eduPersonInfo);

    /**
     * 新增人员信息
     * 
     * @param eduPersonInfo 人员信息
     * @return 结果
     */
    int insertEduPersonInfo(EduPersonInfo eduPersonInfo);

    /**
     * 修改人员信息
     * 
     * @param eduPersonInfo 人员信息
     * @return 结果
     */
    int updateEduPersonInfo(EduPersonInfo eduPersonInfo);

    /**
     * 批量删除人员信息
     * 
     * @param ids 需要删除的人员信息ID
     * @return 结果
     */
    int deleteEduPersonInfoByIds(Long[] ids);

    /**
     * 删除人员信息信息
     * 
     * @param id 人员信息ID
     * @return 结果
     */
    int deleteEduPersonInfoById(Long id);
}
