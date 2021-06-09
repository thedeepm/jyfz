package com.xjy.edu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 国内外主要港口信息对象 port
 * 
 * @author wuzh
 * @date 2021-05-31
 */
public class Port extends BaseSearchEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long portId;

    /** 所属地区 */
    @Excel(name = "所属地区")
    private String region;

    /** 货物吞吐量 */
    @Excel(name = "货物吞吐量")
    private String throughput;

    /** 主营业务 */
    @Excel(name = "主营业务")
    private String coreBusiness;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String addMsg;

    public void setPortId(Long portId) 
    {
        this.portId = portId;
    }

    public Long getPortId() 
    {
        return portId;
    }
    public void setRegion(String region) 
    {
        this.region = region;
    }

    public String getRegion() 
    {
        return region;
    }
    public void setThroughput(String throughput) 
    {
        this.throughput = throughput;
    }

    public String getThroughput() 
    {
        return throughput;
    }
    public void setCoreBusiness(String coreBusiness) 
    {
        this.coreBusiness = coreBusiness;
    }

    public String getCoreBusiness() 
    {
        return coreBusiness;
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
            .append("portId", getPortId())
            .append("chineseName", getChineseName())
            .append("foreignName", getForeignName())
            .append("region", getRegion())
            .append("throughput", getThroughput())
            .append("coreBusiness", getCoreBusiness())
            .append("addMsg", getAddMsg())
            .toString();
    }
}
