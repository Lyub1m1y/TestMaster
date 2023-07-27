package com.testmaster.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class Option {

  @NonNull
  private String option;
  @Getter
  @Setter
  private boolean correct;
}
