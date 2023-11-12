package com.testmaster.repository.impl.csv.impl;

import com.testmaster.repository.impl.csv.CsvLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class СsvFileLoaderFromDirectory implements CsvLoader {

  private final String directoryUrl;

  @Override
  public List<File> getFiles() {
    List<File> csvFiles = new ArrayList<>();
    File folder = new File(directoryUrl);

    File[] files = folder.listFiles();
    for (File file : files) {
      if (Objects.requireNonNull(file.getName()).endsWith(".csv")) {
        csvFiles.add(file);
      }
    }

    return csvFiles;
  }
}
