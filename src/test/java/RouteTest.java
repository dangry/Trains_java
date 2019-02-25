import graph_actions.ActionType;
import graph_actions.GraphAction;
import graph_actions.GraphActionFactory;
import models.Graph;
import models.Train;
import org.junit.Before;
import org.junit.Test;
import services.GraphService;
import services.GraphServiceInterface;
import utils.exception.InvalidPathNameException;

import static junit.framework.TestCase.assertEquals;

public class RouteTest {

    String testGraphText;
    GraphServiceInterface graphServiceInterface;
    GraphAction graphAction;

    @Before
    public void setTestData() throws Exception {
        graphServiceInterface = new GraphService();
        testGraphText = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
    }

    @Test
    public void shouldGetTheDistanceOfRouteABC() throws InvalidPathNameException {
        String route = "A-B-C";
        Graph graph = graphServiceInterface.createGraphFromText(testGraphText);
        Train train = new Train(route, graph);
        graphAction = GraphActionFactory.getAction(ActionType.ROUTE_DISTANCE);
        graphAction.setData(train);
        assertEquals(9, graphAction.execute());
    }

    @Test
    public void shouldGetTheDistanceOfRouteAD() throws InvalidPathNameException {
        String route = "A-D";
        Graph graph = graphServiceInterface.createGraphFromText(testGraphText);
        Train train = new Train(route, graph);
        graphAction = GraphActionFactory.getAction(ActionType.ROUTE_DISTANCE);
        graphAction.setData(train);
        assertEquals(5, graphAction.execute());
    }

    @Test
    public void shouldGetTheDistanceOfRouteADC() throws InvalidPathNameException {
        String route = "A-D-C";
        Graph graph = graphServiceInterface.createGraphFromText(testGraphText);
        Train train = new Train(route, graph);
        graphAction = GraphActionFactory.getAction(ActionType.ROUTE_DISTANCE);
        graphAction.setData(train);
        assertEquals(13, graphAction.execute());
    }

    @Test
    public void shouldGetTheDistanceOfRouteAEBCD() throws InvalidPathNameException {
        String route = "A-E-B-C-D";
        Graph graph = graphServiceInterface.createGraphFromText(testGraphText);
        Train train = new Train(route, graph);
        graphAction = GraphActionFactory.getAction(ActionType.ROUTE_DISTANCE);
        graphAction.setData(train);
        assertEquals(22, graphAction.execute());
    }

    @Test
    public void shouldGetNumberOfPathsBetweenNodeWithLimitDistanceForRouteCC() throws InvalidPathNameException {
        Graph graph = graphServiceInterface.createGraphFromText(testGraphText);
        Train train = new Train('C', 'C', graph, 30);
        GraphAction graphAction = GraphActionFactory.getAction(ActionType.DIFFERENT_ROUTES_MAX_DISTANCE);
        graphAction.setData(train);
        assertEquals(7, graphAction.execute());
    }

    @Test
    public void shouldGetNumberOfPathsBetweenNodesWithLimitDistanceForRouteEC() throws InvalidPathNameException {
        Graph graph = graphServiceInterface.createGraphFromText(testGraphText);
        Train train = new Train('E', 'C', graph, 30);
        GraphAction graphAction = GraphActionFactory.getAction(ActionType.DIFFERENT_ROUTES_MAX_DISTANCE);
        graphAction.setData(train);
        assertEquals(5, graphAction.execute());
    }

    @Test
    public void shouldGetTheNumberOfPathsBetweenNodesWithExactStops() throws InvalidPathNameException {
        Graph graph = graphServiceInterface.createGraphFromText(testGraphText);
        Train train = new Train('A', 'C', graph, 4);
        GraphAction graphAction = GraphActionFactory.getAction(ActionType.NUMBER_OF_TRIPS_EXACT_STOPS);
        graphAction.setData(train);
        assertEquals(3, graphAction.execute());
    }

    @Test
    public void shouldGetNumberOfPathsBetweenNodesWithLimitForRouteAB() throws InvalidPathNameException {
        Graph graph = graphServiceInterface.createGraphFromText(testGraphText);
        Train train = new Train('A', 'B', graph, 30);
        GraphAction graphAction = GraphActionFactory.getAction(ActionType.DIFFERENT_ROUTES_MAX_DISTANCE);
        graphAction.setData(train);
        assertEquals(11, graphAction.execute());
    }

    @Test
    public void shouldGetTheNumberOfPathsBetweenNodesWithMaximumStops() throws InvalidPathNameException {
        Graph graph = graphServiceInterface.createGraphFromText(testGraphText);
        Train train = new Train('C', 'C', graph, 3);
        GraphAction graphAction = GraphActionFactory.getAction(ActionType.NUMBER_OF_TRIPS_MAX_STOPS);
        graphAction.setData(train);
        assertEquals(2, graphAction.execute());
    }

    @Test
    public void getShortestDistanceBetweenTwoNodes() throws InvalidPathNameException {
        Graph graph = graphServiceInterface.createGraphFromText(testGraphText);
        Train train = new Train('A', 'C', graph);
        GraphAction graphAction = GraphActionFactory.getAction(ActionType.SHORTEST_ROUTE);
        graphAction.setData(train);
        assertEquals(9, graphAction.execute());
    }

    @Test
    public void getShortestDistanceBetweenTwoOtherNodes() throws InvalidPathNameException {
        Graph graph = graphServiceInterface.createGraphFromText(testGraphText);
        Train train = new Train('B', 'B', graph);
        graphAction = GraphActionFactory.getAction(ActionType.SHORTEST_ROUTE);
        graphAction.setData(train);
        assertEquals(9, graphAction.execute());
    }

}
