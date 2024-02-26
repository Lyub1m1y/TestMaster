package com.testmaster.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app")
public class Settings implements ResourcesPathProvider, DirectoryPathProvider {

  private String resourcesPath;
  private String directoryPath;
}
