package com.yuepaijie.model.vo.page;

import javax.validation.Valid;
import javax.validation.constraints.Null;

/**
 * <p> Created by pengshuolin on 2018/6/26
 */
public class PageParam<T> {

  private static final int DEFAULT_PAGE_SIZE = 10;
  private static final int DEFAULT_PAGE_NO = 0;

  //过滤条件
  @Valid
  private T filter;
  //页码从0开始
  private Integer pageNo;
  //每页大小
  private Integer pageSize;
  //根据什么排序
  @Null
  private String sortBy;

  public Integer getPageNo() {
    return pageNo == null || pageNo < 0 ? DEFAULT_PAGE_NO : pageNo;
  }

  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
  }

  public Integer getPageSize() {
    return pageSize == null || pageSize <= 0 ? DEFAULT_PAGE_SIZE : pageSize;
  }

  public Page getPage() {
    return Page.of(getPageNo(), getPageSize());
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public T getFilter() {
    return filter;
  }

  public void setFilter(T filter) {
    this.filter = filter;
  }

  public String getSortBy() {
    return sortBy;
  }

  public void setSortBy(String sortBy) {
    this.sortBy = sortBy;
  }
}
