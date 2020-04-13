package com.deepbluebi.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author cj
 */
@ServletComponentScan
@SpringBootApplication
@MapperScan({"com.deepbluebi.basic.dao"})
public class BasicApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class);
    }
}
