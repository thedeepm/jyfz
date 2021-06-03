package com.xjy.edu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 我国周边国家信息对象 country
 * 
 * @author wuzh
 * @date 2021-05-31
 */
public class Country extends BaseSearchEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long countryId;

    /** 简称 */
    @Excel(name = "简称")
    private String abbreviation;

    /** 所属洲 */
    @Excel(name = "所属洲")
    private String belongTo;

    /** 人口数量 */
    @Excel(name = "人口数量")
    private String population;

    /** 人口密度 */
    @Excel(name = "人口密度")
    private String populationDensity;

    /** 面积 */
    @Excel(name = "面积")
    private String area;

    /** 首都 */
    @Excel(name = "首都")
    private String capital;

    /** 国家代码 */
    @Excel(name = "国家代码")
    private String code;

    /** 官方语言 */
    @Excel(name = "官方语言")
    private String language;

    /** 货币 */
    @Excel(name = "货币")
    private String addMsg;

    /** 时区 */
    @Excel(name = "时区")
    private String addMsg1;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String addMsg2;

    public void setCountryId(Long countryId) 
    {
        this.countryId = countryId;
    }

    public Long getCountryId() 
    {
        return countryId;
    }
    public void setAbbreviation(String abbreviation) 
    {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() 
    {
        return abbreviation;
    }
    public void setBelongTo(String belongTo) 
    {
        this.belongTo = belongTo;
    }

    public String getBelongTo() 
    {
        return belongTo;
    }
    public void setPopulation(String population) 
    {
        this.population = population;
    }

    public String getPopulation() 
    {
        return population;
    }
    public void setPopulationDensity(String populationDensity) 
    {
        this.populationDensity = populationDensity;
    }

    public String getPopulationDensity() 
    {
        return populationDensity;
    }
    public void setArea(String area) 
    {
        this.area = area;
    }

    public String getArea() 
    {
        return area;
    }
    public void setCapital(String capital) 
    {
        this.capital = capital;
    }

    public String getCapital() 
    {
        return capital;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setLanguage(String language) 
    {
        this.language = language;
    }

    public String getLanguage() 
    {
        return language;
    }
    public void setAddMsg(String addMsg) 
    {
        this.addMsg = addMsg;
    }

    public String getAddMsg() 
    {
        return addMsg;
    }
    public void setAddMsg1(String addMsg1) 
    {
        this.addMsg1 = addMsg1;
    }

    public String getAddMsg1() 
    {
        return addMsg1;
    }
    public void setAddMsg2(String addMsg2) 
    {
        this.addMsg2 = addMsg2;
    }

    public String getAddMsg2() 
    {
        return addMsg2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("countryId", getCountryId())
            .append("chineseName", getChineseName())
            .append("foreignName", getForeignName())
            .append("abbreviation", getAbbreviation())
            .append("belongTo", getBelongTo())
            .append("population", getPopulation())
            .append("populationDensity", getPopulationDensity())
            .append("area", getArea())
            .append("capital", getCapital())
            .append("code", getCode())
            .append("language", getLanguage())
            .append("addMsg", getAddMsg())
            .append("addMsg1", getAddMsg1())
            .append("addMsg2", getAddMsg2())
            .toString();
    }
}
