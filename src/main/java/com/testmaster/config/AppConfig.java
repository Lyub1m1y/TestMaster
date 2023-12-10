package com.testmaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = "com.testmaster")
@PropertySource("classpath:/application.properties")
public class AppConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
