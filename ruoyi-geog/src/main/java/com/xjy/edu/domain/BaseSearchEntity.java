package com.xjy.edu.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 基础搜索对象BaseSearchEntity
 *
 * @author wuzh
 * @date 2021-05-31
 */
public class BaseSearchEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String type;

    private Long id;

    private  String properties;

    private  String keyword;

    /** 中文名称 */
    @Excel(name = "中文名称")
    private String chineseName;

    /** 外文名称 */
    @Excel(name = "外文名称")
    private String foreignName;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}