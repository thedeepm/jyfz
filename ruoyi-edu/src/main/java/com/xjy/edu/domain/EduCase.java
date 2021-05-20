package com.xjy.edu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 案例对象 edu_case
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public class EduCase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 案例id */
    private Long id;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 模板id */
    @Excel(name = "模板id")
    private Long templateId;

    /** 案例说明 */
    @Excel(name = "案例说明")
    private String description;

    /** 案例名 */
    @Excel(name = "案例名")
    private String caseName;

    /** $column.columnComment */
    @Excel(name = "案例名")
    private String tbc1;

    /** $column.columnComment */
    @Excel(name = "案例名")
    private String tbc2;

    /** $column.columnComment */
    @Excel(name = "案例名")
    private String tbc3;

    /** $column.columnComment */
    @Excel(name = "案例名")
    private String tbc4;

    /** $column.columnComment */
    @Excel(name = "案例名")
    private String tbc5;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setCaseName(String caseName) 
    {
        this.caseName = caseName;
    }

    public String getCaseName() 
    {
        return caseName;
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
            .append("type", getType())
            .append("templateId", getTemplateId())
            .append("description", getDescription())
            .append("caseName", getCaseName())
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
