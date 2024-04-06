package com.testmaster.service.impl;

import com.testmaster.config.LocaleProvider;
import com.testmaster.service.LocalizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LocalizationServiceImpl implements LocalizationService {

    private final MessageSource messageSource;

    private final LocaleProvider locale;

    public String getMessage(String codeMessage, Object... args) {
        return messageSource.getMessage(codeMessage, args, locale.getLocale());
    }
}
