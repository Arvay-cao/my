package com.roadjava.req;

public class StaffRequest {

    private int pageNow;
    private int pageSize;
    private int start;
    private String searchKey;

    public int getStart() {
        return (pageNow-1)*pageSize;
    }

    public int getPageNow() {
        return this.pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {

        this.searchKey = searchKey;
    }
}
