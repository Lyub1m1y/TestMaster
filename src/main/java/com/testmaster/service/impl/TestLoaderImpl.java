package com.testmaster.service.impl;

import com.opencsv.CSVReader;
import com.testmaster.model.Question;
import com.testmaster.model.Test;
import com.testmaster.service.TestLoader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

@Slf4j
public class TestLoaderImpl implements TestLoader {

  private static final String DIRECTORY_TESTS = "tests";

  @Override
  public List<Test> loadTestsFromResources() {
    List<Test> tests = new ArrayList<>();
    ClassPathResource resource = new ClassPathResource(DIRECTORY_TESTS);
    try {
      File testsFolder = resource.getFile();

      for (File file : testsFolder.listFiles()) {
        if (file.isFile() && file.getName().endsWith(".csv")) {
          Test test = loadTestFromFile(file);
          tests.add(test);
        }
      }
    } catch (IOException ex) {
      log.error("The directory doesn't exist", ex);
    }

    return tests;
  }

  private Test loadTestFromFile(File file) {
    List<Question> questions = new ArrayList<>();
    try (CSVReader reader = new CSVReader(new FileReader(file))) {
      String[] line;

      while ((line = reader.readNext()) != null) {
        if (line.length != 0) {
          String questionText = line[0];
          int correctOptionIndex = Integer.parseInt(line[line.length - 1]);
          List<String> options = new ArrayList<>();
          for (int i = 1; i < line.length - 1; i++) {
            options.add(line[i]);
          }
          Question question = new Question(questionText, options, correctOptionIndex);
          questions.add(question);
        }
      }
    } catch (Exception ex) {
      log.error(ex.getMessage());
    }
    String testName = file.getName().substring(0, file.getName().lastIndexOf(".csv"));

    return new Test(testName, questions);
  }
}
