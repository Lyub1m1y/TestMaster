package com.testmaster.config;

import com.testmaster.launcher.TestLauncher;
import com.testmaster.repository.impl.csv.CsvRepository;
import com.testmaster.repository.impl.csv.impl.CsvFileLoaderFromResources;
import com.testmaster.repository.impl.csv.impl.CsvFileLoaderFromDirectory;
import com.testmaster.service.InOutService;
import com.testmaster.service.QuestionConverter;
import com.testmaster.service.TestResultConverter;
import com.testmaster.service.TestService;
import com.testmaster.service.impl.QuestionConverterImpl;
import com.testmaster.service.impl.TestResultConverterImpl;
import com.testmaster.service.impl.TestServiceImpl;
import com.testmaster.service.impl.io.InOutServiceImpl;
import com.testmaster.service.impl.io.stream.StreamInputService;
import com.testmaster.service.impl.io.stream.StreamOutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.Scanner;

@Configuration
@PropertySource("classpath:/application.properties")
public class AppConfig {

    @Autowired
    Environment env;

    @Bean
    public CsvFileLoaderFromResources csvFileLoaderFromResources() {
        return new CsvFileLoaderFromResources(env.getProperty("resources.directory.name"));
    }

    @Bean
    public CsvFileLoaderFromDirectory csvFileLoaderFromDirectory() {
        return new CsvFileLoaderFromDirectory(env.getProperty("directory.url"));
    }

    @Bean
    public CsvRepository csvRepository() {
        return new CsvRepository(List.of(csvFileLoaderFromResources(), csvFileLoaderFromDirectory()));
    }

    @Bean
    public StreamInputService streamInputService() {
        return new StreamInputService(new Scanner(System.in));
    }

    @Bean
    public StreamOutputService streamOutputService() {
        return new StreamOutputService();
    }

    @Bean
    public InOutService inOutService() {
        return new InOutServiceImpl(streamInputService(), streamOutputService());
    }

    @Bean
    public QuestionConverter questionConverter() {
        return new QuestionConverterImpl();
    }

    @Bean
    public TestResultConverter testResultConverter() {
        return new TestResultConverterImpl();
    }

    @Bean
    public TestService testService() {
        return new TestServiceImpl(csvRepository());
    }

    @Bean
    public TestLauncher testLauncher() {
        return new TestLauncher(testService(), inOutService(),
                                questionConverter(), testResultConverter());
    }
}
