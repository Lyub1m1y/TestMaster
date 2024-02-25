package com.testmaster.repository.impl.csv;

import java.io.InputStream;
import java.util.Map;

public interface CsvFileProvider {

  Map<String, InputStream> getFiles();
}
