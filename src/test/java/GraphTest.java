import models.Edge;
import models.Graph;
import models.Node;
import models.Train;
import org.junit.Before;
import org.junit.Test;
import services.GraphService;
import utils.exception.InvalidPathNameException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class GraphTest {

    String testGraphText;
    GraphService graphServiceInterface;

    @Before
    public void setTestData() {
        graphServiceInterface = new GraphService();
        testGraphText = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
    }

    @Test
    public void shouldCreateANodeWithEdges() throws Exception {
        Edge edge = new Edge(new Node('A'), 1);
        Node node = new Node();
        try {
            node.addEdge(edge);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(node.getEdges());
    }

    @Test
    public void shouldCreateAnEdgeWithDestinationAndLength() {
        Node mockedNode = mock(Node.class);
        Edge edge = new Edge(mockedNode, 1);
        edge.setLength(1);

        assertNotNull(edge);
        assertEquals(mockedNode, edge.getDestination());
        assertEquals(1, edge.getLength());
    }

    @Test
    public void shouldCreateAGraphWithNodesFromText() throws Exception {
        Graph graph = graphServiceInterface.createGraphFromText(testGraphText);
        assertEquals(3, graph.nodes.get('A').getEdges().size());
        assertEquals(5, graph.nodes.size());
    }

    @Test(expected = InvalidPathNameException.class)
    public void shouldNotParseInvalidPaths() throws InvalidPathNameException {
        String invalidPaths = "AB5, INVALID42, CD812F, DC8, DE6, AD5, CE2, EB3, AE7AAA111";
        graphServiceInterface.createGraphFromText(invalidPaths);
    }

    @Test
    public void shouldCreateAPointer() {
        Train pointer = new Train('A', 'B', new Graph());
        assertNotNull(pointer);
    }

}
