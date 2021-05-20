package com.xjy.edu.mapper;

import java.util.List;
import com.xjy.edu.domain.EduTemplate;

/**
 * 模板Mapper接口
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public interface EduTemplateMapper 
{
    /**
     * 查询模板
     * 
     * @param id 模板ID
     * @return 模板
     */
        EduTemplate selectEduTemplateById(Long id);

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
     * @param eduTemplate 模板
     * @return 结果
     */
    int insertEduTemplate(EduTemplate eduTemplate);

    /**
     * 修改模板
     * 
     * @param eduTemplate 模板
     * @return 结果
     */
    int updateEduTemplate(EduTemplate eduTemplate);

    /**
     * 删除模板
     * 
     * @param id 模板ID
     * @return 结果
     */
    int deleteEduTemplateById(Long id);

    /**
     * 批量删除模板
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteEduTemplateByIds(Long[] ids);
}
