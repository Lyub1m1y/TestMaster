package com.testmaster.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TestResult {

  private User user;
  @NonNull
  private String testName;
  @NonNull
  private int numberOfQuestions;
  private int numberOfCorrectAnswer;
}
