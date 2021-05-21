package com.xjy.edu.domain.vo;

import com.xjy.edu.domain.EduGroup;
import com.xjy.edu.domain.EduPartition;
import com.xjy.edu.domain.EduTemplate;

import java.util.List;

/**
 * 模板对象 edu_template
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public class EduGroupRequestVo extends EduPartition
{
    private static final long serialVersionUID = 1L;

    public List<EduGroup> eduGroupList;

    public List<EduGroup> getEduGroupList() {
        return eduGroupList;
    }

    public void setEduGroupList(List<EduGroup> eduGroupList) {
        this.eduGroupList = eduGroupList;
    }
}
