package com.testmaster.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Question {

  private String text;
  private List<Option> options;

}
