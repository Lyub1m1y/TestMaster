package com.testmaster.repository.impl.csv;

import java.io.InputStream;
import java.util.List;

public interface CsvLoader {

  List<InputStream> getFilesStreams();
}
