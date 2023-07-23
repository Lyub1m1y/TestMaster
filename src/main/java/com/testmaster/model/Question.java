package com.testmaster.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Question {

  private String text;
  private List<String> options;
  private int correctOptionIndex;

}
