package com.testmaster.repository.impl.csv.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvFileProviderFromResourcesTest {

  private CsvFileProviderFromResources csvFileProvider;

  private static final String DIRECTORY_NAME = "tests";
  private static final String NOT_EXIST_DIRECTORY = "notExistDirectory";

  @BeforeEach
  void setUp() {
    csvFileProvider = new CsvFileProviderFromResources();
    csvFileProvider.setDirectoryName(DIRECTORY_NAME);
  }


  @DisplayName("Should return non empty list files from resources")
  @Test
  void shouldReturnNonEmptyListFiles() {
    List<File> csvFiles = csvFileProvider.getFiles();

    assertFalse(csvFiles.isEmpty());
  }

  @DisplayName("Should return non empty list csv files from resources")
  @Test
  void shouldReturnNonEmptyListCSVFiles() {
    List<File> csvFiles = csvFileProvider.getFiles();

    for (File file : csvFiles) {
      assertTrue(file.getName().endsWith(".csv"));
    }
  }

  @DisplayName("If the directory not exist, an empty list of files should be returned")
  @Test
  void shouldReturnEmptyListCSVFilesWhenDirectoryPathNotExist() {
    csvFileProvider.setDirectoryName(NOT_EXIST_DIRECTORY);

    List<File> csvFiles = csvFileProvider.getFiles();

    assertTrue(csvFiles.isEmpty());
  }
}