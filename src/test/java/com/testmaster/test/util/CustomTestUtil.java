package com.testmaster.test.util;

import com.testmaster.model.CustomTest;
import com.testmaster.model.Question;
import java.util.List;

public class CustomTestUtil {

  public static CustomTest getTest() {
    return new CustomTest("Math", List.of(
        new Question("2+2?", List.of("5","22","4"),3),
        new Question("10+10?", List.of("1010", "20", "10"),2))
    );
  }
}
