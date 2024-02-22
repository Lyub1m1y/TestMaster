package com.testmaster.repository.impl.csv.impl;

import com.testmaster.exception.TestRetrieveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CsvFileProviderFromDirectoryTest {

  private static final String DIRECTORY_PATH = "src/test/resources/tests";
  private static final String NOT_EXIST_PATH = "notExistPath";

  private CsvFileProviderFromDirectory csvFileProvider;

  @BeforeEach
  void setUp() {
    csvFileProvider = new CsvFileProviderFromDirectory(DIRECTORY_PATH);
  }

  @DisplayName("Should return non empty list files from directory")
  @Test
  void shouldReturnNonEmptyListFiles() {
    Map<String, InputStream> csvFiles = csvFileProvider.getFiles();

    assertFalse(csvFiles.isEmpty());
  }

  @DisplayName("If the directory not exist, an empty list of files should be returned")
  @Test
  void shouldReturnEmptyListCSVFilesWhenDirectoryPathNotExist() {
    CsvFileProviderFromDirectory csvFileProvider = new CsvFileProviderFromDirectory(NOT_EXIST_PATH);

    assertThrows(TestRetrieveException.class, csvFileProvider::getFiles);
  }
}