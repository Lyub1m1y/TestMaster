package com.testmaster.repository.impl.csv.impl;

import com.testmaster.repository.impl.csv.CsvFileProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CsvFileProviderFromResources implements CsvFileProvider {

  @Value("${resources.directory.name}")
  private String directoryName;

  @Override
  public List<File> getFiles() {
    List<File> csvFiles = new ArrayList<>();
    String directoryPath = "classpath:" + directoryName + "/**";
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    try {
      Resource[] resources = resolver.getResources(directoryPath);
      for (Resource resource : resources) {
        String fileName = resource.getFilename();
        if (fileName != null && fileName.endsWith(".csv")) {
          File file = readResourceToFile(resource.getInputStream(), fileName);
          csvFiles.add(file);
        }
      }
    } catch (Exception ex) {
      log.error(ex.getMessage());
    }

    return csvFiles;
  }

  private File readResourceToFile(InputStream fileInputStream, String fileName) throws IOException {
    try (InputStream inputStream = fileInputStream) {
      File tempDir = new File(System.getProperty("java.io.tmpdir"));
      File tempFile = new File(tempDir, fileName);
      try (OutputStream outputStream = new FileOutputStream(tempFile)) {

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
          outputStream.write(buffer, 0, bytesRead);
        }
      }

      return tempFile;
    }
  }
}
