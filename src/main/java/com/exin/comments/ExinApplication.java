package com.exin.comments;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan(basePackages = {"com.exin.comments.mapper","com.exin.comments.service.impl"})
@ComponentScan(basePackages = {"com.exin.comments.service.impl"})
public class ExinApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExinApplication.class, args);
    }

}
