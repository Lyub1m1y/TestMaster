package com.testmaster.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = "com.testmaster")
@PropertySource("classpath:/application.properties")
public class AppConfig {
}
