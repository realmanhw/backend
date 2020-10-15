package com.yuepaijie;

import com.yuepaijie.common.tool.redis.RedisOperator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YuePaiJieTests {

    @Autowired
    RedisOperator redisOperator;

    @Test
    void contextLoads() throws Exception{
        redisOperator.set("11","22",10);
        Thread.sleep(500);
        System.out.println(redisOperator.get("11"));
    }

}
