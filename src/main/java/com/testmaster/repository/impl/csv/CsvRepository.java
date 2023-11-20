package com.testmaster.repository.impl.csv;

import com.opencsv.CSVReader;
import com.testmaster.model.Option;
import com.testmaster.model.Question;
import com.testmaster.model.UserTest;
import com.testmaster.repository.UserTestRepository;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CsvRepository implements UserTestRepository {

  private final List<CsvFileLoader> loaders;

  public CsvRepository(List<CsvFileLoader> loaders) {
    this.loaders = loaders;
  }

  @Override
  public List<String> getTestsNames() {
    List<String> namesTests = new ArrayList<>();

    for (File csvFile : getCsvFiles()) {
      namesTests.add(csvFile.getName().replace(".csv",""));
    }

    return namesTests;
  }

  @Override
  public UserTest getTestByName(String testName) {
    UserTest test = null;

    for (File csvFile : getCsvFiles()) {
      try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
        if (csvFile.getName().replace(".csv","").equals(testName)) {
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

  private List<File> getCsvFiles() {
    List<File> files = new ArrayList<>();

    for (CsvFileLoader loader : loaders) {
      files.addAll(loader.getFiles());
    }

    return files;
  }
}
