package com.xjy.edu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 案例任务关联对象 edu_case_task
 * 
 * @author wuzh
 * @date 2021-05-29
 */
public class EduCaseTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 关联案例id */
    @Excel(name = "关联案例id")
    private Long caseId;

    /** 关联任务id */
    @Excel(name = "关联任务id")
    private Long taskId;

    /** 是否完成 */
    @Excel(name = "是否完成")
    private Long completed;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCaseId(Long caseId) 
    {
        this.caseId = caseId;
    }

    public Long getCaseId() 
    {
        return caseId;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setCompleted(Long completed) 
    {
        this.completed = completed;
    }

    public Long getCompleted() 
    {
        return completed;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("caseId", getCaseId())
            .append("taskId", getTaskId())
            .append("completed", getCompleted())
            .toString();
    }
}
