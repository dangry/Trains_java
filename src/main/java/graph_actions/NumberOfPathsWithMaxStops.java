package graph_actions;

import models.Edge;
import models.Node;
import models.Train;

import java.util.HashSet;
import java.util.Set;

public class NumberOfPathsWithMaxStops implements GraphAction {

  private int maxStops;
  private Train train;

  private Set<String> routesSeen;
  private int stopsCount;
  private String route;
  private int distanceTraveled;

  public NumberOfPathsWithMaxStops() {}

  public int getNumberOfPathsWithLimitStops(
      Train train, int stopsCount, String route, int distanceTraveled) {

    route += train.currentNodeName;

    if (route.length() - 1 > maxStops) {
      return stopsCount;
    }

    if (isTheEndOfTheRoute(route, train.destinationNodeName)) {

      if (route.length() - 1 <= maxStops) {
        stopsCount++;
        routesSeen.add(route);
      }
    }

    Node currentNode = train.getCurrentNode();

    for (Edge edge : currentNode.getEdges().values()) {

      Character nextNodeName = edge.getDestination().getName();
      train.currentNodeName = nextNodeName;
      stopsCount =
          getNumberOfPathsWithLimitStops(
              train, stopsCount, route, distanceTraveled + edge.getLength());
    }

    return stopsCount;
  }

  public Boolean isTheEndOfTheRoute(String route, char endNodeName) {
    return !routesSeen.contains(route)
        && route.endsWith(String.valueOf(endNodeName))
        && route.length() > 1;
  }

  @Override
  public void setData(Train train) {
    this.maxStops = train.limit;
    this.train = train;
    routesSeen = new HashSet<>();
    stopsCount = 0;
    route = "";
    distanceTraveled = 0;
  }

  @Override
  public int execute() {
    return getNumberOfPathsWithLimitStops(train, stopsCount, route, distanceTraveled);
  }
}
