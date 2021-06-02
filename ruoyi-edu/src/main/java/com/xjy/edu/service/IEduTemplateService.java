package com.xjy.edu.service;

import java.util.List;
import com.xjy.edu.domain.EduTemplate;
import com.xjy.edu.domain.vo.EduTemplateRequestVo;

/**
 * 模板Service接口
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public interface IEduTemplateService 
{
    /**
     * 查询模板
     * 
     * @param id 模板ID
     * @return 模板
     */
        EduTemplate selectEduTemplateById(Long id);
    /**
     * 查询最近的模板
     *
     * @return 模板
     */
    EduTemplate getLastEduTemplate();

    /**
     * 查询模板列表
     * 
     * @param eduTemplate 模板
     * @return 模板集合
     */
    List<EduTemplate> selectEduTemplateList(EduTemplate eduTemplate);

    /**
     * 新增模板
     * 
     * @param eduTemplateVo 模板
     * @return 结果
     */
    int insertEduTemplate(EduTemplateRequestVo eduTemplateVo);

    /**
     * 修改模板
     * 
     * @param eduTemplateVo 模板
     * @return 结果
     */
    int updateEduTemplate(EduTemplateRequestVo eduTemplateVo);

    /**
     * 批量删除模板
     * 
     * @param ids 需要删除的模板ID
     * @return 结果
     */
    int deleteEduTemplateByIds(Long[] ids);

    /**
     * 删除模板信息
     * 
     * @param id 模板ID
     * @return 结果
     */
    int deleteEduTemplateById(Long id);
}
