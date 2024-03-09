package com.testmaster.repository;

import com.testmaster.model.UserTest;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CsvUtils {

    private static final String FILE_NAME_1 = "file1.csv";

    private static final String FILE_NAME_2 = "file2.csv";

    private static final String FILE_NAME_3 = "file3.csv";

    public static List<File> getCSVFiles() {
        File tempDir = new File(System.getProperty("java.io.tmpdir"));

        File file1 = new File(tempDir, FILE_NAME_1);
        File file2 = new File(tempDir, FILE_NAME_2);
        File file3 = new File(tempDir, FILE_NAME_3);

        try {
            file1.createNewFile();
            file2.createNewFile();
            file3.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return List.of(file1, file2, file3);
    }

    public static List<String> getCSVFilesNames(List<File> csvFiles) {
        List<String> filesNames = new ArrayList<>();

        for (File file : csvFiles) {
            filesNames.add(file.getName().replace(".csv", ""));
        }

        return filesNames;
    }

    public static List<UserTest> getUserTests() {
        List<UserTest> userTests = new ArrayList<>();

        for (String testName : getCSVFilesNames(getCSVFiles())) {
            userTests.add(new UserTest(testName, List.of()));
        }

        return userTests;
    }
}