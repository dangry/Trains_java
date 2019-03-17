package services;

import actions.ActionType;
import models.Action;
import models.Graph;

public interface TextService {
  void createGraphFromText(String graphText);

  ActionType getActionType(String instruction);

  String getActionData(String instruction);

  Action getAction(String routeDistanceString);

  Graph getGraph();
}
