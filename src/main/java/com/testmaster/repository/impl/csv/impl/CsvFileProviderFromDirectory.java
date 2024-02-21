package com.testmaster.repository.impl.csv.impl;

import com.testmaster.exception.TestRetrieveException;
import com.testmaster.repository.impl.csv.CsvFileProvider;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static com.testmaster.app.TestMasterConstants.TEST_RETRIEVE_ERROR_MESSAGE;

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
    try {
      File folder = new File(directoryUrl);
      File[] files = folder.listFiles();

      if (isNull(files)) {
        throw new NullPointerException("Files is null");
      }

      return Arrays.stream(files)
          .filter(file -> file.getName().endsWith(".csv"))
          .collect(Collectors.toList());
    } catch (Exception ex) {
        log.error(ExceptionUtils.getStackTrace(ex));
        throw new TestRetrieveException(String.format(TEST_RETRIEVE_ERROR_MESSAGE, "directory", directoryUrl));
    }
  }
}
