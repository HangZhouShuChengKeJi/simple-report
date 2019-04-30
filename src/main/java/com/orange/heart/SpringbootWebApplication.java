package com.orange.heart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author 小天
 * @date 2019/1/18 15:13
 */
@PropertySource({"classpath:/application.properties", "classpath:/redis.properties", "classpath:/freemarker.properties"})
@SpringBootApplication
public class SpringbootWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebApplication.class, args);
    }
}

