package com.testmaster.repository.csv.impl;

import com.testmaster.repository.csv.CsvLoader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class CsvDirectoryLoader implements CsvLoader {

  private String directory;

  @Override
  public List<InputStream> getFilesStreams() {
    List<InputStream> filesStreams = new ArrayList<>();

    try {
      Path folderPath = Paths.get(directory);
      Files.walk(folderPath).forEach(file -> {
        if (!Files.isDirectory(file)) {
          InputStream inputStream = null;
          try {
            inputStream = Files.newInputStream(file);
          } catch (IOException ex) {
            log.error(ex.getMessage());
          }
          filesStreams.add(inputStream);
        }
      });
    } catch (Exception ex) {
      log.error(ex.getMessage());
    }

    return filesStreams;
  }
}
