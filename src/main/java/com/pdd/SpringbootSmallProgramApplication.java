package com.pdd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.pdd.mapper")
@EnableTransactionManagement
public class SpringbootSmallProgramApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSmallProgramApplication.class, args);
    }

}

