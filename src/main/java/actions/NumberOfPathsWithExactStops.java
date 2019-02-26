package actions;

import models.Edge;
import models.Node;
import models.Train;

import java.util.HashSet;
import java.util.Set;

public class NumberOfPathsWithExactStops implements GraphAction {

  private int exactStops;
  private Train train;
  private Set<String> routesSeen;
  private int stopsCount;
  private String route;

  public int getNumberOfPathsWithExactStops(Train train, int stopsCount, String route) {

    int distanceFromSource = route.length();
    route += train.currentNodeName;

    if (distanceFromSource > exactStops) {
      return routesSeen.size();
    }

    if (isTheEndOfTheRoute(route, train.destinationNodeName)) {
      if (distanceFromSource == exactStops) {
        routesSeen.add(route);
      }
    }

    Node currentNode = train.getCurrentNode();

    for (Edge edge : currentNode.getEdges().values()) {
      Character nextNodeName = edge.getDestination().getName();
      train.currentNodeName = nextNodeName;
      stopsCount = getNumberOfPathsWithExactStops(train, stopsCount, route);
    }

    return stopsCount;
  }

  private Boolean isTheEndOfTheRoute(String route, char endNodeName) {
    return !routesSeen.contains(route)
        && route.endsWith(String.valueOf(endNodeName))
        && route.length() > 1;
  }

  @Override
  public void setData(Train train) {
    this.exactStops = train.limit;
    this.train = train;
    routesSeen = new HashSet<>();
    stopsCount = 0;
    route = "";
  }

  @Override
  public int execute() {
    return getNumberOfPathsWithExactStops(train, stopsCount, route);
  }
}
