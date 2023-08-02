package com.testmaster.repository.impl;

import com.opencsv.CSVReader;
import com.testmaster.model.Option;
import com.testmaster.model.Question;
import com.testmaster.model.UserTest;
import com.testmaster.repository.UserTestRepository;
import com.testmaster.repository.csv.CsvLoader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CsvRepository implements UserTestRepository {

  private final List<CsvLoader> loaders;

  public CsvRepository(List<CsvLoader> loaders) {
    this.loaders = loaders;
  }

  @Override
  public List<String> getTestsNames() {
    List<String> namesTests = new ArrayList<>();

    for (InputStream fileStream : getFilesStreams()) {
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
  public UserTest getTestByName(String testName) {
    UserTest test = null;

    for (InputStream fileStream : getFilesStreams()) {
      try (CSVReader reader = new CSVReader(new InputStreamReader(fileStream))) {
        String name = reader.readNext()[0];
        if (name.equals(testName)) {
          String[] line;
          List<Question> questions = new ArrayList<>();
          while ((line = reader.readNext()) != null) {
            if (line.length != 0) {
              String questionText = line[0];
              int correctOptionIndex = (Integer.parseInt(line[line.length - 1])) - 1;
              List<Option> options = new ArrayList<>();
              for (int i = 1; i < line.length - 1; i++) {
                options.add(new Option(line[i]));
              }
              options.get(correctOptionIndex).setCorrect(true);
              Question question = new Question(questionText, options);
              questions.add(question);
            }
          }
          test = new UserTest(testName, questions);
        }
      } catch (Exception ex) {
        log.error(ex.getMessage());
      }
    }

    return test;
  }

  private List<InputStream> getFilesStreams() {
    List<InputStream> filesStreams = new ArrayList<>();

    for (CsvLoader loader : loaders) {
      filesStreams.addAll(loader.getFilesStreams());
    }

    return filesStreams;
  }
}
