package com.testmaster.repository.csv.impl;

import com.testmaster.repository.csv.CsvLoader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class CsvResourceLoader implements CsvLoader {

  private String zip;

  @Override
  public List<InputStream> getFilesStreams() {
    List<InputStream> filesStreams = new ArrayList<>();
    ClassLoader classLoader = getClass().getClassLoader();

    InputStream folderStream = classLoader.getResourceAsStream(zip);
    try (ZipInputStream zipInputStream = new ZipInputStream(folderStream)) {
      ZipEntry entry;
      while ((entry = zipInputStream.getNextEntry()) != null) {
        if (!entry.isDirectory()) {
          byte[] buffer = new byte[1024];
          int bytesRead;
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          while ((bytesRead = zipInputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
          }
          InputStream fileInputStream = new ByteArrayInputStream(baos.toByteArray());
          filesStreams.add(fileInputStream);
        }
        zipInputStream.closeEntry();
      }
    } catch (Exception ex) {
      log.error(ex.getMessage());
    }

    return filesStreams;
  }
}
