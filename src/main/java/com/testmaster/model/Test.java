package com.testmaster.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Test {

  private String testName;
  private List<Question> questions;

}
