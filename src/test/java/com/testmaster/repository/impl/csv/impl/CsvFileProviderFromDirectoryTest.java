package com.testmaster.repository.impl.csv.impl;

import com.testmaster.repository.impl.csv.CsvFileProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvFileProviderFromDirectoryTest {

  private static final String PATH = "src/test/resources/tests";

  @DisplayName("Should return non empty list files from directory")
  @Test
  void shouldReturnNonEmptyListFiles() {
    File directory = new File(PATH);
    String absolutePath = directory.getAbsolutePath();
    CsvFileProvider csvFileProvider = new CsvFileProviderFromDirectory(absolutePath);

    List<File> csvFiles = csvFileProvider.getFiles();

    assertFalse(csvFiles.isEmpty());
  }

  @DisplayName("Should return non empty list csv files from directory")
  @Test
  void shouldReturnNonEmptyListCSVFiles() {
    File directory = new File(PATH);
    String absolutePath = directory.getAbsolutePath();
    CsvFileProvider csvFileProvider = new CsvFileProviderFromDirectory(absolutePath);

    List<File> csvFiles = csvFileProvider.getFiles();

    for (File file : csvFiles) {
      assertTrue(file.getName().endsWith(".csv"));
    }
  }

  @DisplayName("If the directory not exist, an empty list of files should be returned")
  @Test
  void shouldReturnEmptyListCSVFilesWhenDirectoryPathNotExist() {
    File directory = new File("notExistPath");
    String absolutePath = directory.getAbsolutePath();
    CsvFileProvider csvFileProvider = new CsvFileProviderFromDirectory(absolutePath);

    List<File> csvFiles = csvFileProvider.getFiles();

    assertTrue(csvFiles.isEmpty());
  }
}