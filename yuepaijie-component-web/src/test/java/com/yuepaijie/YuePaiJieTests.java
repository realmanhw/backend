package com.yuepaijie;

import com.yuepaijie.kit.encryption.CredentialUtils;
import com.yuepaijie.kit.redis.RedisKit;
import com.yuepaijie.kit.thirdparty.encrypt.AESUtils;
import com.yuepaijie.kit.thirdparty.encrypt.DESUtils;
import com.yuepaijie.service.UserInfoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class YuePaiJieTests {

    @Autowired
    RedisKit redisKit;

    @Autowired
    UserInfoService userInfoService;

    @Test
    void testRedis2() throws Exception{
        redisKit.setex("11",10,"22");
        System.out.println(redisKit.get("11"));
    }

    @Test
    void testOptional(){
        List<Integer> list = new ArrayList<>();
        Optional.ofNullable(list).orElse(null).get(0);
    }

    @Test
    void testdecode() throws Exception {
        String s1 = CredentialUtils.credentialEncode("    2 423%%43$$#54_-_S`1");
        String s = CredentialUtils.credentialDecode(s1);
        System.out.println(s);
    }

}

