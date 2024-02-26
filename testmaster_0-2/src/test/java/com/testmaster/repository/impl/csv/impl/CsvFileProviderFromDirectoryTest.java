package com.testmaster.repository.impl.csv.impl;

import com.testmaster.config.Settings;
import com.testmaster.utils.MessageUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@EnableConfigurationProperties(Settings.class)
@SpringBootTest(classes = {MessageUtils.class, CsvFileProviderFromDirectory.class})
class CsvFileProviderFromDirectoryTest {

  @Autowired
  private CsvFileProviderFromDirectory csvFileProvider;

  @DisplayName("Should return non empty list files from directory")
  @Test
  void shouldReturnNonEmptyListFiles() {
    assertFalse(csvFileProvider.getFiles().isEmpty());
  }
}