package graph_actions;

import java.util.HashMap;
import java.util.Map;

public class GraphActionFactory {

    private static final Map<ActionType, GraphAction> ACTIONS = new HashMap<ActionType, GraphAction>() {{
        put(ActionType.ROUTE_DISTANCE, new RouteDistance());
        put(ActionType.NUMBER_OF_TRIPS_MAX_STOPS, new NumberOfPathsWithMaxStops());
        put(ActionType.NUMBER_OF_TRIPS_EXACT_STOPS, new NumberOfPathsWithExactStops());
        put(ActionType.SHORTEST_ROUTE, new ShortestRouteDistance());
        put(ActionType.DIFFERENT_ROUTES_MAX_DISTANCE, new NumberOfPathsWithLimitDistance());
    }};

    public static GraphAction getAction(ActionType type) {
        GraphAction action = ACTIONS.get(type);
        if (action == null) {
            throw new IllegalArgumentException("No such action type");
        }
        return action;
    }

}