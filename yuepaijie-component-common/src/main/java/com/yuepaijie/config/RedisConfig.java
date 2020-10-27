package com.yuepaijie.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tanwei on 下午6:19 2018/6/25
 */
@Configuration
@ConfigurationProperties(prefix = "redis")
@Data
@ToString
public class RedisConfig {

  private Boolean useSentinel;

  /* Sentinel 模式配置：sentinels 和 masterName */
  private String sentinels;

  private String masterName;

  // 非 Sentinel 模式配置：host 和 port
  private String host;

  private Integer port;

  private String password;

  private Integer database;

  private Integer timeout;

  private Pool pool;

  private Boolean testOnBorrow;

  @Data
  @ToString
  public static class Pool {

    private Integer maxTotal;

    private Integer maxIdle;

    private Long maxWaitMillis;
  }
}
