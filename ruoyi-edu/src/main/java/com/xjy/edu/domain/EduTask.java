package com.xjy.edu.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任务对象 edu_task
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public class EduTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务id */
    private Long id;

    /** 任务名 */
    @Excel(name = "任务名")
    private String taskName;

    /** 分区名 */
    @Excel(name = "分区名")
    private String partitionName;

    /** 分组名 */
    @Excel(name = "分组名")
    private String groupName;

    /** 席位编号 */
    @Excel(name = "席位编号")
    private Long seatId;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 预警时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预警时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date warningTime;

    /** 关联流程id */
    @Excel(name = "关联流程id")
    private Long flowId;

    /** 步骤层级 */
    @Excel(name = "步骤层级")
    private Long stepLevel;

    /** 是否完成（0否1是） */
    @Excel(name = "是否完成", readConverterExp = "0=否1是")
    private Long completed;

    /** $column.columnComment */
    @Excel(name = "是否完成", readConverterExp = "$column.readConverterExp()")
    private String tbc1;

    /** $column.columnComment */
    @Excel(name = "是否完成", readConverterExp = "$column.readConverterExp()")
    private String tbc2;

    /** $column.columnComment */
    @Excel(name = "是否完成", readConverterExp = "$column.readConverterExp()")
    private String tbc3;

    /** $column.columnComment */
    @Excel(name = "是否完成", readConverterExp = "$column.readConverterExp()")
    private String tbc4;

    /** $column.columnComment */
    @Excel(name = "是否完成", readConverterExp = "$column.readConverterExp()")
    private String tbc5;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTaskName(String taskName) 
    {
        this.taskName = taskName;
    }

    public String getTaskName() 
    {
        return taskName;
    }
    public void setPartitionName(String partitionName) 
    {
        this.partitionName = partitionName;
    }

    public String getPartitionName() 
    {
        return partitionName;
    }
    public void setGroupName(String groupName) 
    {
        this.groupName = groupName;
    }

    public String getGroupName() 
    {
        return groupName;
    }
    public void setSeatId(Long seatId) 
    {
        this.seatId = seatId;
    }

    public Long getSeatId() 
    {
        return seatId;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setWarningTime(Date warningTime) 
    {
        this.warningTime = warningTime;
    }

    public Date getWarningTime() 
    {
        return warningTime;
    }
    public void setFlowId(Long flowId) 
    {
        this.flowId = flowId;
    }

    public Long getFlowId() 
    {
        return flowId;
    }
    public void setStepLevel(Long stepLevel) 
    {
        this.stepLevel = stepLevel;
    }

    public Long getStepLevel() 
    {
        return stepLevel;
    }
    public void setCompleted(Long completed) 
    {
        this.completed = completed;
    }

    public Long getCompleted() 
    {
        return completed;
    }
    public void setTbc1(String tbc1) 
    {
        this.tbc1 = tbc1;
    }

    public String getTbc1() 
    {
        return tbc1;
    }
    public void setTbc2(String tbc2) 
    {
        this.tbc2 = tbc2;
    }

    public String getTbc2() 
    {
        return tbc2;
    }
    public void setTbc3(String tbc3) 
    {
        this.tbc3 = tbc3;
    }

    public String getTbc3() 
    {
        return tbc3;
    }
    public void setTbc4(String tbc4) 
    {
        this.tbc4 = tbc4;
    }

    public String getTbc4() 
    {
        return tbc4;
    }
    public void setTbc5(String tbc5) 
    {
        this.tbc5 = tbc5;
    }

    public String getTbc5() 
    {
        return tbc5;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskName", getTaskName())
            .append("partitionName", getPartitionName())
            .append("groupName", getGroupName())
            .append("seatId", getSeatId())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("warningTime", getWarningTime())
            .append("createTime", getCreateTime())
            .append("flowId", getFlowId())
            .append("stepLevel", getStepLevel())
            .append("completed", getCompleted())
            .append("tbc1", getTbc1())
            .append("tbc2", getTbc2())
            .append("tbc3", getTbc3())
            .append("tbc4", getTbc4())
            .append("tbc5", getTbc5())
            .toString();
    }
}
