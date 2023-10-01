package com.weimin.activemqspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ActivemqSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivemqSpringbootApplication.class, args);
    }

}
