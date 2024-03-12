package com.testmaster.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class UserTest {

    private String testName;

    private List<Question> questions;

}
