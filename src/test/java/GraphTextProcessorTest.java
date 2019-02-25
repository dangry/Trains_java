import graph_actions.ActionType;
import models.Graph;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class GraphTextProcessorTest {

  private static final String ROUTE_DISTANCE_STRING = "The distance of the route A-B-C";
  private static final String INVALID_ROUTE_DISTANCE_STRING = "The distance of the route A-E-D";
  private static final String NUMBER_OF_TRIPS_MAX_STOPS_STRING =
      "The number of trips starting at C and ending at C with a maximum of 3 stops.";
  private static final String NUMBER_OF_TRIPS_EXACT_STOPS_STRING =
      "The number of trips starting at A and ending at C with exactly 4 stops.";
  private static final String SHORTEST_ROUTE_STRING =
      "The length of the shortest route (in terms of distance to travel) from A to C.";
  private static final String DIFFERENT_ROUTES_MAX_DISTANCE_STRING =
      "The number of different routes from C to C with a distance of less than 30.  In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.";

  String fileName;
  GraphTextProcessor textProcessor;

  @Before
  public void setupTestData() throws FileNotFoundException {
    fileName = "src/test/resources/testFile.txt";
    textProcessor = new GraphTextProcessor(fileName);
  }

  @Test
  public void shouldCreateDataFromTextFile() {
    assertNotNull(textProcessor.getFileLines());
  }

  @Test
  public void shouldReadDataFromTextFile() {
    assertEquals(11, textProcessor.getFileLines().size());
  }

  @Test
  public void shouldCreateAGraphFromTheFirstLine() {
    Graph graph = new Graph();
    assertThat(graph, instanceOf(textProcessor.getGraph().getClass()));
  }

  @Test
  public void shouldCheckIfDistanceInstructionIsValid() {
    assertEquals(ActionType.ROUTE_DISTANCE, textProcessor.getActionType(ROUTE_DISTANCE_STRING));
  }

  @Test
  public void shouldCheckIfNumberOfTripsMaxStopsInstructionIsValid() {
    assertEquals(
        ActionType.NUMBER_OF_TRIPS_MAX_STOPS,
        textProcessor.getActionType(NUMBER_OF_TRIPS_MAX_STOPS_STRING));
  }

  @Test
  public void shouldCheckIfNumberOfTripsExactStopsInstructionIsValid() {
    assertEquals(
        ActionType.NUMBER_OF_TRIPS_EXACT_STOPS,
        textProcessor.getActionType(NUMBER_OF_TRIPS_EXACT_STOPS_STRING));
  }

  @Test
  public void shouldCheckIfShortestRouteInstructionIsValid() {
    assertEquals(ActionType.SHORTEST_ROUTE, textProcessor.getActionType(SHORTEST_ROUTE_STRING));
  }

  @Test
  public void shouldCheckIfDifferentRoutesMaxDistanceInstructionIsValid() {
    assertEquals(
        ActionType.DIFFERENT_ROUTES_MAX_DISTANCE,
        textProcessor.getActionType(DIFFERENT_ROUTES_MAX_DISTANCE_STRING));
  }

  @Test
  public void shouldGetRouteDataWithRouteDistanceString() {
    assertEquals("A-B-C", textProcessor.getActionData(ROUTE_DISTANCE_STRING));
  }

  @Test
  public void shouldGetRouteDataWithNumberOfTripsMaxStopString() {
    assertEquals("CC3", textProcessor.getActionData(NUMBER_OF_TRIPS_MAX_STOPS_STRING));
  }

  @Test
  public void shouldGetRouteDataWithNumberOfTripsExactStopString() {
    assertEquals("AC4", textProcessor.getActionData(NUMBER_OF_TRIPS_EXACT_STOPS_STRING));
  }

  @Test
  public void shouldGetRouteDataWithShortestRouteString() {
    assertEquals("AC", textProcessor.getActionData(SHORTEST_ROUTE_STRING));
  }

  @Test
  public void shouldGetRouteDataWithDifferentRoutesMaxDistanceString() {
    assertEquals("CC30", textProcessor.getActionData(DIFFERENT_ROUTES_MAX_DISTANCE_STRING));
  }

  @Test
  public void shouldCreateTrainWithTextData() {
    assertEquals(
        'C',
        textProcessor.createTrainFromData("CC30", ActionType.DIFFERENT_ROUTES_MAX_DISTANCE)
            .currentNodeName);
  }

  @Test
  public void shouldDoActionWithText() {
    assertEquals("9", textProcessor.doAction(ROUTE_DISTANCE_STRING));
  }

  @Test
  public void shouldDoActionWithText1() {
    assertEquals("NO SUCH ROUTE", textProcessor.doAction(INVALID_ROUTE_DISTANCE_STRING));
  }

  @Test
  public void shouldDoActionWithText2() {
    assertEquals("2", textProcessor.doAction(NUMBER_OF_TRIPS_MAX_STOPS_STRING));
  }

  @Test
  public void shouldDoActionWithText3() {
    assertEquals("3", textProcessor.doAction(NUMBER_OF_TRIPS_EXACT_STOPS_STRING));
  }

  @Test
  public void shouldDoActionWithText4() {
    assertEquals("9", textProcessor.doAction(SHORTEST_ROUTE_STRING));
  }

  @Test
  public void shouldDoActionWithText5() {
    assertEquals("7", textProcessor.doAction(DIFFERENT_ROUTES_MAX_DISTANCE_STRING));
  }

  @Test
  public void processText() {
    assertEquals("9-5-13-22-NO SUCH ROUTE-2-3-9-9-7-", textProcessor.processFile());
  }
}
