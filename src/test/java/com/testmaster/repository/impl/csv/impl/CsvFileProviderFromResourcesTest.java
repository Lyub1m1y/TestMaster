package com.testmaster.repository.impl.csv.impl;

import com.testmaster.repository.impl.csv.CsvFileProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvFileProviderFromResourcesTest {

  private static final String directoryName = "tests";

  @DisplayName("Should return non empty list files from resources")
  @Test
  void shouldReturnNonEmptyListFiles() {
    CsvFileProvider csvFileProvider = new CsvFileProviderFromResources(directoryName);

    List<File> csvFiles = csvFileProvider.getFiles();

    assertFalse(csvFiles.isEmpty());
  }

  @DisplayName("Should return non empty list csv files from resources")
  @Test
  void shouldReturnNonEmptyListCSVFiles() {
    CsvFileProvider csvFileProvider = new CsvFileProviderFromResources(directoryName);

    List<File> csvFiles = csvFileProvider.getFiles();

    for (File file : csvFiles) {
      assertTrue(file.getName().endsWith(".csv"));
    }
  }

  @DisplayName("If the directory not exist, an empty list of files should be returned")
  @Test
  void shouldReturnEmptyListCSVFilesWhenDirectoryPathNotExist() {
    CsvFileProvider csvFileProvider = new CsvFileProviderFromResources("notExistDirectory");

    List<File> csvFiles = csvFileProvider.getFiles();

    assertTrue(csvFiles.isEmpty());
  }
}