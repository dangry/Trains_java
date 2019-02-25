package models;

public class Train {

  public char currentNodeName;
  public char destinationNodeName;
  public int limit;
  public String route;

  public Graph graph;

  public Train(char currentNodeName, char destinationNodeName, Graph graph, int limit) {
    this.currentNodeName = currentNodeName;
    this.destinationNodeName = destinationNodeName;
    this.limit = limit;
    this.graph = graph;
  }

  public Train(String route, Graph graph) {
    this.route = route;
    this.graph = graph;
  }

  public Train(char currentNodeName, char destinationNodeName, Graph graph) {
    this.currentNodeName = currentNodeName;
    this.destinationNodeName = destinationNodeName;
    this.graph = graph;
  }

  public Node getCurrentNode() {
    return graph.nodes.get(currentNodeName);
  }
}
