package com.testmaster.service.impl.io.system;

import com.testmaster.exception.InvalidNumberByIntervalException;
import com.testmaster.service.impl.io.InputStreamProvider;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@Setter
public class SystemInputService implements InputStreamProvider {

  @Autowired
  private Scanner scanner;

  @Override
  public String readLine() {
    return scanner.nextLine();
  }

  @Override
  public int readIntByInterval(int min, int max) {
    int input = Integer.parseInt(scanner.nextLine());
    if (input >= min && input <= max) {
      return input;
    } else {
      throw new InvalidNumberByIntervalException("Please enter a number between " + min + " and " + max + ".");
    }
  }
}
