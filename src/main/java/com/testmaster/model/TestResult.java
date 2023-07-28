package com.testmaster.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TestResult {

  private int numberOfQuestions;
  private int numberOfCorrectAnswer;
}
