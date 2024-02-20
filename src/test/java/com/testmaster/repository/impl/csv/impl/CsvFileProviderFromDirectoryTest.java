package com.testmaster.repository.impl.csv.impl;

import com.testmaster.exception.TestRetrieveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvFileProviderFromDirectoryTest {

  private static final String PATH = "src/test/resources/tests";
  private static final String NOT_EXIST_PATH = "notExistPath";

  private CsvFileProviderFromDirectory csvFileProvider;

  @BeforeEach
  void setUp() {
    File directory = new File(PATH);
    String absolutePath = directory.getAbsolutePath();
    csvFileProvider = new CsvFileProviderFromDirectory(absolutePath);
  }

  @DisplayName("Should return non empty list files from directory")
  @Test
  void shouldReturnNonEmptyListFiles() {
    List<File> csvFiles = csvFileProvider.getFiles();

    assertFalse(csvFiles.isEmpty());
  }

  @DisplayName("Should return non empty list csv files from directory")
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
    CsvFileProviderFromDirectory csvFileProvider = new CsvFileProviderFromDirectory(NOT_EXIST_PATH);

    assertThrows(TestRetrieveException.class, csvFileProvider::getFiles);
  }
}