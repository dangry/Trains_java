import models.Edge;
import models.Node;
import org.junit.Before;
import org.junit.Test;
import utils.exception.InvalidPathNameException;

import static junit.framework.TestCase.assertEquals;

public class NodeTest {

    Node nodeA;
    Node nodeB;

    @Before
    public void setTestData() {
        nodeA = new Node('A');
        nodeB = new Node('B');
        Edge testEdge = new Edge(nodeB, 5);
        nodeA.addEdge(testEdge);
    }

    @Test
    public void shouldGetNodesDistance() throws InvalidPathNameException {
        assertEquals(5, nodeA.getDistanceToNodeByName('B'));
    }

    @Test
    public void getAdjacentNodeByName() {
        assertEquals(nodeB, nodeA.getAdjacentNodeByName('B'));
    }

    // TODO:
//    @Test(expected = Exception.class)
//    public void nodeEdgesShouldNotHaveTheSameNodeName() throws Exception {
//        nodeA.addEdge(new Edge(nodeA, 1));
//    }


    // TODO: Need some kind of factory.
//    @Test(expected = Exception.class)
//    public void nodesShouldBeUniqueByName() throws Exception {
//        new Node('A');
//    }
//
//    // TODO:
//    @Test(expected = Exception.class)
//    public void nodeShouldHaveValidNames() throws Exception {
//        new Node('F');
//    }

}
