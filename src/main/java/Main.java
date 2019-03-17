import controllers.FileController;
import models.Action;
import services.TextService;
import services.TextServiceImpl;
import services.TrainService;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    try {

      FileController fileController = new FileController(args[0]);
      TextService textService = new TextServiceImpl();
      TrainService trainService = new TrainService(textService);

      List<String> fileLines = fileController.getFileLines();
      List<Action> actionList = new ArrayList<>();

      for (String line : fileLines) {

        if (TextServiceImpl.isGraphString(line)) {
          textService.createGraphFromText(line);
        } else {
          actionList.add(textService.getAction(line));
        }

      }

      List<String> fileControllerResponses = trainService.doActions(actionList);

      for(String response : fileControllerResponses) {
        System.out.println(response);
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
