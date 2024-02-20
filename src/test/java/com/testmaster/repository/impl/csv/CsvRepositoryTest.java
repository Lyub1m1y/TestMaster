package com.testmaster.repository.impl.csv;

import com.testmaster.model.UserTest;
import com.testmaster.repository.CsvUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CsvRepositoryTest {

  @Mock
  private CsvFileProvider csvFileProviderMock;
  @InjectMocks
  private CsvRepository csvRepository;

  @BeforeEach
  void setUp() {
    List<CsvFileProvider> providers = new ArrayList<>();
    providers.add(csvFileProviderMock);
    csvRepository = new CsvRepository(providers, null);
  }

  @DisplayName("Should return non empty list file names")
  @Test
  void shouldReturnNonEmptyListTestNames() {
    when(csvFileProviderMock.getFiles()).thenReturn(CsvUtils.getCSVFiles());

    List<String> actualTestNames = csvRepository.getTestNames();

    verify(csvFileProviderMock).getFiles();
    assertEquals(CsvUtils.getCSVFilesNames(CsvUtils.getCSVFiles()), actualTestNames);
  }

  @DisplayName("Should return test by name")
  @Test
  void shouldReturnTestByName() {
    List<UserTest> expectedUserTests = CsvUtils.getUserTests();
    List<UserTest> actualUserTests = new ArrayList<>();
    List<File> files = CsvUtils.getCSVFiles();
    when(csvFileProviderMock.getFiles()).thenReturn(files);

    for (String testName : CsvUtils.getCSVFilesNames(files)) {
      actualUserTests.add(csvRepository.getTestByName(testName));
    }

    assertEquals(expectedUserTests, actualUserTests);
  }

  @DisplayName("If test name not exist should return null")
  @Test
  void shouldReturnNullWhenTestNameNotExist() {
    List<File> files = CsvUtils.getCSVFiles();
    when(csvFileProviderMock.getFiles()).thenReturn(files);

    UserTest actualUserTest = csvRepository.getTestByName("testNameNotExist");

    assertNull(actualUserTest);
  }
}