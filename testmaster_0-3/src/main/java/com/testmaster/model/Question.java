package com.testmaster.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Question {

    private String text;

    private List<Option> options;

}
