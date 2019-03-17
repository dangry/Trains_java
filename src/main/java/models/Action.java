package models;

import actions.ActionType;

import java.util.Objects;

public class Action {
  ActionType actionType;
  String actionData;

  public Action(ActionType actionType, String actionData) {
    this.actionData = actionData;
    this.actionType = actionType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Action)) return false;
    Action action = (Action) o;
    return actionType == action.actionType &&
            Objects.equals(actionData, action.actionData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(actionType, actionData);
  }

  public ActionType getActionType() {
    return actionType;
  }
}
