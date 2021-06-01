package com.xjy.edu.domain;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
