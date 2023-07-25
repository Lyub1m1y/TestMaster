package com.testmaster.service.impl;

import com.opencsv.CSVReader;
import com.testmaster.model.CustomTest;
import com.testmaster.model.Question;
import com.testmaster.service.TestLoader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class TestCsvLoaderImpl implements TestLoader {

  @NonNull
  private final String directory;
  private static final String RESOURCES_TESTS = "src/main/resources/tests";

  @Override
  public List<CustomTest> loadTests() {
    List<CustomTest> tests = new ArrayList<>();
    try {
      List<File> testPaths =  List.of(new File(RESOURCES_TESTS), new File(directory));

      for (File testsFolder : testPaths) {
        if (!testsFolder.exists() || !testsFolder.isDirectory()) {
          log.error("The directory doesn't exist or it's not a directory.");
          return tests;
        }

        File[] files = testsFolder.listFiles();

        if (files == null) {
          log.error("Failed to list files in the directory.");
          return tests;
        }

        for (File file : files) {
          if (file.isFile() && file.getName().endsWith(".csv")) {
            try {
              CustomTest test = loadTestFromFile(file);
              tests.add(test);
            } catch (Exception ex) {
              log.error("Couldn't read the "  + file.getName() + ". " + ex.getMessage());
            }
          }
        }
      }
    } catch (Exception ex) {
      log.error("The directory doesn't exist." + ex.getMessage());
    }

    return tests;
  }

  private CustomTest loadTestFromFile(File file) throws Exception {
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
    }
    String testName = file.getName().substring(0, file.getName().lastIndexOf(".csv"));

    return new CustomTest(testName, questions);
  }
}
