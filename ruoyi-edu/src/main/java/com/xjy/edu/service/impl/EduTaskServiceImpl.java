package com.xjy.edu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import com.xjy.edu.domain.EduPersonInfo;
import com.xjy.edu.domain.EduSeat;
import com.xjy.edu.mapper.EduPersonInfoMapper;
import com.xjy.edu.mapper.EduSeatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjy.edu.mapper.EduTaskMapper;
import com.xjy.edu.domain.EduTask;
import com.xjy.edu.service.IEduTaskService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 任务Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@Service
public class EduTaskServiceImpl implements IEduTaskService 
{
    @Autowired
    private EduTaskMapper eduTaskMapper;

    @Autowired
    private EduSeatMapper eduSeatMapper;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private EduPersonInfoMapper eduPersonInfoMapper;
    /**
     * 查询任务
     * 
     * @param id 任务ID
     * @return 任务
     */
    @Override
    public EduTask selectEduTaskById(Long id)
    {
        return eduTaskMapper.selectEduTaskById(id);
    }

    /**
     * 查询任务列表
     * 
     * @param eduTask 任务
     * @return 任务
     */
    @Override
    public List<EduTask> selectEduTaskList(EduTask eduTask)
    {
        return eduTaskMapper.selectEduTaskList(eduTask);
    }

    /**
     * 新增任务
     * 
     * @param eduTask 任务
     * @return 结果
     */
    @Override
    @Transactional
    public EduTask insertEduTask(EduTask eduTask, AjaxResult ajaxResult)
    {
        EduPersonInfo eduPersonInfo = new EduPersonInfo();
        eduPersonInfo.setId(eduTask.getPersonId());
        EduSeat eduSeat;
        eduTask.setCreateTime(DateUtils.getNowDate());
        int rows = eduTaskMapper.insertEduTask(eduTask);
        if(rows != 0){
            eduTask = eduTaskMapper.getLastEduTask();
            eduSeat = eduSeatMapper.selectEduSeatById(eduTask.getSeatId());
            eduSeat.setOccupied(true);
            eduSeatMapper.updateEduSeat(eduSeat);
            StringBuilder userName = new StringBuilder(32);
            userName.append(eduTask.getPartitionId());
            userName.append(eduTask.getGroupId());
            userName.append(eduTask.getSeatId());
            userName.append(eduTask.getStepLevel());
            SysUser user = new SysUser();
            user.setUserName(userName.toString());
            user.setPassword(SecurityUtils.encryptPassword("123456"));
            user.setNickName("AutoGenerate");
            Long[] roles = new Long[]{100L};
            user.setRoleIds(roles);
            sysUserService.insertUser(user);
            eduPersonInfo.setUserId(user.getUserId());
            eduPersonInfo.setSeatId(eduSeat.getId());
            eduPersonInfoMapper.updateEduPersonInfo(eduPersonInfo);
            eduPersonInfo = eduPersonInfoMapper.selectEduPersonInfoById(eduPersonInfo.getId());
            ajaxResult.put("userName", user.getUserName());
            ajaxResult.put("eduPersonInfo", eduPersonInfo);
        }
        return eduTask;
    }

    /**
     * 修改任务
     * 
     * @param eduTask 任务
     * @return 结果
     */
    @Override
    public int updateEduTask(EduTask eduTask)
    {
        return eduTaskMapper.updateEduTask(eduTask);
    }

    /**
     * 批量删除任务
     * 
     * @param ids 需要删除的任务ID
     * @return 结果
     */
    @Override
    public int deleteEduTaskByIds(Long[] ids)
    {
        return eduTaskMapper.deleteEduTaskByIds(ids);
    }

    /**
     * 删除任务信息
     * 
     * @param id 任务ID
     * @return 结果
     */
    @Override
    public int deleteEduTaskById(Long id)
    {
        return eduTaskMapper.deleteEduTaskById(id);
    }
}
