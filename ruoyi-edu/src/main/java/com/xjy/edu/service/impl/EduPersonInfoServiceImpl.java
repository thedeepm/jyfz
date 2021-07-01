package com.xjy.edu.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(EduPersonInfoServiceImpl.class);

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

    /**
     * 导入人员信息数据
     *
     * @param personInfoList 人员信息数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importPersonInfo(List<EduPersonInfo> personInfoList, Boolean isUpdateSupport){

        if (StringUtils.isNull(personInfoList) || personInfoList.size() == 0)
        {
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (EduPersonInfo eduPersonInfo : personInfoList)
        {
            try
            {
                EduPersonInfo personInfo = eduPersonInfoMapper.selectEduPersonInfoByUserName(eduPersonInfo.getPersonName());
                if (StringUtils.isNull(personInfo))
                {
                    this.insertEduPersonInfo(eduPersonInfo);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、人员名 " + eduPersonInfo.getPersonName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    this.updateEduPersonInfo(eduPersonInfo);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、人员名 " + eduPersonInfo.getPersonName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、人员名 " + eduPersonInfo.getPersonName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、人员名 " + eduPersonInfo.getPersonName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
