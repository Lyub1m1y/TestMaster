package com.testmaster.repository.impl.csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.testmaster.exception.TestRetrieveException;
import com.testmaster.model.Option;
import com.testmaster.model.Question;
import com.testmaster.model.UserTest;
import com.testmaster.repository.UserTestRepository;
import com.testmaster.service.InOutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class CsvRepository implements UserTestRepository {

  private final List<CsvFileProvider> providers;
  private final InOutService inOutService;

  public CsvRepository(List<CsvFileProvider> providers, InOutService inOutService) {
      this.providers = providers;
      this.inOutService = inOutService;
  }

  @Override
  public List<String> getTestNames() {
    return new ArrayList<>(getFiles().keySet());
  }

  @Override
  public UserTest getTestByName(String testName) {
    Map<String, InputStream> tests = getFiles();
    for (Map.Entry<String, InputStream> test : tests.entrySet()) {
      try (CSVReader reader = new CSVReader(new InputStreamReader(test.getValue()))) {
        if (testName.equals(test.getKey())) {
          List<Question> questions = parseQuestionsFromCSV(reader);
          return new UserTest(testName, questions);
        }
      } catch (Exception ex) {
        log.error(ex.getMessage());
      }
    }

    return null;
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

  private Map<String, InputStream> getFiles() {
    Map<String, InputStream> tests = new HashMap<>();

    for (CsvFileProvider provider : providers) {
      try {
        Map<String, InputStream> providerTests = provider.getFiles();
        if (providerTests != null) {
          tests.putAll(providerTests);
        }
      } catch (TestRetrieveException ex) {
        log.error(ex.getMessage(), ex);
        inOutService.printMessage(ex.getMessage());
        inOutService.printMessageInterval();
      }
    }

    return tests;
  }
}
