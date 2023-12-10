package com.testmaster.repository.impl.csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.testmaster.model.Option;
import com.testmaster.model.Question;
import com.testmaster.model.UserTest;
import com.testmaster.repository.UserTestRepository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.testmaster.repository.impl.csv.impl.CsvFileProviderFromDirectory;
import com.testmaster.repository.impl.csv.impl.CsvFileProviderFromResources;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@Setter
public class CsvRepository implements UserTestRepository {

  private List<CsvFileProvider> providers;

  public CsvRepository(@Autowired CsvFileProviderFromDirectory csvFileProviderFromDirectory,
                       @Autowired CsvFileProviderFromResources csvFileProviderFromResources) {
      this.providers = List.of(csvFileProviderFromDirectory, csvFileProviderFromResources);
  }

  @Override
  public List<String> getTestNames() {
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
        if (isMatchingTestName(csvFile, testName)) {
          List<Question> questions = parseQuestionsFromCSV(reader);
          test = new UserTest(testName, questions);
        }
      } catch (Exception ex) {
        log.error(ex.getMessage());
      }
    }

    return test;
  }

  private boolean isMatchingTestName(File csvFile, String testName) {
    String fileName = csvFile.getName();
    String fileNameWithoutExtension = fileName.replace(".csv", "");
    return fileNameWithoutExtension.equals(testName);
  }

  private List<Question> parseQuestionsFromCSV(CSVReader reader) throws CsvValidationException, IOException {
    List<Question> questions = new ArrayList<>();

    String[] line;
    while ((line = reader.readNext()) != null) {
      if (line.length != 0) {
        String questionText = line[0];
        int correctOptionIndex = Integer.parseInt(line[line.length - 1]) - 1;
        List<Option> options = parseOptions(line);
        options.get(correctOptionIndex).setCorrect(true);
        Question question = new Question(questionText, options);
        questions.add(question);
      }
    }

    return questions;
  }

  private List<Option> parseOptions(String[] line) {
    List<Option> options = new ArrayList<>();

    for (int i = 1; i < line.length - 1; i++) {
      options.add(new Option(line[i]));
    }

    return options;
  }

  private List<File> getCsvFiles() {
    List<File> files = new ArrayList<>();

    for (CsvFileProvider provider : providers) {
      List<File> providerFiles =  provider.getFiles();
      if (providerFiles != null) {
        files.addAll(providerFiles);
      }
    }

    return files;
  }
}
