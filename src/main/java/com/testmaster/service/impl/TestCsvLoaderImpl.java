package com.testmaster.service.impl;

import com.opencsv.CSVReader;
import com.testmaster.model.CustomTest;
import com.testmaster.model.Question;
import com.testmaster.service.TestLoader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

@RequiredArgsConstructor
@Slf4j
public class TestCsvLoaderImpl implements TestLoader {

  @NonNull
  private final String directory;
  private static final String RESOURCES_TESTS = "tests";

  @Override
  public List<CustomTest> loadTests() {
    List<CustomTest> tests = new ArrayList<>();
    try {
      tests = loadTestsFromStream();

      List<File> testPaths =  List.of(new File(directory));

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


  private List<CustomTest> loadTestsFromStream() throws Exception {
    List<CustomTest> tests = new ArrayList<>();
    List<Question> questions = new ArrayList<>();
    List<InputStream> streams =  getInputStreamsForCsvFiles();

    Files.walk(Path.of(RESOURCES_TESTS), null)

//    IOUtils.resourceToString("/tests/History.csv");
//    new Files.find();
//    FileUtils.
//
//
//    List<File> csvFiles = (List<File>) FileUtils.listFiles(RESOURCES_TESTS, new String[]{"csv"}, false)

    try (Stream<Path> paths = Files.walk(Paths.get("/tests"))) {
      paths
          .filter(Files::isRegularFile)
          .forEach(System.out::println);
    }

    IOUtils.resourceToString()

    int it = 0;
    for (InputStream inputStreamFile : streams) {
      try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputStreamFile))) {
        String[] line;
        while ((line = csvReader.readNext()) != null) {
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
      String testName = "Stream" + ++it;
      tests.add(new CustomTest(testName, questions));
    }

    return tests;
  }


  private List<InputStream> getInputStreamsForCsvFiles() {
    List<InputStream> csvInputStreams = new ArrayList<>();
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    InputStream testsDirectoryStream = classLoader.getResourceAsStream(RESOURCES_TESTS);

    if (testsDirectoryStream != null) {
      try (BufferedReader br = new BufferedReader(new InputStreamReader(testsDirectoryStream))) {
        String csvFile;
        while ((csvFile = br.readLine()) != null) {
          if (csvFile.toLowerCase().endsWith(".csv")) {
            // Get the InputStream for each CSV file.
            InputStream csvInputStream = classLoader.getResourceAsStream(RESOURCES_TESTS + "/"
                + csvFile);
            if (csvInputStream != null) {
              csvInputStreams.add(csvInputStream);
            }
          }
        }
      } catch (Exception ex) {
        log.error(ex.getMessage());
      }
    } else {
      System.err.println("Directory not found inside the JAR.");
    }

    return csvInputStreams;
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
