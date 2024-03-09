package com.testmaster.utils;

import com.testmaster.config.LocaleProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MessageUtils {

  private final MessageSource messageSource;
  private final LocaleProvider locale;

  public String getMessage(String code) {
    return messageSource.getMessage(code, null, locale.getLocale());
  }
}
