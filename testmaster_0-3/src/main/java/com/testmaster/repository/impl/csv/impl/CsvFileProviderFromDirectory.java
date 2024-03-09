package com.testmaster.repository.impl.csv.impl;

import com.testmaster.config.DirectoryPathProvider;
import com.testmaster.exception.TestRetrieveException;
import com.testmaster.repository.impl.csv.CsvFileProvider;
import com.testmaster.service.LocalizationService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Slf4j
@Setter
@Component
public class CsvFileProviderFromDirectory implements CsvFileProvider {

  private static final String PROJECT_ABSOLUTE_PATH = System.getProperty("user.dir");
  private final String directoryPath;
  private final LocalizationService localizationService;

  public CsvFileProviderFromDirectory(DirectoryPathProvider directoryPathProvider, LocalizationService localizationService) {
    this.directoryPath = PROJECT_ABSOLUTE_PATH + "/" + directoryPathProvider.getDirectoryPath();
    this.localizationService = localizationService;
  }

  @Override
  public Map<String, InputStream> getFiles() {
    try {
      File folder = new File(directoryPath);
      File[] files = folder.listFiles();

      if (isNull(files)) {
        throw new NullPointerException("Files is null");
      }

      return Arrays.stream(files)
          .filter(file -> file.getName().endsWith(".csv"))
          .collect(Collectors.toMap(
              file -> Objects.requireNonNull(file.getName()).replace(".csv", ""),
              file -> {
                try {
                  return new FileInputStream(file);
                } catch (FileNotFoundException e) {
                  throw new RuntimeException("Error when getting inputStream from a file: " + file.getName(), e);
                }
              }));
    } catch (Exception ex) {
        log.error(ex.getMessage(), ex);
        throw new TestRetrieveException(
            String.format(localizationService.getMessage("TEST_RETRIEVE_ERROR_MESSAGE"), "directory", directoryPath));
    }
  }
}
