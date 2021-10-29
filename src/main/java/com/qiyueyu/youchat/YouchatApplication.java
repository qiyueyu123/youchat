package com.qiyueyu.youchat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.qiyueyu")
@MapperScan("com.qiyueyu.youchat.mapper")
public class YouchatApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouchatApplication.class, args);
    }

}
