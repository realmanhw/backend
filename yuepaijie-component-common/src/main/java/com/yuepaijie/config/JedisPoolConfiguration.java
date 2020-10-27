package com.yuepaijie.config;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.util.Pool;

@Slf4j
@Configuration
public class JedisPoolConfiguration {

  @Autowired
  private RedisConfig redisConfig;

  @Bean
  public Pool<Jedis> jedisPool() {
    return createJedisSentinelPool();
  }

  private Pool<Jedis> createJedisSentinelPool() {
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    RedisConfig.Pool pool = redisConfig.getPool();
    jedisPoolConfig.setMaxTotal(pool.getMaxTotal());
    jedisPoolConfig.setMaxIdle(pool.getMaxIdle());
    jedisPoolConfig.setMaxWaitMillis(pool.getMaxWaitMillis());
    jedisPoolConfig.setTestOnBorrow(redisConfig.getTestOnBorrow());

    if (redisConfig.getUseSentinel()) {
      return createJedisSentinelPool(jedisPoolConfig);
    } else {
      return createJedisPool(jedisPoolConfig);
    }
  }

  private Pool<Jedis> createJedisSentinelPool(JedisPoolConfig jedisPoolConfig) {
    Set<String> sentinelAddrs = Arrays.stream(StringUtils.commaDelimitedListToStringArray(redisConfig.getSentinels()))
        .collect(Collectors.toSet());

    return new JedisSentinelPool(redisConfig.getMasterName(), sentinelAddrs, jedisPoolConfig, redisConfig.getTimeout(),
        redisConfig.getPassword(), redisConfig.getDatabase());
  }

  private Pool<Jedis> createJedisPool(JedisPoolConfig jedisPoolConfig) {
    return new JedisPool(jedisPoolConfig, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getTimeout(),
        redisConfig.getPassword(), redisConfig.getDatabase());
  }
}
