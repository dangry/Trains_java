package actions;

import models.Graph;
import models.Train;
import utils.exception.InvalidPathNameException;

public class RouteDistance implements GraphAction {

  private static final String ROUTE_DELIMITER = "-";
  private static final String NOTHING = "";

  private String route;
  private Graph graph;

  public int getRouteDistance() throws InvalidPathNameException {

    int routeDistance = 0;

    String routeNames = route.replace(ROUTE_DELIMITER, NOTHING);

    for (int i = 0; i < routeNames.length() - 1; i++) {

      routeDistance +=
          graph.nodes.get(routeNames.charAt(i)).getDistanceToNodeByName(routeNames.charAt(i + 1));
    }

    return routeDistance;
  }

  @Override
  public void setData(Train train) {
    this.route = train.route;
    this.graph = train.graph;
  }

  @Override
  public int execute() {
    int result;

    try {
      result = getRouteDistance();
    } catch (InvalidPathNameException exception) {
      result = -1;
    }
    return result;
  }
}
