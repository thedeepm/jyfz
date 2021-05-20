package com.xjy.edu.service;

import java.util.List;
import com.xjy.edu.domain.EduCase;

/**
 * 案例Service接口
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public interface IEduCaseService 
{
    /**
     * 查询案例
     * 
     * @param id 案例ID
     * @return 案例
     */
        EduCase selectEduCaseById(Long id);

    /**
     * 查询案例列表
     * 
     * @param eduCase 案例
     * @return 案例集合
     */
    List<EduCase> selectEduCaseList(EduCase eduCase);

    /**
     * 新增案例
     * 
     * @param eduCase 案例
     * @return 结果
     */
    int insertEduCase(EduCase eduCase);

    /**
     * 修改案例
     * 
     * @param eduCase 案例
     * @return 结果
     */
    int updateEduCase(EduCase eduCase);

    /**
     * 批量删除案例
     * 
     * @param ids 需要删除的案例ID
     * @return 结果
     */
    int deleteEduCaseByIds(Long[] ids);

    /**
     * 删除案例信息
     * 
     * @param id 案例ID
     * @return 结果
     */
    int deleteEduCaseById(Long id);
}
