package com.yuepaijie.kit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 身份证号码工具类
 * <p>居民一代身份证号码由 15 位数字组成；排列顺序从左至右依次为：六位地址码，六位出生日期码（yyMMdd），三位数字码(奇数分配给男性，偶数分配给女性)</p>
 * <p>根据 GB 11643-1999，居民二代身份证号码由 17 位本体码和 1 位校验码组成</p>
 * <p>排列顺序从左至右依次为：六位数字地址码，八位出生日期码，三位顺序码(奇数分配给男性，偶数分配给女性)和一位校验码；</p>
 *
 * @author wuzhiming@itiger.com
 * @date 2020/8/25
 */
public class IdentificationUtil {

  private static final Logger logger = LoggerFactory.getLogger(IdentificationUtil.class);

  public static boolean validateChinaIdentification(String idStr) {
    return validateChinaIdentification(idStr, null);
  }

  public static boolean validateChinaIdentification(String idStr, Boolean isMale) {
    try {
      new ChinaIdentification(idStr, isMale);
      return true;
    } catch (IllegalArgumentException e) {
      logger.warn("identification [{}] with gender [{}] validate failed, reason: {}", idStr, isMale, e.getMessage());
      return false;
    }
  }

  /**
   * /**
   * 中国大陆身份证
   */
  private static class ChinaIdentification {

    /**
     * 一代身份证号码长度
     */
    private static final int G1_ID_LENGTH = 15;
    /**
     * 二代身份证号码长度
     */
    private static final int G2_ID_LENGTH = 18;

    /**
     * 一代身份证号码正则
     */
    private static final String G1_ID_PATTERN = "^[1-9]\\d{5}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}";
    /**
     * 二代身份证号码正则
     */
    private static final String G2_ID_PATTERN =
        "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

    private static final LocalDate START_DATE = LocalDate.of(1800, 1, 1);

    /**
     * 省级地址码
     */
    private static final Map<String, String> AREA_CODE = new HashMap<>(64);

    static {
      AREA_CODE.put("11", "北京");
      AREA_CODE.put("12", "天津");
      AREA_CODE.put("13", "河北");
      AREA_CODE.put("14", "山西");
      AREA_CODE.put("15", "内蒙古");
      AREA_CODE.put("21", "辽宁");
      AREA_CODE.put("22", "吉林");
      AREA_CODE.put("23", "黑龙江");
      AREA_CODE.put("31", "上海");
      AREA_CODE.put("32", "江苏");
      AREA_CODE.put("33", "浙江");
      AREA_CODE.put("34", "安徽");
      AREA_CODE.put("35", "福建");
      AREA_CODE.put("36", "江西");
      AREA_CODE.put("37", "山东");
      AREA_CODE.put("41", "河南");
      AREA_CODE.put("42", "湖北");
      AREA_CODE.put("43", "湖南");
      AREA_CODE.put("44", "广东");
      AREA_CODE.put("45", "广西");
      AREA_CODE.put("46", "海南");
      AREA_CODE.put("50", "重庆");
      AREA_CODE.put("51", "四川");
      AREA_CODE.put("52", "贵州");
      AREA_CODE.put("53", "云南");
      AREA_CODE.put("54", "西藏");
      AREA_CODE.put("61", "陕西");
      AREA_CODE.put("62", "甘肃");
      AREA_CODE.put("63", "青海");
      AREA_CODE.put("64", "宁夏");
      AREA_CODE.put("65", "新疆");
      AREA_CODE.put("71", "台湾");
      AREA_CODE.put("81", "香港");
      AREA_CODE.put("82", "澳门");
      AREA_CODE.put("91", "国外");
    }

    private final String areaCode;
    private final LocalDate birthday;
    private final Integer orderCode;
    private final Integer verificationCode;
    private final Boolean firstGeneration;

    /**
     * 创建身份证对象
     *
     * @param idStr 身份证字符串
     * @param gender 性别标记：null-不涉及；true-男性；false-女性
     */
    ChinaIdentification(String idStr, Boolean gender) {
      if (StringUtils.isBlank(idStr)) {
        throw new IllegalArgumentException("身份证号码为空");
      }
      if (idStr.length() != G1_ID_LENGTH && idStr.length() != G2_ID_LENGTH) {
        throw new IllegalArgumentException("身份证号码长度错误");
      }
      this.firstGeneration = idStr.length() == G1_ID_LENGTH;
      if (this.firstGeneration ? !idStr.matches(G1_ID_PATTERN) : !idStr.matches(G2_ID_PATTERN)) {
        throw new IllegalArgumentException("身份证号码格式错误");
      }
      this.areaCode = pickAreaCode(idStr);
      this.birthday = pickBirthday(idStr, this.firstGeneration);
      this.orderCode = pickOrderCode(idStr, this.firstGeneration, gender);
      this.verificationCode = pickVerificationCode(idStr, this.firstGeneration);
    }

    private Integer pickVerificationCode(String idStr, Boolean firstGeneration) {
      if (firstGeneration) {
        return null;
      }
      char[] chars = idStr.toUpperCase().toCharArray();
      int code = 'X' == chars[chars.length - 1] ? 10 : chars[chars.length - 1] - 0x30;
      int sum = 0;
      for (int i = 0; i < chars.length - 1; i++) {
        sum += (Math.pow(2, 17 - i) % 11) * (chars[i] - 0x30);
      }
      if (code != ((12 - (sum % 11)) % 11)) {
        throw new IllegalArgumentException("校验码非法");
      }
      return code;
    }

    private Integer pickOrderCode(String idStr, Boolean firstGeneration, Boolean gender) {
      String orderStr = firstGeneration ? idStr.substring(12) : idStr.substring(14, 17);
      int order;
      try {
        order = Integer.parseInt(orderStr);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("顺序码非法");
      }
      if (gender != null && gender.equals(order % 2 == 0)) {
        throw new IllegalArgumentException("顺序码和性别不匹配");
      }
      return order;
    }

    private LocalDate pickBirthday(String idStr, Boolean firstGeneration) {
      String birthStr = firstGeneration ? idStr.substring(6, 12) : idStr.substring(6, 14);
      // 如果是一代身份证，补全年份
      birthStr = firstGeneration ? "19" + birthStr : birthStr;
      LocalDate birth = null;
      try {
        birth = LocalDate.parse(birthStr, DateTimeFormatter.ofPattern("yyyyMMdd"));
      } catch (Exception e) {
        throw new IllegalArgumentException("出生日期非法");
      }
      if (birth.isBefore(START_DATE) || birth.isAfter(LocalDate.now())) {
        throw new IllegalArgumentException("出生日期超出范围");
      }
      return birth;
    }

    private static String pickAreaCode(String idStr) {
      String areaCode = idStr.substring(0, 2);
      if (!AREA_CODE.containsKey(areaCode)) {
        throw new IllegalArgumentException("地址码非法");
      }
      return areaCode;
    }

    @Override
    public String toString() {
      return "ChinaIdentification{" +
          "areaCode='" + areaCode + '\'' +
          ", birthday=" + birthday +
          ", orderCode=" + orderCode +
          ", verificationCode=" + verificationCode +
          ", firstGeneration=" + firstGeneration +
          '}';
    }
  }
}
