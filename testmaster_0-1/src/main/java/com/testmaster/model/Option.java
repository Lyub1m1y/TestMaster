package com.testmaster.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class Option {

  @NonNull
  private String option;
  @Setter
  private boolean correct;
}
