package com.xjy.edu.domain.vo;

import com.xjy.edu.domain.*;

import java.util.List;

/**
 * 模板对象 edu_template
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public class EduFlowRequestVo extends EduFlow
{
    private static final long serialVersionUID = 1L;

    public List<EduTask> eduTaskList;

    public Long templateId;

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public List<EduTask> getEduTaskList() {
        return eduTaskList;
    }

    public void setEduTaskList(List<EduTask> eduTaskList) {
        this.eduTaskList = eduTaskList;
    }

    public EduFlow getEduflow(){
        EduFlow eduFlow = new EduFlow();
        eduFlow.setFileId(this.getFileId());
        eduFlow.setFlowName(this.getFlowName());
        eduFlow.setStepNumber(this.getStepNumber());
        return eduFlow;
    }
}
