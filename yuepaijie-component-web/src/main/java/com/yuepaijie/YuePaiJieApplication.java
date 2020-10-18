package com.yuepaijie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.yuepaijie")
public class YuePaiJieApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuePaiJieApplication.class, args);
    }

}


