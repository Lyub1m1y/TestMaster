package com.testmaster.repository.impl;

import com.testmaster.model.UserTest;
import com.testmaster.repository.impl.csv.CsvLoader;
import com.testmaster.repository.impl.csv.CsvRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CsvRepositoryTest {

  @Mock
  private CsvLoader csvLoaderMock;

  @InjectMocks
  private CsvRepository csvRepository;

  private final String testCsv1 = "Test1,Question1,Option1,Option2,1";
  private final String testCsv2 = "Test2,Question1,Option1,Option2,2";

  @BeforeEach
  void setUp() {
//    MockitoAnnotations.initMocks(this);
//    List<CsvLoader> loaders = new ArrayList<>();
//    loaders.add(csvLoaderMock);
//    csvRepository = new CsvRepository(loaders);
  }

  @Test
  void getTestsNames_shouldReturnNonEmptyListTestsNames() {
//    List<InputStream> inputStreams = new ArrayList<>();
//    inputStreams.add(new ByteArrayInputStream(testCsv1.getBytes()));
//    inputStreams.add(new ByteArrayInputStream(testCsv2.getBytes()));
//
//    when(csvLoaderMock.getFilesStreams()).thenReturn(inputStreams);
//
//    List<String> testNames = csvRepository.getTestsNames();
//    assertEquals(2, testNames.size());
//    assertEquals("Test1", testNames.get(0));
//    assertEquals("Test2", testNames.get(1));
//
//    verify(csvLoaderMock).getFilesStreams();
  }

  @Test
  void getTestByName_shouldReturnTestByName() {
//    List<InputStream> inputStreams = new ArrayList<>();
//    inputStreams.add(new ByteArrayInputStream(testCsv1.getBytes()));
//    inputStreams.add(new ByteArrayInputStream(testCsv2.getBytes()));
//
//    when(csvLoaderMock.getFilesStreams()).thenReturn(inputStreams);
//    UserTest test1 = csvRepository.getTestByName("Test1");
//
//    assertEquals("Test1", test1.getTestName());
//
//    verify(csvLoaderMock).getFilesStreams();
  }
}