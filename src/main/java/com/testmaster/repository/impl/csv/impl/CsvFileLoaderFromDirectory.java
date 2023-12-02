package com.testmaster.repository.impl.csv.impl;

import com.testmaster.repository.impl.csv.CsvFileLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@Slf4j
public class CsvFileLoaderFromDirectory implements CsvFileLoader {

  @Value("${directory.url}")
  private String directoryUrl;

  @Override
  public List<File> getFiles() {
    List<File> csvFiles = new ArrayList<>();
    File folder = new File(directoryUrl);

    File[] files = folder.listFiles();
    if (!isNull(files)) {
      for (File file : files) {
        if (Objects.requireNonNull(file.getName()).endsWith(".csv")) {
          csvFiles.add(file);
        }
      }
    }

    return csvFiles;
  }
}
