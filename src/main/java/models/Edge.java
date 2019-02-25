package models;

public class Edge {

    private Node destination;
    private int length;

    public Edge(Node node, int length) {
        this.destination = node;
        this.length = length;
    }

    public Node getDestination() {
        return destination;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
