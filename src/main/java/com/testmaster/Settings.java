package com.testmaster;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Accessors(fluent = true)
public class Settings {

  @Value("${resources.directory.name}")
  private String resourcesDirectoryName;

  @Value("${directory.relative.path}")
  private String directoryRelativePath;
}
