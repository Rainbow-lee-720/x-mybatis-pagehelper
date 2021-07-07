package com.springboot.xmybatispagehelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class XMybatisPagehelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(XMybatisPagehelperApplication.class, args);
    }

}
