package services;

import actions.ActionType;

public interface TextService {
  void createGraph(String graphText);

  ActionType getActionType(String instruction);

  String getActionData(String instruction);

  String doAction(String instruction);
}
