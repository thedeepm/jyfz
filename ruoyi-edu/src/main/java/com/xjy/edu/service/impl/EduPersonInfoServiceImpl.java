package com.xjy.edu.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjy.edu.mapper.EduPersonInfoMapper;
import com.xjy.edu.domain.EduPersonInfo;
import com.xjy.edu.service.IEduPersonInfoService;

/**
 * 人员信息Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-28
 */
@Service
public class EduPersonInfoServiceImpl implements IEduPersonInfoService 
{
    @Autowired
    private EduPersonInfoMapper eduPersonInfoMapper;

    /**
     * 查询人员信息
     * 
     * @param id 人员信息ID
     * @return 人员信息
     */
    @Override
    public EduPersonInfo selectEduPersonInfoById(Long id)
    {
        return eduPersonInfoMapper.selectEduPersonInfoById(id);
    }

    /**
     * 查询人员信息列表
     * 
     * @param eduPersonInfo 人员信息
     * @return 人员信息
     */
    @Override
    public List<EduPersonInfo> selectEduPersonInfoList(EduPersonInfo eduPersonInfo)
    {
        return eduPersonInfoMapper.selectEduPersonInfoList(eduPersonInfo);
    }

    /**
     * 新增人员信息
     * 
     * @param eduPersonInfo 人员信息
     * @return 结果
     */
    @Override
    public int insertEduPersonInfo(EduPersonInfo eduPersonInfo)
    {
        eduPersonInfo.setCreateTime(DateUtils.getNowDate());
        return eduPersonInfoMapper.insertEduPersonInfo(eduPersonInfo);
    }

    /**
     * 修改人员信息
     * 
     * @param eduPersonInfo 人员信息
     * @return 结果
     */
    @Override
    public int updateEduPersonInfo(EduPersonInfo eduPersonInfo)
    {
        eduPersonInfo.setUpdateTime(DateUtils.getNowDate());
        return eduPersonInfoMapper.updateEduPersonInfo(eduPersonInfo);
    }

    /**
     * 批量删除人员信息
     * 
     * @param ids 需要删除的人员信息ID
     * @return 结果
     */
    @Override
    public int deleteEduPersonInfoByIds(Long[] ids)
    {
        return eduPersonInfoMapper.deleteEduPersonInfoByIds(ids);
    }

    /**
     * 删除人员信息信息
     * 
     * @param id 人员信息ID
     * @return 结果
     */
    @Override
    public int deleteEduPersonInfoById(Long id)
    {
        return eduPersonInfoMapper.deleteEduPersonInfoById(id);
    }
}
