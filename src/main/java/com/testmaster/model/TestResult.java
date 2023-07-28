package com.testmaster.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TestResult {

  @NonNull
  private int numberOfQuestions;
  private int numberOfCorrectAnswer;
}
