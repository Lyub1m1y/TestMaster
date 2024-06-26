package com.testmaster.repository.impl.csv.impl;

import com.testmaster.config.Settings;
import com.testmaster.service.impl.LocalizationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@EnableConfigurationProperties(Settings.class)
@SpringBootTest(classes = {LocalizationServiceImpl.class, CsvFileProviderFromResources.class})
class CsvFileProviderFromResourcesTest {

    @Autowired
    private CsvFileProviderFromResources csvFileProvider;

    @Test
    @DisplayName("Should return non empty list files from resources")
    void shouldReturnNonEmptyListFiles() {
        assertFalse(csvFileProvider.getFiles().isEmpty());
    }
}
