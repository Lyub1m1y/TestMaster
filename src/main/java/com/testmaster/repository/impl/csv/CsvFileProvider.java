package com.testmaster.repository.impl.csv;

import java.io.File;
import java.util.List;

public interface CsvFileProvider {

  List<File> getFiles();
}
