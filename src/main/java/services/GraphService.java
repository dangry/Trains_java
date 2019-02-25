package services;

import models.Edge;
import models.Graph;
import models.Node;
import utils.Utils;
import utils.exception.InvalidPathNameException;

public class GraphService implements GraphServiceInterface {

  private static final String GRAPH_DELIMITER = ", ";

  private static void addRoute(Graph graph, String route) throws InvalidPathNameException {
    Node startNode = createNode(graph, route.charAt(0));
    Node endNode = createNode(graph, route.charAt(1));
    int length = Integer.valueOf(route.substring(2));
    Edge edge = new Edge(endNode, length);

    startNode.addEdge(edge);

    graph.addNode(startNode);
  }

  private static Node createNode(Graph graph, char nodeName) throws InvalidPathNameException {
    if (graph.nodes.containsKey(nodeName)) {
      return graph.nodes.get(nodeName);
    } else {
      return new Node(nodeName);
    }
  }

  @Override
  public Graph createGraphFromText(String graphText) throws InvalidPathNameException {
    Graph graph = new Graph();

    for (String route : graphText.split(GRAPH_DELIMITER)) {

      if (!isValidPath(route)) {
        throw new InvalidPathNameException("The proportioned path is not valid");
      }

      addRoute(graph, route);
    }

    return graph;
  }

  public static boolean isValidPath(String path) {

    if (!Character.isLetter((path.charAt(0)))) return false;
    else if (!Character.isLetter((path.charAt(1)))) return false;
    else if (!Utils.isNumber(path.substring(2))) return false;

    return true;
  }
}
