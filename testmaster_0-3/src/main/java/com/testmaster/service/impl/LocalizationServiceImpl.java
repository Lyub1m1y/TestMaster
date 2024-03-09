package com.testmaster.service.impl;

import com.testmaster.config.LocaleProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LocalizationServiceImpl implements com.testmaster.service.LocalizationService {

  private final MessageSource messageSource;
  private final LocaleProvider locale;

  public String getMessage(String code) {
    return messageSource.getMessage(code, null, locale.getLocale());
  }
}
