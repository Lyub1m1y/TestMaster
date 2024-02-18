package com.testmaster.repository.impl.csv.impl;

import com.testmaster.repository.impl.csv.CsvFileProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@Slf4j
@Setter
public class CsvFileProviderFromDirectory implements CsvFileProvider {

  private final String directoryUrl;

  public CsvFileProviderFromDirectory(@Value("${directory.url}") String directoryUrl) {
    this.directoryUrl = directoryUrl;
  }

  @Override
  public List<File> getFiles() {
    List<File> csvFiles = new ArrayList<>();
    File folder = new File(directoryUrl);
    File[] files = folder.listFiles();

    if (isNull(files)) {
      return Collections.emptyList();
    }
    for (File file : files) {
      if (Objects.requireNonNull(file.getName()).endsWith(".csv")) {
        csvFiles.add(file);
      }
    }

    return csvFiles;
  }
}
