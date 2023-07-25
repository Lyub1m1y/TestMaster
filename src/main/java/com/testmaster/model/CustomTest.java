package com.testmaster.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomTest {

  private String testName;
  private List<Question> questions;

}
