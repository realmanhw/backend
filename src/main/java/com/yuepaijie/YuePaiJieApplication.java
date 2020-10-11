package com.yuepaijie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yuepaijie.common.dao.generated")
public class YuePaiJieApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuePaiJieApplication.class, args);
    }

}
