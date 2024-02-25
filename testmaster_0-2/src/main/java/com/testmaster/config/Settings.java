package com.testmaster.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Settings implements ResourcesDirectoryNameProvider, DirectoryRelativePathProvider {

  @Value("${resources.directory.name}")
  private String resourcesDirectoryName;

  @Value("${directory.relative.path}")
  private String directoryRelativePath;
}
