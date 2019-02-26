package services;

import actions.ActionType;
import actions.GraphAction;
import actions.GraphActionFactory;
import models.Graph;
import models.Train;
import utils.exception.InvalidPathNameException;

import static actions.ActionType.*;
import static actions.ActionType.SHORTEST_ROUTE;

public class TextServiceImpl implements TextService {
  private static final String ROUTE_DISTANCE_PATTERN = "The distance of the route";
  private static final String TRIPS_STARTING_AT_PATTERN = "The number of trips starting at";
  private static final String SHORTEST_ROUTE_PATTERN =
      "The length of the shortest route (in terms of distance to travel) from";
  private static final String DIFFERENT_ROUTES_MAX_DISTANCE_PATTERN =
      "The number of different routes from";

  private static final String WITH_A_DISTANCE_OF_LESS_THAN = "with a distance of less than";
  private static final String WITH_A_MAXIMUM_OF_STRING = "with a maximum of";
  private static final String AND_ENDING_AT_STRING = "and ending at";
  private static final String WITH_EXACTLY_STRING = "with exactly";
  private static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";
  private static final String STOPS_STRING = "stops";
  private static final String TO_STRING = "to";
  private static final String SPACE_STRING = " ";
  private static final String DOT_STRING = ".";
  private static final String NOTHING = "";

  private Graph graph;

  GraphService graphServiceInterface = new GraphServiceImpl();

  @Override
  public void createGraphFromText(String graphText) {
    try {
      this.graph = graphServiceInterface.createGraphFromText(graphText);
    } catch (InvalidPathNameException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String doAction(String instruction) {

    String actionResponse;

    ActionType actionType = getActionType(instruction);
    String actionData = getActionData(instruction);

    Train train = createTrainFromData(actionData, actionType);

    GraphAction graphAction = GraphActionFactory.getAction(actionType);
    graphAction.setData(train);

    int result = graphAction.execute();

    if (result > -1) {
      actionResponse = String.valueOf(result);
    } else {
      actionResponse = NO_SUCH_ROUTE;
    }

    return actionResponse;
  }

  @Override
  public ActionType getActionType(String instruction) {
    if (instruction.startsWith(ROUTE_DISTANCE_PATTERN)) {
      return ROUTE_DISTANCE;
    } else if (instruction.startsWith(TRIPS_STARTING_AT_PATTERN)) {
      if (instruction.indexOf(WITH_A_MAXIMUM_OF_STRING) > -1) {
        return NUMBER_OF_TRIPS_MAX_STOPS;
      } else if (instruction.indexOf(WITH_EXACTLY_STRING) > -1) {
        return NUMBER_OF_TRIPS_EXACT_STOPS;
      }
    } else if (instruction.startsWith(SHORTEST_ROUTE_PATTERN)) {
      return SHORTEST_ROUTE;
    } else if (instruction.startsWith(DIFFERENT_ROUTES_MAX_DISTANCE_PATTERN)) {
      return DIFFERENT_ROUTES_MAX_DISTANCE;
    }

    return null;
  }

  @Override
  public String getActionData(String instruction) {
    ActionType instructionActionType = getActionType(instruction);
    if (ROUTE_DISTANCE == instructionActionType) {
      return getRouteDistanceData(instruction);
    } else if (NUMBER_OF_TRIPS_MAX_STOPS == instructionActionType) {
      return getNumberOfTripStopsData(instruction);
    } else if (NUMBER_OF_TRIPS_EXACT_STOPS == instructionActionType) {
      return getNumberOfTripStopsData(instruction);
    } else if (SHORTEST_ROUTE == instructionActionType) {
      return getShortestRouteData(instruction);
    } else if (DIFFERENT_ROUTES_MAX_DISTANCE == instructionActionType) {
      return getDifferentRoutesMaxDistanceData(instruction);
    }

    return null;
  }

  private String getDifferentRoutesMaxDistanceData(String instruction) {

    if (instructionHasExtraInformation(instruction)) {
      instruction = removeExtraInformationFromInstruction(instruction);
    }

    instruction =
        instruction
            .replace(DIFFERENT_ROUTES_MAX_DISTANCE_PATTERN, NOTHING)
            .replace(WITH_A_DISTANCE_OF_LESS_THAN, NOTHING)
            .replace(TO_STRING, NOTHING)
            .replace(SPACE_STRING, NOTHING)
            .replace(DOT_STRING, NOTHING);

    return instruction;
  }

  private String getShortestRouteData(String instruction) {

    if (instructionHasExtraInformation(instruction)) {
      instruction = removeExtraInformationFromInstruction(instruction);
    }

    instruction =
        instruction
            .replace(SHORTEST_ROUTE_PATTERN, NOTHING)
            .replace(TO_STRING, NOTHING)
            .replace(SPACE_STRING, NOTHING)
            .replace(DOT_STRING, NOTHING);

    return instruction;
  }

  private String getNumberOfTripStopsData(String instruction) {

    if (instructionHasExtraInformation(instruction)) {
      instruction = removeExtraInformationFromInstruction(instruction);
    }

    instruction =
        instruction
            .replace(TRIPS_STARTING_AT_PATTERN, NOTHING)
            .replace(AND_ENDING_AT_STRING, NOTHING)
            .replace(WITH_A_MAXIMUM_OF_STRING, NOTHING)
            .replace(WITH_EXACTLY_STRING, NOTHING)
            .replace(STOPS_STRING, NOTHING)
            .replace(SPACE_STRING, NOTHING)
            .replace(DOT_STRING, NOTHING);
    return instruction;
  }

  private String getRouteDistanceData(String instruction) {

    if (instructionHasExtraInformation(instruction)) {
      instruction = removeExtraInformationFromInstruction(instruction);
    }

    return instruction
        .replaceAll(ROUTE_DISTANCE_PATTERN, NOTHING)
        .replace(SPACE_STRING, NOTHING)
        .replace(DOT_STRING, NOTHING);
  }

  private String removeExtraInformationFromInstruction(String instruction) {
    return instruction.substring(0, instruction.indexOf(DOT_STRING + SPACE_STRING));
  }

  private boolean instructionHasExtraInformation(String instruction) {
    return instruction.indexOf(DOT_STRING + SPACE_STRING) > -1;
  }

  private Train createTrainFromData(String instructionData, ActionType actionType) {

    if (actionType == ROUTE_DISTANCE) {
      return new Train(instructionData, graph);
    } else if (actionType == SHORTEST_ROUTE) {
      char start = instructionData.charAt(0);
      char end = instructionData.charAt(1);
      return new Train(start, end, graph);
    } else {
      char start = instructionData.charAt(0);
      char end = instructionData.charAt(1);
      int limit = Integer.valueOf(instructionData.substring(2));
      return new Train(start, end, graph, limit);
    }
  }
}
