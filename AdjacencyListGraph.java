import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Adjacency-list graph with integer nodes in [0, numNodes-1].
 * Supports directed and undirected modes.
 */
public class AdjacencyListGraph<L> {

    public static final class Edge<L> {
        private final int firstNode;
        private final int secondNode;
        private final L label;

        public Edge(int firstNode, int secondNode, L label) {
            this.firstNode = firstNode;
            this.secondNode = secondNode;
            this.label = label;
        }

        public L label() {
            return label;
        }

        public int firstNode() {
            return firstNode;
        }

        public int secondNode() {
            return secondNode;
        }

        public int oppositeNode(int node) {
            if (node == firstNode) {
                return secondNode;
            }
            if (node == secondNode) {
                return firstNode;
            }
            throw new IllegalArgumentException("Node is not an endpoint of this edge.");
        }
    }

    private final int numNodes;
    private final boolean directed;
    private final List<Map<Integer, L>> outAdj;
    private final List<Map<Integer, L>> inAdj;
    private int numEdges;

    public AdjacencyListGraph(int numNodes, boolean directed) {
        if (numNodes <= 0) {
            throw new IllegalArgumentException("numNodes must be positive.");
        }
        this.numNodes = numNodes;
        this.directed = directed;
        this.outAdj = new ArrayList<>(numNodes);
        this.inAdj = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++) {
            outAdj.add(new LinkedHashMap<>());
            inAdj.add(new LinkedHashMap<>());
        }
        this.numEdges = 0;
    }

    /**
     * Builds a graph from an adjacency list. All inserted edge labels are null.
     * For undirected graphs, each undirected edge should ideally appear once
     * (duplicates are safely ignored).
     */
    public AdjacencyListGraph(List<Integer>[] adjacencyList, boolean directed) {
        this(adjacencyList.length, directed);
        for (int u = 0; u < adjacencyList.length; u++) {
            if (adjacencyList[u] == null) {
                continue;
            }
            for (int v : adjacencyList[u]) {
                addEdge(u, v, null);
            }
        }
    }

    public int numNodes() {
        return numNodes;
    }

    public Iterable<Integer> nodes() {
        List<Integer> nodes = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++) {
            nodes.add(i);
        }
        return nodes;
    }

    public int numEdges() {
        return numEdges;
    }

    public Iterable<Edge<L>> edges() {
        List<Edge<L>> allEdges = new ArrayList<>();
        if (directed) {
            for (int u = 0; u < numNodes; u++) {
                for (Map.Entry<Integer, L> e : outAdj.get(u).entrySet()) {
                    allEdges.add(new Edge<>(u, e.getKey(), e.getValue()));
                }
            }
            return allEdges;
        }

        for (int u = 0; u < numNodes; u++) {
            for (Map.Entry<Integer, L> e : outAdj.get(u).entrySet()) {
                int v = e.getKey();
                if (u <= v) {
                    allEdges.add(new Edge<>(u, v, e.getValue()));
                }
            }
        }
        return allEdges;
    }

    public int aNode() {
        return 0;
    }

    public void addEdge(int node1, int node2, L label) {
        validateNode(node1);
        validateNode(node2);

        if (directed) {
            if (outAdj.get(node1).containsKey(node2)) {
                return;
            }
            outAdj.get(node1).put(node2, label);
            inAdj.get(node2).put(node1, label);
            numEdges++;
            return;
        }

        if (outAdj.get(node1).containsKey(node2)) {
            return;
        }

        outAdj.get(node1).put(node2, label);
        outAdj.get(node2).put(node1, label);
        inAdj.get(node1).put(node2, label);
        inAdj.get(node2).put(node1, label);
        numEdges++;
    }

    public boolean edgeExists(int node1, int node2) {
        validateNode(node1);
        validateNode(node2);
        return outAdj.get(node1).containsKey(node2);
    }

    // Undirected-style methods
    public int degree(int node) {
        validateNode(node);
        return outAdj.get(node).size();
    }

    public Iterable<Integer> adjacentNodes(int node) {
        validateNode(node);
        return Collections.unmodifiableSet(outAdj.get(node).keySet());
    }

    public Iterable<Edge<L>> incidentEdges(int node) {
        validateNode(node);
        List<Edge<L>> result = new ArrayList<>();
        for (Map.Entry<Integer, L> e : outAdj.get(node).entrySet()) {
            result.add(new Edge<>(node, e.getKey(), e.getValue()));
        }
        return result;
    }

    // Directed-style methods
    public int inDegree(int node) {
        validateNode(node);
        return inAdj.get(node).size();
    }

    public int outDegree(int node) {
        validateNode(node);
        return outAdj.get(node).size();
    }

    public Iterable<Integer> inAdjacentNodes(int node) {
        validateNode(node);
        return Collections.unmodifiableSet(inAdj.get(node).keySet());
    }

    public Iterable<Integer> outAdjacentNodes(int node) {
        validateNode(node);
        return Collections.unmodifiableSet(outAdj.get(node).keySet());
    }

    public Iterable<Edge<L>> inIncidentEdges(int node) {
        validateNode(node);
        List<Edge<L>> result = new ArrayList<>();
        for (Map.Entry<Integer, L> e : inAdj.get(node).entrySet()) {
            result.add(new Edge<>(e.getKey(), node, e.getValue()));
        }
        return result;
    }

    public Iterable<Edge<L>> outIncidentEdges(int node) {
        validateNode(node);
        List<Edge<L>> result = new ArrayList<>();
        for (Map.Entry<Integer, L> e : outAdj.get(node).entrySet()) {
            result.add(new Edge<>(node, e.getKey(), e.getValue()));
        }
        return result;
    }

    public boolean isDirected() {
        return directed;
    }

    private void validateNode(int node) {
        if (node < 0 || node >= numNodes) {
            throw new IllegalArgumentException("Invalid node: " + node);
        }
    }
}
