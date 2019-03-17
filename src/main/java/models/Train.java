package models;

import static actions.ActionType.ROUTE_DISTANCE;
import static actions.ActionType.SHORTEST_ROUTE;

public class Train {

  public char currentNodeName;
  public char destinationNodeName;
  public int limit;
  public String route;

  public Graph graph;

  public Train(char currentNodeName, char destinationNodeName, Graph graph, int limit) {
    this.currentNodeName = currentNodeName;
    this.destinationNodeName = destinationNodeName;
    this.limit = limit;
    this.graph = graph;
  }

  public Train(String route, Graph graph) {
    this.route = route;
    this.graph = graph;
  }

  public Train(char currentNodeName, char destinationNodeName, Graph graph) {
    this.currentNodeName = currentNodeName;
    this.destinationNodeName = destinationNodeName;
    this.graph = graph;
  }

  public Node getCurrentNode() {
    return graph.nodes.get(currentNodeName);
  }

  public static Train createTrain(Action action, Graph graph) {

    if (action.actionType == ROUTE_DISTANCE) {
      return new Train(action.actionData, graph);
    } else if (action.actionType == SHORTEST_ROUTE) {
      char start = action.actionData.charAt(0);
      char end = action.actionData.charAt(1);
      return new Train(start, end, graph);
    } else {
      char start = action.actionData.charAt(0);
      char end = action.actionData.charAt(1);
      int limit = Integer.valueOf(action.actionData.substring(2));
      return new Train(start, end, graph, limit);
    }
  }

}
