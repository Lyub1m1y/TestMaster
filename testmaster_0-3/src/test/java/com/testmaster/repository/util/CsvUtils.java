package com.testmaster.repository.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@UtilityClass
@Slf4j
public class CsvUtils {

    private static final String FILE_NAME = "file.csv";

    public static File getFile() throws IOException {
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        File file = new File(tempDir, FILE_NAME);
        file.createNewFile();

        return file;
    }
}