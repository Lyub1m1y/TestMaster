package com.testmaster.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@Getter
@Setter
@ConfigurationProperties(prefix = "app")
public class Settings implements ResourcesPathProvider, DirectoryPathProvider, LocaleProvider {

  private String resourcesPath;
  private String directoryPath;
  private Locale locale;
}
