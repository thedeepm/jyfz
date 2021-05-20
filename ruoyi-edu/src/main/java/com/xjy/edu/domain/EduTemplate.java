package com.xjy.edu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 模板对象 edu_template
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public class EduTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 模板id */
    private Long id;

    /** 分区大厅名 */
    @Excel(name = "分区大厅名")
    private String hallName;

    /** 席位总数量 */
    @Excel(name = "席位总数量")
    private Long totalSeats;

    /** 分区数量 */
    @Excel(name = "分区数量")
    private String partitionNumber;

    /** 关联流程id */
    @Excel(name = "关联流程id")
    private Long flowId;

    /** $column.columnComment */
    @Excel(name = "关联流程id")
    private String tbc1;

    /** $column.columnComment */
    @Excel(name = "关联流程id")
    private String tbc2;

    /** $column.columnComment */
    @Excel(name = "关联流程id")
    private String tbc3;

    /** $column.columnComment */
    @Excel(name = "关联流程id")
    private String tbc4;

    /** $column.columnComment */
    @Excel(name = "关联流程id")
    private String tbc5;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setHallName(String hallName) 
    {
        this.hallName = hallName;
    }

    public String getHallName() 
    {
        return hallName;
    }
    public void setTotalSeats(Long totalSeats) 
    {
        this.totalSeats = totalSeats;
    }

    public Long getTotalSeats() 
    {
        return totalSeats;
    }
    public void setPartitionNumber(String partitionNumber) 
    {
        this.partitionNumber = partitionNumber;
    }

    public String getPartitionNumber() 
    {
        return partitionNumber;
    }
    public void setFlowId(Long flowId) 
    {
        this.flowId = flowId;
    }

    public Long getFlowId() 
    {
        return flowId;
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
            .append("hallName", getHallName())
            .append("totalSeats", getTotalSeats())
            .append("partitionNumber", getPartitionNumber())
            .append("flowId", getFlowId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("tbc1", getTbc1())
            .append("tbc2", getTbc2())
            .append("tbc3", getTbc3())
            .append("tbc4", getTbc4())
            .append("tbc5", getTbc5())
            .toString();
    }
}
