package com.xjy.edu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 国内主要城市信息对象 city
 * 
 * @author wuzh
 * @date 2021-05-31
 */
public class City extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long cityId;

    /** 中文名称 */
    @Excel(name = "中文名称")
    private String chineseName;

    /** 外文名称 */
    @Excel(name = "外文名称")
    private String foreignName;

    /** 别名 */
    @Excel(name = "别名")
    private String otherName;

    /** 行政区类别 */
    @Excel(name = "行政区类别")
    private String category;

    /** 面积 */
    @Excel(name = "面积")
    private String area;

    /** 人口数量 */
    @Excel(name = "人口数量")
    private String population;

    /** 地理位置 */
    @Excel(name = "地理位置")
    private String position;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String addMsg;

    public void setCityId(Long cityId) 
    {
        this.cityId = cityId;
    }

    public Long getCityId() 
    {
        return cityId;
    }
    public void setChineseName(String chineseName) 
    {
        this.chineseName = chineseName;
    }

    public String getChineseName() 
    {
        return chineseName;
    }
    public void setForeignName(String foreignName) 
    {
        this.foreignName = foreignName;
    }

    public String getForeignName() 
    {
        return foreignName;
    }
    public void setOtherName(String otherName) 
    {
        this.otherName = otherName;
    }

    public String getOtherName() 
    {
        return otherName;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }
    public void setArea(String area) 
    {
        this.area = area;
    }

    public String getArea() 
    {
        return area;
    }
    public void setPopulation(String population) 
    {
        this.population = population;
    }

    public String getPopulation() 
    {
        return population;
    }
    public void setPosition(String position) 
    {
        this.position = position;
    }

    public String getPosition() 
    {
        return position;
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
            .append("cityId", getCityId())
            .append("chineseName", getChineseName())
            .append("foreignName", getForeignName())
            .append("otherName", getOtherName())
            .append("category", getCategory())
            .append("area", getArea())
            .append("population", getPopulation())
            .append("position", getPosition())
            .append("addMsg", getAddMsg())
            .toString();
    }
}
