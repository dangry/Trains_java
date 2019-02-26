package actions;

import models.Edge;
import models.Node;
import models.Train;

public class ShortestRouteDistance implements GraphAction {

  private int shortestDistance;
  private Train train;
  private String route;
  private int distanceTraveled;

  public int getShortestDistance(Train train, String route, int distanceTraveled) {

    if (shouldReturn(train, distanceTraveled, route)) {
      return shortestDistance(distanceTraveled);
    } else {
      route += train.currentNodeName;
    }

    Node currentNode = train.getCurrentNode();

    for (Edge edge : currentNode.getEdges().values()) {
      Character nextNodeName = edge.getDestination().getName();
      int distanceToNextNode = edge.getLength();
      train.currentNodeName = nextNodeName;
      getShortestDistance(train, route, distanceTraveled + distanceToNextNode);
    }

    return shortestDistance;
  }

  private int shortestDistance(int distanceTraveled) {
    if (shortestDistance > distanceTraveled) {
      shortestDistance = distanceTraveled;
    }

    return shortestDistance;
  }

  private boolean shouldReturn(Train train, int distanceTraveled, String route) {
    String currentNodeNameString = String.valueOf(train.currentNodeName);
    return endReached(train.currentNodeName, train.destinationNodeName, distanceTraveled)
        || route.contains(currentNodeNameString);
  }

  private Boolean endReached(char currentNodeName, char endNodeName, int distanceTraveled) {
    return currentNodeName == endNodeName && distanceTraveled > 0;
  }

  @Override
  public void setData(Train train) {
    this.train = train;
    route = "";
    distanceTraveled = 0;
    shortestDistance = Integer.MAX_VALUE;
  }

  @Override
  public int execute() {
    return getShortestDistance(train, route, distanceTraveled);
  }
}
