package com.yuepaijie.kit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

public class StringKit {


  /**
   * 判断是否含有特殊字符,除了"_"下划线
   * @param str
   * @return true为包含，false为不包含
   */
  public static boolean containsSpecialChar(String str) {
    String regEx = "[ `~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(str);
    return m.find();
  }

  public static final String DEFAULT_QUERY_REGEX = "[!$^&*+=|{}';'\",<>/?~！#￥%……&*——|{}【】‘；：”“'。，、？]";

  /**
   * 判断查询参数中是否以特殊字符开头，如果以特殊字符开头则返回true，否则返回false
   *
   * @param value
   * @return
   * @see {@link #getQueryRegex()}
   * @see {@link #DEFAULT_QUERY_REGEX}
   */
  public static boolean specialSymbols(String value) {
    if (StringUtils.isBlank(value)) {
      return false;
    }
    Pattern pattern = Pattern.compile(getQueryRegex());
    Matcher matcher = pattern.matcher(value);

    char[] specialSymbols = getQueryRegex().toCharArray();

    boolean isStartWithSpecialSymbol = false; // 是否以特殊字符开头
    for (int i = 0; i < specialSymbols.length; i++) {
      char c = specialSymbols[i];
      if (value.indexOf(c) == 0) {
        isStartWithSpecialSymbol = true;
        break;
      }
    }

    return matcher.find() && isStartWithSpecialSymbol;
  }


  /**
   * 获取查询过滤的非法字符
   *
   * @return
   */
  protected static String getQueryRegex() {
    return DEFAULT_QUERY_REGEX;
  }
}
