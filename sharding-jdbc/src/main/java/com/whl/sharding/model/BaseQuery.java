package com.whl.sharding.model;

public class BaseQuery {
    private int pageSize;

    private int pageNum;

    private String keywork;

    private String sort;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getKeywork() {
        return keywork;
    }

    public void setKeywork(String keywork) {
        this.keywork = keywork;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
