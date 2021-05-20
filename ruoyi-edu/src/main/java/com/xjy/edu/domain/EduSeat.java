package com.xjy.edu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 席位对象 edu_seat
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public class EduSeat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 席位id */
    private Long id;

    /** 席位名 */
    @Excel(name = "席位名")
    private String seatName;

    /** 关联分组id */
    @Excel(name = "关联分组id")
    private Long groupId;

    /** 分组区 */
    @Excel(name = "分组区")
    private Long groupIndex;

    /** 标签 */
    @Excel(name = "标签")
    private String tag;

    /** 职位 */
    @Excel(name = "职位")
    private String position;

    /** $column.columnComment */
    @Excel(name = "职位")
    private String tbc1;

    /** $column.columnComment */
    @Excel(name = "职位")
    private String tbc2;

    /** $column.columnComment */
    @Excel(name = "职位")
    private String tbc3;

    /** $column.columnComment */
    @Excel(name = "职位")
    private String tbc4;

    /** $column.columnComment */
    @Excel(name = "职位")
    private String tbc5;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSeatName(String seatName) 
    {
        this.seatName = seatName;
    }

    public String getSeatName() 
    {
        return seatName;
    }
    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
    }
    public void setGroupIndex(Long groupIndex) 
    {
        this.groupIndex = groupIndex;
    }

    public Long getGroupIndex() 
    {
        return groupIndex;
    }
    public void setTag(String tag) 
    {
        this.tag = tag;
    }

    public String getTag() 
    {
        return tag;
    }
    public void setPosition(String position) 
    {
        this.position = position;
    }

    public String getPosition() 
    {
        return position;
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
            .append("seatName", getSeatName())
            .append("groupId", getGroupId())
            .append("groupIndex", getGroupIndex())
            .append("tag", getTag())
            .append("createTime", getCreateTime())
            .append("position", getPosition())
            .append("tbc1", getTbc1())
            .append("tbc2", getTbc2())
            .append("tbc3", getTbc3())
            .append("tbc4", getTbc4())
            .append("tbc5", getTbc5())
            .toString();
    }
}
