package com.testmaster.repository.impl.csv;

import com.testmaster.model.UserTest;
import com.testmaster.repository.util.CsvUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CsvRepository.class, CsvFileProvider.class})
class CsvRepositoryTest {

    @Mock
    private CsvFileProvider csvFileProviderMock;

    private UserTest userTest;

    private CsvRepository csvRepository;

    @BeforeEach
    void setUp() throws IOException {
        csvRepository = new CsvRepository(List.of(csvFileProviderMock));
        userTest = new UserTest("testName", Arrays.asList());
        Map<String, InputStream> tests = Map.of(userTest.getTestName(), new FileInputStream(CsvUtils.getFile()));
        when(csvFileProviderMock.getFiles()).thenReturn(tests);
    }

    @Test
    @DisplayName("Should return non empty list file names")
    void shouldReturnNonEmptyListTestNames() {
        assertEquals(Arrays.asList(userTest.getTestName()), csvRepository.getTestNames());
        verify(csvFileProviderMock, times(1)).getFiles();
    }

    @Test
    @DisplayName("Should return test by name")
    void shouldReturnTestByName() {
        assertEquals(userTest, csvRepository.getTestByName(userTest.getTestName()));
        verify(csvFileProviderMock, times(1)).getFiles();
    }

    @Test
    @DisplayName("If test name not exist should return null")
    void shouldReturnNullWhenTestNameNotExist() throws FileNotFoundException {
        assertNull(csvRepository.getTestByName("testNameNotExist"));
        verify(csvFileProviderMock, times(1)).getFiles();
    }
}