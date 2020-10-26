package com.yuepaijie.constants;

public class RedisKeys {
  public static final String PROJECT_PREFIX = "yuepaijie:";
  public static final String YUEPAIJIE_AUTH_PREFIX = "yuepaijie_auth:";
  public static final String USER_DETAIL_PREFIX = YUEPAIJIE_AUTH_PREFIX + "user_detail:";
  public static final String TICKET_DICT_KEY = YUEPAIJIE_AUTH_PREFIX + "user_ticket_dict";
  public static final String AUTHENTICATOR_TOKEN_KEY = PROJECT_PREFIX + "authenticatorTokenKey:";
}
