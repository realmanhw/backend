package com.yuepaijie;


import com.yuepaijie.entity.generated.UserAccount;
import com.yuepaijie.tool.redis.RedisOperator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class YuePaiJieTests {

    @Autowired
    RedisOperator redisOperator;

    @Autowired
    UserInfoService userInfoService;

    @Test
    void testRedis() throws Exception{
        redisOperator.set("11","22",10);
        Thread.sleep(500);
        System.out.println(redisOperator.get("11"));
    }

    @Test
    void testMybatis() throws Exception{
        UserAccount userAccount = new UserAccount();
        userAccount.setAccount("1");
        userAccount.setPassword("1");
        userAccount.setName("1");
        userInfoService.addOrModify(userAccount);
    }

}

