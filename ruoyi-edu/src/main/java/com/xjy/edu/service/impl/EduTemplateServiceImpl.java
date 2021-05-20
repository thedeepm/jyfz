package com.xjy.edu.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjy.edu.mapper.EduTemplateMapper;
import com.xjy.edu.domain.EduTemplate;
import com.xjy.edu.service.IEduTemplateService;

/**
 * 模板Service业务层处理
 * 
 * @author wuzh
 * @date 2021-05-19
 */
@Service
public class EduTemplateServiceImpl implements IEduTemplateService 
{
    @Autowired(required = false)
    private EduTemplateMapper eduTemplateMapper;

    /**
     * 查询模板
     * 
     * @param id 模板ID
     * @return 模板
     */
    @Override
    public EduTemplate selectEduTemplateById(Long id)
    {
        return eduTemplateMapper.selectEduTemplateById(id);
    }

    /**
     * 查询模板列表
     * 
     * @param eduTemplate 模板
     * @return 模板
     */
    @Override
    public List<EduTemplate> selectEduTemplateList(EduTemplate eduTemplate)
    {
        return eduTemplateMapper.selectEduTemplateList(eduTemplate);
    }

    /**
     * 新增模板
     * 
     * @param eduTemplate 模板
     * @return 结果
     */
    @Override
    public int insertEduTemplate(EduTemplate eduTemplate)
    {
        eduTemplate.setCreateTime(DateUtils.getNowDate());
        return eduTemplateMapper.insertEduTemplate(eduTemplate);
    }

    /**
     * 修改模板
     * 
     * @param eduTemplate 模板
     * @return 结果
     */
    @Override
    public int updateEduTemplate(EduTemplate eduTemplate)
    {
        eduTemplate.setUpdateTime(DateUtils.getNowDate());
        return eduTemplateMapper.updateEduTemplate(eduTemplate);
    }

    /**
     * 批量删除模板
     * 
     * @param ids 需要删除的模板ID
     * @return 结果
     */
    @Override
    public int deleteEduTemplateByIds(Long[] ids)
    {
        return eduTemplateMapper.deleteEduTemplateByIds(ids);
    }

    /**
     * 删除模板信息
     * 
     * @param id 模板ID
     * @return 结果
     */
    @Override
    public int deleteEduTemplateById(Long id)
    {
        return eduTemplateMapper.deleteEduTemplateById(id);
    }
}
