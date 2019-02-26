package controllers;

import services.TextService;
import services.TextServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileController {

  private static final char COMMA_CHAR = ',';

  private TextService textService = new TextServiceImpl();

  private List<String> fileLines = new ArrayList<>();

  public FileController(String fileName) throws FileNotFoundException {
    File file = new File(fileName);
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      fileLines.add(scanner.nextLine());
    }
  }

  public List<String> processFile() {

    List<String> response = new ArrayList<>();

    for (String instruction : fileLines) {
      if (isGraph(instruction)) {
        textService.createGraphFromText(instruction);
      } else {
        String actionResponse = textService.doAction(instruction);
        response.add(actionResponse);
      }
    }

    return response;
  }

  private boolean isGraph(String instruction) {
    return instruction.charAt(3) == COMMA_CHAR && instruction.charAt(8) == COMMA_CHAR
        ? true
        : false;
  }
}
