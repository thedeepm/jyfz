package com.xjy.edu.util;

import java.io.Serializable;

public class PageData<T> implements Serializable {
    private long total;

    private java.util.List<T> rows;

    public PageData(long total, java.util.List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public java.util.List<T> getRows() {
        return this.rows;
    }

    public void setRows(java.util.List<T> rows) {
        this.rows = rows;
    }

    public PageData() {
    }
}

