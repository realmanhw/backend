package com.yuepaijie.model.vo.page;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Lists;
import com.yuepaijie.constants.View;
import java.util.List;
import lombok.Data;

/**
 * <p> Created by pengshuolin on 2018/6/26
 */
@Data
public class PageEntity {

  /**
   * 默认每页数量
   */
  public static final Integer defaultPageSize = 10;
  /**
   * 页号
   */
  @JsonView(View.BaseView.class)
  private Integer pageNo;
  /**
   * 每页数量
   */
  @JsonView(View.BaseView.class)
  private Integer pageSize;
  /**
   * 总页数
   */
  @JsonView(View.BaseView.class)
  private Integer pageCount;
  /**
   * 总记录数
   */
  @JsonView(View.BaseView.class)
  private Integer total;
  /**
   * 当页数据
   */
  @JsonView(View.BaseView.class)
  private List<?> list;

  @JsonView(View.BaseView.class)
  private Object addition;

  public static PageEntity emptyList(Object... additions) {
    return new PageEntity(0, defaultPageSize, 0, Lists.newArrayList(),
            additions == null || additions.length == 0 ? null : additions[0]);
  }

  /**
   * @param page  page实体：包含页码page_no从0开始，page_size每页的list大小（非总数）
   * @param total 总的list的size（总数）
   * @param list  筛选过后的每页list内容（非总数）
   * @return PageEntity
   *         包含页码page_no从0开始，
   *         page_size每页的list大小（非总数），
   *         page_count总页数
   *         总的list的size（总数）
   *         筛选过后的每页list内容（非总数）
   */
  public static PageEntity of(PageParam page, Integer total, List<?> list) {
    return of(page, total, list, null);
  }

  public static PageEntity of(Page page, Integer total, List<?> list, Object... additions) {
    int pageNo;
    int pageSize;
    if (page == null) {
      pageSize = list.size();
      pageNo = 0;
    } else {
      pageNo = page.getPageNo();
      pageSize = page.getPageSize();
    }
    return new PageEntity(total, pageSize, pageNo, list,
            additions == null || additions.length == 0 ? null : additions[0]);
  }

  public static PageEntity of(PageParam page, Integer total, List<?> list, Object addition) {
    Integer pageNo = page.getPageNo(), pageSize = page.getPageSize();
    if (pageNo == null) {
      pageNo = 0;
    }
    if (pageSize == null) {
      pageSize = defaultPageSize;
    }
    return new PageEntity(total, pageSize, pageNo, list, addition);
  }

  public PageEntity(Integer total, Integer pageSize, Integer pageNo, List<?> list) {
    this.total = total;
    this.pageSize = pageSize;
    this.pageNo = pageNo;
    this.pageCount = getPageCount(total, pageSize);
    this.list = list;
  }

  public PageEntity(Integer total, Integer pageSize, Integer pageNo, List<?> list, Object addition) {
    this.total = total;
    this.pageSize = pageSize;
    this.pageNo = pageNo;
    this.pageCount = getPageCount(total, pageSize);
    this.list = list;
    this.addition = addition;
  }

  private Integer getPageCount(Integer total, Integer pageSize) {
    int pageCount = 0;
    if (pageSize > 0) {
      pageCount = total / pageSize;
      if (total % pageSize != 0) {
        pageCount++;
      }
    }

    return pageCount;
  }
}
