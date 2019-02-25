package graph_actions;

import models.Edge;
import models.Node;
import models.Train;

import java.util.HashSet;
import java.util.Set;

public class NumberOfPathsWithLimitDistance implements GraphAction {

  private int limit;
  private Train train;
  private Set<String> routesSeen;
  private int distanceTraveled;
  private int pathsCount;
  private String route;

  public NumberOfPathsWithLimitDistance() {}

  public int getNumberOfPathsWithLimitDistance(
      Train train, int pathsCount, String route, int distanceTraveled) {

    route += train.currentNodeName;

    if (distanceTraveled > limit) {
      return pathsCount;
    }

    if (isTheEndOfTheRoute(route, train.destinationNodeName)) {

      if (distanceTraveled < limit) {
        pathsCount++;
        routesSeen.add(route);
      } else {
        return pathsCount;
      }
    }

    Node currentNode = train.getCurrentNode();

    for (Edge edge : currentNode.getEdges().values()) {

      Character nextNodeName = edge.getDestination().getName();
      train.currentNodeName = nextNodeName;
      pathsCount =
          getNumberOfPathsWithLimitDistance(
              train, pathsCount, route, distanceTraveled + edge.getLength());
    }

    return pathsCount;
  }

  private Boolean isTheEndOfTheRoute(String route, char endNodeName) {
    return !routesSeen.contains(route)
        && route.endsWith(String.valueOf(endNodeName))
        && route.length() > 1;
  }

  @Override
  public void setData(Train train) {
    this.limit = train.limit;
    this.train = train;
    routesSeen = new HashSet<>();
    distanceTraveled = 0;
    pathsCount = 0;
    route = "";
  }

  @Override
  public int execute() {
    return getNumberOfPathsWithLimitDistance(train, pathsCount, route, distanceTraveled);
  }
}
