package com.testmaster.repository.csv.impl;

import java.io.InputStream;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvResourceLoaderTest {

  private static final String ZIP = "tests.zip";

  @Test
  void getFilesStreams_shouldReturnNonEmptyListInputStream() {
    CsvResourceLoader csvResourceLoader = new CsvResourceLoader(ZIP);

    List<InputStream> filesStreams = csvResourceLoader.getFilesStreams();

    assertTrue(!filesStreams.isEmpty());
  }
}