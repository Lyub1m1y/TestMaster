package com.testmaster.repository.impl.csv.impl;

import com.testmaster.config.ResourcesPathProvider;
import com.testmaster.exception.TestRetrieveException;
import com.testmaster.repository.impl.csv.CsvFileProvider;
import com.testmaster.service.LocalizationService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Setter
@Component
public class CsvFileProviderFromResources implements CsvFileProvider {

    private final String resourcesPath;

    private final LocalizationService localizationService;

    public CsvFileProviderFromResources(ResourcesPathProvider resourcesPathProvider,
                                        LocalizationService localizationService) {
        this.resourcesPath = resourcesPathProvider.getResourcesPath();
        this.localizationService = localizationService;
    }

    @Override
    public Map<String, InputStream> getFiles() {
        Map<String, InputStream> testsFromResources = new HashMap<>();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        try {
            Resource[] resources = resolver.getResources(resourcesPath + "/*.csv");
            Arrays.stream(resources).forEach(resource -> {
                try {
                    testsFromResources.put(Objects.requireNonNull(resource.getFilename())
                            .replace(".csv", ""), resource.getInputStream());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new TestRetrieveException(String.format(localizationService.getMessage(
                    "TEST_RETRIEVE_ERROR_MESSAGE"), "resources", resourcesPath)
            );
        }

        return testsFromResources;
    }
}
