package services;

import models.Action;
import org.junit.Before;
import org.junit.Test;

import static actions.ActionType.*;
import static org.junit.Assert.assertEquals;

public class TextServiceTest {

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

  TextService textService;

  @Before
  public void setupTestData() {
    textService = new TextServiceImpl();
    textService.createGraphFromText("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
  }

  @Test
  public void shouldCheckIfDistanceInstructionIsValid() {
    assertEquals(ROUTE_DISTANCE, textService.getActionType(ROUTE_DISTANCE_STRING));
  }

  @Test
  public void shouldCheckIfNumberOfTripsMaxStopsInstructionIsValid() {
    assertEquals(
        NUMBER_OF_TRIPS_MAX_STOPS, textService.getActionType(NUMBER_OF_TRIPS_MAX_STOPS_STRING));
  }

  @Test
  public void shouldCheckIfNumberOfTripsExactStopsInstructionIsValid() {
    assertEquals(
        NUMBER_OF_TRIPS_EXACT_STOPS, textService.getActionType(NUMBER_OF_TRIPS_EXACT_STOPS_STRING));
  }

  @Test
  public void shouldCheckIfShortestRouteInstructionIsValid() {
    assertEquals(SHORTEST_ROUTE, textService.getActionType(SHORTEST_ROUTE_STRING));
  }

  @Test
  public void shouldCheckIfDifferentRoutesMaxDistanceInstructionIsValid() {
    assertEquals(
        DIFFERENT_ROUTES_MAX_DISTANCE,
        textService.getActionType(DIFFERENT_ROUTES_MAX_DISTANCE_STRING));
  }

  @Test
  public void shouldGetRouteDataWithRouteDistanceString() {
    assertEquals("A-B-C", textService.getActionData(ROUTE_DISTANCE_STRING));
  }

  @Test
  public void shouldGetRouteDataWithNumberOfTripsMaxStopString() {
    assertEquals("CC3", textService.getActionData(NUMBER_OF_TRIPS_MAX_STOPS_STRING));
  }

  @Test
  public void shouldGetRouteDataWithNumberOfTripsExactStopString() {
    assertEquals("AC4", textService.getActionData(NUMBER_OF_TRIPS_EXACT_STOPS_STRING));
  }

  @Test
  public void shouldGetRouteDataWithShortestRouteString() {
    assertEquals("AC", textService.getActionData(SHORTEST_ROUTE_STRING));
  }

  @Test
  public void shouldGetRouteDataWithDifferentRoutesMaxDistanceString() {
    assertEquals("CC30", textService.getActionData(DIFFERENT_ROUTES_MAX_DISTANCE_STRING));
  }


  @Test
  public void shouldReturnAnActionFromInstruction() {
    Action action = new Action(ROUTE_DISTANCE, "A-B-C");
    assertEquals(action, textService.getAction(ROUTE_DISTANCE_STRING));
  }

}
