package com.testmaster.repository.csv;

import java.io.InputStream;
import java.util.List;

public interface CsvLoader {

  List<InputStream> getFilesStreams();
}
