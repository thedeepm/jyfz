package com.xjy.edu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 主要海洋基础信息对象 sea
 * 
 * @author wuzh
 * @date 2021-05-31
 */
public class Sea extends BaseSearchEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long seaId;

    /** 面积 */
    @Excel(name = "面积")
    private String area;

    /** 地理位置 */
    @Excel(name = "地理位置")
    private String position;

    /** 平均水深 */
    @Excel(name = "平均水深")
    private String deep;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String addMsg;

    public void setSeaId(Long seaId) 
    {
        this.seaId = seaId;
    }

    public Long getSeaId() 
    {
        return seaId;
    }
    public void setArea(String area) 
    {
        this.area = area;
    }

    public String getArea() 
    {
        return area;
    }
    public void setPosition(String position) 
    {
        this.position = position;
    }

    public String getPosition() 
    {
        return position;
    }
    public void setDeep(String deep) 
    {
        this.deep = deep;
    }

    public String getDeep() 
    {
        return deep;
    }
    public void setAddMsg(String addMsg) 
    {
        this.addMsg = addMsg;
    }

    public String getAddMsg() 
    {
        return addMsg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("seaId", getSeaId())
            .append("chineseName", getChineseName())
            .append("foreignName", getForeignName())
            .append("area", getArea())
            .append("position", getPosition())
            .append("deep", getDeep())
            .append("addMsg", getAddMsg())
            .toString();
    }
}
