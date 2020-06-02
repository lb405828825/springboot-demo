package com.xh.demo_lb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DemoLbApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoLbApplication.class, args);
    }

}
