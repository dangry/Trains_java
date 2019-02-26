import controllers.FileController;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    try {

      FileController fileController = new FileController(args[0]);
      List<String> fileControllerResponses = fileController.processFile();

      for(String response : fileControllerResponses) {
        System.out.println(response);
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
