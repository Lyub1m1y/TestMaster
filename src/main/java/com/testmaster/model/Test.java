package com.testmaster.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Test {

  private String name;
  private List<Question> questions;

}
