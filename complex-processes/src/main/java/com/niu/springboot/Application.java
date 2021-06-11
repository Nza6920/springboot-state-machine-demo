package com.niu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.config.EnableStateMachine;

/**
 * 启动类
 *
 * @author [nza]
 * @version 1.0 [2021/06/11 17:07]
 * @createTime [2021/06/11 17:07]
 */
@SpringBootApplication
@EnableStateMachine
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
