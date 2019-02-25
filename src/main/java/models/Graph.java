package models;

import java.util.HashMap;

public class Graph {
    public HashMap<Character, Node> nodes;

    public Graph() {
        this.nodes = new HashMap<>();
    }

    public void addNode(Node node) {
        nodes.put(node.getName(), node);
    }

    private boolean isNewNode(Node node) {
        return !nodes.containsKey(node.getName());
    }
}
