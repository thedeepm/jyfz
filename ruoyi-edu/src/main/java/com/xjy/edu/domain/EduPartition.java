package com.xjy.edu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 分区对象 edu_partition
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public class EduPartition extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分区id */
    private Long id;

    /** 分区名 */
    @Excel(name = "分区名")
    private String partitionName;

    /** 分区席位总数 */
    @Excel(name = "分区席位总数")
    private Long totalSeats;

    /** 分组数量 */
    @Excel(name = "分组数量")
    private Long groupNumber;

    /** 关联模板id */
    @Excel(name = "关联模板id")
    private Long templateId;

    /** 分区区间（中划线分开） */
    @Excel(name = "分区区间", readConverterExp = "中=划线分开")
    private String partitionInterval;

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
    public void setPartitionName(String partitionName) 
    {
        this.partitionName = partitionName;
    }

    public String getPartitionName() 
    {
        return partitionName;
    }
    public void setTotalSeats(Long totalSeats) 
    {
        this.totalSeats = totalSeats;
    }

    public Long getTotalSeats() 
    {
        return totalSeats;
    }
    public void setGroupNumber(Long groupNumber) 
    {
        this.groupNumber = groupNumber;
    }

    public Long getGroupNumber() 
    {
        return groupNumber;
    }
    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }
    public void setPartitionInterval(String partitionInterval) 
    {
        this.partitionInterval = partitionInterval;
    }

    public String getPartitionInterval() 
    {
        return partitionInterval;
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
            .append("partitionName", getPartitionName())
            .append("totalSeats", getTotalSeats())
            .append("groupNumber", getGroupNumber())
            .append("templateId", getTemplateId())
            .append("partitionInterval", getPartitionInterval())
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
