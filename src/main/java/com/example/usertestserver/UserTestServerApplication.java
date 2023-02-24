package com.example.usertestserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class UserTestServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserTestServerApplication.class, args);
    }

}
