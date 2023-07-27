package com.testmaster.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserTest {

  private String testName; // TODO mb delete
  private List<Question> questions;

}
