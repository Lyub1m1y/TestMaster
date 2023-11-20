package com.testmaster.repository.csv.impl;

import com.testmaster.repository.impl.csv.CsvFileLoader;
import com.testmaster.repository.impl.csv.impl.CsvFileLoaderFromResources;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvFileLoaderFromResourcesTest {

  private static final String directoryName = "tests";

  @DisplayName("Should return non empty list files from resources")
  @Test
  void shouldReturnNonEmptyListFiles() {
    CsvFileLoader csvFileLoader = new CsvFileLoaderFromResources(directoryName);

    List<File> csvFiles = csvFileLoader.getFiles();

    assertFalse(csvFiles.isEmpty());
  }

  @DisplayName("Should return non empty list csv files from resources")
  @Test
  void shouldReturnNonEmptyListCSVFiles() {
    CsvFileLoader csvFileLoader = new CsvFileLoaderFromResources(directoryName);

    List<File> csvFiles = csvFileLoader.getFiles();

    for (File file : csvFiles) {
      assertTrue(file.getName().endsWith(".csv"));
    }
  }

  @DisplayName("If the directory not exist, an empty list of files should be returned")
  @Test
  void shouldReturnEmptyListCSVFilesWhenDirectoryPathNotExist() {
    CsvFileLoader csvFileLoader = new CsvFileLoaderFromResources("notExistDirectory");

    List<File> csvFiles = csvFileLoader.getFiles();

    assertTrue(csvFiles.isEmpty());
  }
}