package actions;

import models.Graph;
import models.Train;
import org.junit.Before;
import org.junit.Test;
import exceptions.InvalidPathNameException;

import static junit.framework.TestCase.assertEquals;

public class GraphActionsTest {

  Graph graph;
  GraphAction graphAction;

  @Before
  public void setTestData() throws InvalidPathNameException {
    String testGraphText = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
    graph = Graph.createGraphFromText(testGraphText);
  }

  @Test
  public void shouldGetTheDistanceOfRouteABC() {
    String route = "A-B-C";
    Train train = new Train(route, graph);
    graphAction = GraphActionFactory.getAction(ActionType.ROUTE_DISTANCE);
    graphAction.setData(train);
    assertEquals(9, graphAction.execute());
  }

  @Test
  public void shouldGetTheDistanceOfRouteAD() {
    String route = "A-D";
    Train train = new Train(route, graph);
    graphAction = GraphActionFactory.getAction(ActionType.ROUTE_DISTANCE);
    graphAction.setData(train);
    assertEquals(5, graphAction.execute());
  }

  @Test
  public void shouldGetTheDistanceOfRouteADC() {
    String route = "A-D-C";
    Train train = new Train(route, graph);
    graphAction = GraphActionFactory.getAction(ActionType.ROUTE_DISTANCE);
    graphAction.setData(train);
    assertEquals(13, graphAction.execute());
  }

  @Test
  public void shouldGetTheDistanceOfRouteAEBCD() {
    String route = "A-E-B-C-D";
    Train train = new Train(route, graph);
    graphAction = GraphActionFactory.getAction(ActionType.ROUTE_DISTANCE);
    graphAction.setData(train);
    assertEquals(22, graphAction.execute());
  }

  @Test
  public void shouldGetNumberOfPathsBetweenNodeWithLimitDistanceForRouteCC() {
    Train train = new Train('C', 'C', graph, 30);
    GraphAction graphAction =
        GraphActionFactory.getAction(ActionType.DIFFERENT_ROUTES_MAX_DISTANCE);
    graphAction.setData(train);
    assertEquals(7, graphAction.execute());
  }

  @Test
  public void shouldGetNumberOfPathsBetweenNodesWithLimitDistanceForRouteEC() {
    Train train = new Train('E', 'C', graph, 30);
    GraphAction graphAction =
        GraphActionFactory.getAction(ActionType.DIFFERENT_ROUTES_MAX_DISTANCE);
    graphAction.setData(train);
    assertEquals(5, graphAction.execute());
  }

  @Test
  public void shouldGetTheNumberOfPathsBetweenNodesWithExactStops() {
    Train train = new Train('A', 'C', graph, 4);
    GraphAction graphAction = GraphActionFactory.getAction(ActionType.NUMBER_OF_TRIPS_EXACT_STOPS);
    graphAction.setData(train);
    assertEquals(3, graphAction.execute());
  }

  @Test
  public void shouldGetNumberOfPathsBetweenNodesWithLimitForRouteAB() {
    Train train = new Train('A', 'B', graph, 30);
    GraphAction graphAction =
        GraphActionFactory.getAction(ActionType.DIFFERENT_ROUTES_MAX_DISTANCE);
    graphAction.setData(train);
    assertEquals(11, graphAction.execute());
  }

  @Test
  public void shouldGetTheNumberOfPathsBetweenNodesWithMaximumStops() {
    Train train = new Train('C', 'C', graph, 3);
    GraphAction graphAction = GraphActionFactory.getAction(ActionType.NUMBER_OF_TRIPS_MAX_STOPS);
    graphAction.setData(train);
    assertEquals(2, graphAction.execute());
  }

  @Test
  public void getShortestDistanceBetweenTwoNodes() {
    Train train = new Train('A', 'C', graph);
    GraphAction graphAction = GraphActionFactory.getAction(ActionType.SHORTEST_ROUTE);
    graphAction.setData(train);
    assertEquals(9, graphAction.execute());
  }

  @Test
  public void getShortestDistanceBetweenTwoOtherNodes() {
    Train train = new Train('B', 'B', graph);
    graphAction = GraphActionFactory.getAction(ActionType.SHORTEST_ROUTE);
    graphAction.setData(train);
    assertEquals(9, graphAction.execute());
  }
}
