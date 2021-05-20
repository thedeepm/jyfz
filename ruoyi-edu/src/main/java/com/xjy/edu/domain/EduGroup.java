package com.xjy.edu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 分组对象 edu_group
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public class EduGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分组id */
    private Long id;

    /** 组名 */
    @Excel(name = "组名")
    private String groupName;

    /** 分组席位总数 */
    @Excel(name = "分组席位总数")
    private Long totalSeats;

    /** 分组区间（中划线分开） */
    @Excel(name = "分组区间", readConverterExp = "中=划线分开")
    private String groupInterval;

    /** 关联分区id */
    @Excel(name = "关联分区id")
    private Long partitionId;

    /** 功能属性 */
    @Excel(name = "功能属性")
    private String functionAttribute;

    /** 颜色 */
    @Excel(name = "颜色")
    private String color;

    /** $column.columnComment */
    @Excel(name = "颜色")
    private String tbc1;

    /** $column.columnComment */
    @Excel(name = "颜色")
    private String tbc2;

    /** $column.columnComment */
    @Excel(name = "颜色")
    private String tbc3;

    /** $column.columnComment */
    @Excel(name = "颜色")
    private String tbc4;

    /** $column.columnComment */
    @Excel(name = "颜色")
    private String tbc5;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGroupName(String groupName) 
    {
        this.groupName = groupName;
    }

    public String getGroupName() 
    {
        return groupName;
    }
    public void setTotalSeats(Long totalSeats) 
    {
        this.totalSeats = totalSeats;
    }

    public Long getTotalSeats() 
    {
        return totalSeats;
    }
    public void setGroupInterval(String groupInterval) 
    {
        this.groupInterval = groupInterval;
    }

    public String getGroupInterval() 
    {
        return groupInterval;
    }
    public void setPartitionId(Long partitionId) 
    {
        this.partitionId = partitionId;
    }

    public Long getPartitionId() 
    {
        return partitionId;
    }
    public void setFunctionAttribute(String functionAttribute) 
    {
        this.functionAttribute = functionAttribute;
    }

    public String getFunctionAttribute() 
    {
        return functionAttribute;
    }
    public void setColor(String color) 
    {
        this.color = color;
    }

    public String getColor() 
    {
        return color;
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
            .append("groupName", getGroupName())
            .append("totalSeats", getTotalSeats())
            .append("groupInterval", getGroupInterval())
            .append("partitionId", getPartitionId())
            .append("functionAttribute", getFunctionAttribute())
            .append("color", getColor())
            .append("tbc1", getTbc1())
            .append("tbc2", getTbc2())
            .append("tbc3", getTbc3())
            .append("tbc4", getTbc4())
            .append("tbc5", getTbc5())
            .toString();
    }
}
