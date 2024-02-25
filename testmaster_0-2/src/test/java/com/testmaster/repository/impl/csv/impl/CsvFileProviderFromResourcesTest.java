package com.testmaster.repository.impl.csv.impl;

import com.testmaster.Settings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = {Settings.class, CsvFileProviderFromResources.class})
class CsvFileProviderFromResourcesTest {

  @Autowired
  private CsvFileProviderFromResources csvFileProvider;

  @DisplayName("Should return non empty list files from resources")
  @Test
  void shouldReturnNonEmptyListFiles() {
    assertFalse(csvFileProvider.getFiles().isEmpty());
  }
}