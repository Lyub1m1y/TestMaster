package com.testmaster.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class Option {

  private final String name;
  @Setter
  private boolean correct;
}
