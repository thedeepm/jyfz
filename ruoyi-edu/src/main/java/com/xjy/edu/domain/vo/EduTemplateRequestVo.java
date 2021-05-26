package com.xjy.edu.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.xjy.edu.domain.EduPartition;
import com.xjy.edu.domain.EduTemplate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 模板对象 edu_template
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public class EduTemplateRequestVo extends EduTemplate
{
    private static final long serialVersionUID = 1L;

    public List<EduPartition> partitionsList;

    public List<EduPartition> getPartitionsList() {
        return partitionsList;
    }

    public void setPartitionsList(List<EduPartition> partitionsList) {
        this.partitionsList = partitionsList;
    }

    public EduTemplate getTemplate(){
        EduTemplate eduTemplate = new EduTemplate();
        eduTemplate.setFlowId(this.getFlowId());
        eduTemplate.setHallName(this.getHallName());
        eduTemplate.setId(this.getId());
        eduTemplate.setPartitionNumber(this.getPartitionNumber());
        eduTemplate.setTbc1(this.getTbc1());
        eduTemplate.setTbc2(this.getTbc2());
        eduTemplate.setTbc3(this.getTbc3());
        eduTemplate.setTbc4(this.getTbc4());
        eduTemplate.setTbc5(this.getTbc5());
        eduTemplate.setTotalSeats(this.getTotalSeats());
        eduTemplate.setCreateTime(this.getCreateTime());
        eduTemplate.setUpdateTime(this.getUpdateTime());
        return eduTemplate;
    }

}
