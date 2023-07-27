package com.testmaster.repository.impl;

import com.opencsv.CSVReader;
import com.testmaster.model.UserTest;
import com.testmaster.repository.TestRepository;
import com.testmaster.repository.csv.CsvLoader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CsvRepository implements TestRepository {

  private final List<CsvLoader> loaders;

  public CsvRepository(List<CsvLoader> loaders) {
    this.loaders = loaders;
  }

  @Override
  public List<String> getTestsNames() {
    List<String> namesTests = new ArrayList<>();
    List<InputStream> filesStreams = new ArrayList<>();

    for (CsvLoader loader : loaders) {
      filesStreams.addAll(loader.getFilesStreams());
    }

    for (InputStream fileStream : filesStreams) {
      try (CSVReader reader = new CSVReader(new InputStreamReader(fileStream))) {
        String[] line = reader.readNext();
        namesTests.add(line[0]);
      } catch (Exception ex) {
        log.error(ex.getMessage());
      }
    }
    return namesTests;
  }

  @Override
  public UserTest getTestByName() {
    return null;
  }
}
