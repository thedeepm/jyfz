package com.xjy.edu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 人员信息对象 edu_person_info
 * 
 * @author wuzh
 * @date 2021-05-28
 */
public class EduPersonInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 人员id */
    private Long id;

    /** 简介 */
    @Excel(name = "简介")
    private String briefIntroduction;

    /** 职位信息 */
    @Excel(name = "职位信息")
    private String position;

    /** 关联席位id */
    @Excel(name = "关联席位id")
    private Long seatId;

    /** 关联文件id */
    @Excel(name = "关联文件id")
    private Long fileId;

    /** 人物标签 */
    @Excel(name = "人物标签")
    private String tag;

    /** 人物姓名 */
    @Excel(name = "人物姓名")
    private String personName;

    /** $column.columnComment */
    @Excel(name = "关联文件id")
    private String tbc1;

    /** $column.columnComment */
    @Excel(name = "关联文件id")
    private String tbc2;

    /** $column.columnComment */
    @Excel(name = "关联文件id")
    private String tbc3;

    /** $column.columnComment */
    @Excel(name = "关联文件id")
    private String tbc4;

    /** $column.columnComment */
    @Excel(name = "关联文件id")
    private String tbc5;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBriefIntroduction(String briefIntroduction) 
    {
        this.briefIntroduction = briefIntroduction;
    }

    public String getBriefIntroduction() 
    {
        return briefIntroduction;
    }
    public void setPosition(String position) 
    {
        this.position = position;
    }

    public String getPosition() 
    {
        return position;
    }
    public void setSeatId(Long seatId) 
    {
        this.seatId = seatId;
    }

    public Long getSeatId() 
    {
        return seatId;
    }
    public void setFileId(Long fileId) 
    {
        this.fileId = fileId;
    }

    public Long getFileId() 
    {
        return fileId;
    }
    public void setTbc1(String tbc1) 
    {
        this.tbc1 = tbc1;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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
            .append("briefIntroduction", getBriefIntroduction())
            .append("position", getPosition())
            .append("seatId", getSeatId())
            .append("remark", getRemark())
            .append("fileId", getFileId())
            .append("tag", getTag())
            .append("personName", getPersonName())
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
