package com.yuepaijie.model.vo.page;

import lombok.Data;

/**
 * <p> Created by pengshuolin on 2018/6/28
 */
@Data
public class Page {

  private Integer pageNo;
  private Integer pageSize;

  private int offset;
  private int limit;
  private String orderBy;

  public static Page EMPTY = new Page();

  /**
   * 根据传入的pageNo和pageSize得到offset和limit ==> 给 RowBounds用
   * @param pageNo
   * @param pageSize
   * @return
   */
  public static Page of(Integer pageNo, Integer pageSize) {
    int no = pageNo == null? 0: pageNo;
    int size = pageSize == null? 10: pageSize;
    if (no < 0 || size <= 0)
      throw new IllegalArgumentException("illegal pageNo and pageSize");

    Page page = new Page();
    page.pageNo = pageNo;
    page.pageSize = pageSize;
    page.offset = no * size;
    page.limit = size;
    return page;
  }

  public static Page of(Integer pageNo, Integer pageSize, String orderBy) {
    Page page = of(pageNo, pageSize);
    page.setOrderBy(orderBy);
    return page;
  }

}
