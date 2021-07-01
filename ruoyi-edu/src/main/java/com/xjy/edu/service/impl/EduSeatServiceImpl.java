package com.xjy.edu.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjy.edu.mapper.EduSeatMapper;
import com.xjy.edu.domain.EduSeat;
import com.xjy.edu.service.IEduSeatService;

/**
 * 席位Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@Service
public class EduSeatServiceImpl implements IEduSeatService 
{
    @Autowired
    private EduSeatMapper eduSeatMapper;

    /**
     * 查询席位
     * 
     * @param id 席位ID
     * @return 席位
     */
    @Override
    public EduSeat selectEduSeatById(Long id)
    {
        return eduSeatMapper.selectEduSeatById(id);
    }

    /**
     * 查询席位列表
     * 
     * @param eduSeat 席位
     * @return 席位
     */
    @Override
    public List<EduSeat> selectEduSeatList(EduSeat eduSeat)
    {
        return eduSeatMapper.selectEduSeatList(eduSeat);
    }
    
    /**
     * 获取最新席位
     *
     * @return 席位
     */
    @Override
    public EduSeat getLastEduSeat(){
        return eduSeatMapper.getLastEduSeat();
    };

    /**
     * 新增席位
     * 
     * @param eduSeat 席位
     * @return 结果
     */
    @Override
    public int insertEduSeat(EduSeat eduSeat)
    {
        eduSeat.setCreateTime(DateUtils.getNowDate());
        return eduSeatMapper.insertEduSeat(eduSeat);
    }

    /**
     * 修改席位
     * 
     * @param eduSeat 席位
     * @return 结果
     */
    @Override
    public int updateEduSeat(EduSeat eduSeat)
    {
        return eduSeatMapper.updateEduSeat(eduSeat);
    }

    /**
     * 批量删除席位
     * 
     * @param ids 需要删除的席位ID
     * @return 结果
     */
    @Override
    public int deleteEduSeatByIds(Long[] ids)
    {
        return eduSeatMapper.deleteEduSeatByIds(ids);
    }

    /**
     * 删除席位信息
     * 
     * @param id 席位ID
     * @return 结果
     */
    @Override
    public int deleteEduSeatById(Long id)
    {
        return eduSeatMapper.deleteEduSeatById(id);
    }
}
