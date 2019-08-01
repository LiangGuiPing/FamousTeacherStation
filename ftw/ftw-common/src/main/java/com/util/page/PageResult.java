package com.util.page;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "数据列表", required = true)
    private List<T> list;

    @ApiModelProperty(value = "当前页", required = true)
    private int pageNo = 1;

    @ApiModelProperty(value = "总条数", required = true)
    private long totalCount;

    @ApiModelProperty(value = "总页数", required = true)
    private int totalPage;

    @ApiModelProperty(value = "每页条数", required = true)
    private int pageSize = 10;

    @ApiModelProperty(value = "总条数", required = false)
    private long total;

    public void setTotalPage() {
        if (this.totalCount == 0) {
            this.totalPage = 0;
        } else {
            this.totalPage = (int) ((this.totalCount % this.pageSize > 0) ? (this.totalCount / this.pageSize + 1) : this.totalCount / this.pageSize);
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public long getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        this.total = totalCount;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
        this.totalCount = total;
    }

}
