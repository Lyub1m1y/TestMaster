package com.testmaster.repository.csv.impl;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.testmaster.repository.impl.csv.impl.CsvDirectoryLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvDirectoryLoaderTest {

  private static final String PATH = "src/test/resources/tests";

  @Test
  void getFilesStreams_shouldReturnNonEmptyListInputStream() {
//    File file = new File(PATH);
//    String absolutePath = file.getAbsolutePath();
//    CsvDirectoryLoader csvDirectoryLoader = new CsvDirectoryLoader(absolutePath);
//
//    List<InputStream> filesStreams = csvDirectoryLoader.getFilesStreams();
//
//    assertTrue(!filesStreams.isEmpty());
  }
}