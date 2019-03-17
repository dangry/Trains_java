package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileController {

  private List<String> fileLines = new ArrayList<>();

  public FileController(String fileName) throws FileNotFoundException {
    File file = new File(fileName);
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      fileLines.add(scanner.nextLine());
    }
  }

  public List<String> getFileLines() {
    return fileLines;
  }
}
