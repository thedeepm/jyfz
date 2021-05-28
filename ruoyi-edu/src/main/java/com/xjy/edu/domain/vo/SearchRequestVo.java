package com.xjy.edu.domain.vo;

import com.xjy.edu.domain.EduFlow;
import com.xjy.edu.domain.EduTask;

import java.util.List;

/**
 * 搜索对象
 * 
 * @author wuzh
 * @date 2021-05-19
 */
public class SearchRequestVo
{
    private static final long serialVersionUID = 1L;

    public String keyword;

    public String category;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
