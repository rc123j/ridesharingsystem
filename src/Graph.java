/**
 * The Graph class provides a comprehensive representation of the road network as a graph
 * and allows for basic operations like adding nodes and edges, retrieving neighbors,
 * and getting travel times.
 */

import java.util.*;

public class Graph {
    private Map<String, Node> nodes;  // Maps node id to Node object
    private Map<Node, List<Edge>> adjacencyList;  // Maps node to list of outgoing edges

    // Constructor to initialize the graph
    public Graph() {
        nodes = new HashMap<>();
        adjacencyList = new HashMap<>();
    }

    // Method to add a node to the graph
    public void addNode(Node node) {
        nodes.put(node.getId(), node);
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    /**
     * Adds an edge to the graph, creating a bidirectional connection between the source and destination nodes.
     * @param edge The edge to add to the graph.
     */    public void addEdge(Edge edge) {
        adjacencyList.get(edge.getSource()).add(edge);

        // Add a second edge in the opposite direction to make the graph bidirectional
        Node destination = edge.getDestination();
        adjacencyList.get(destination).add(new Edge(destination, edge.getSource(), edge.getTravelTime()));
    }

    // Method to get the neighbors of a node
    public List<Edge> getNeighbors(Node node) {
        return adjacencyList.get(node);
    }

    // Method to get the travel time between two nodes using Dijkstra's algorithm
    public int getTravelTime(Node source, Node destination) {
        // Use Dijkstra's algorithm to find the shortest path from the source to the destination
        Path shortestPath = DijkstraAlgorithm.findShortestPath(this, source, destination);

        // Retrieve the travel time from the shortest path
        int travelTime = shortestPath.getTotalTravelTime();

        // Return the travel time if a path exists, otherwise return a high value
        return travelTime != Integer.MAX_VALUE ? travelTime : Integer.MAX_VALUE;
    }

    // Method to get a node by its id
    public Node getNode(String id) {
        return nodes.get(id);
    }

    // Method to get all nodes in the graph
    public Collection<Node> getNodes() {
        return nodes.values();
    }

    // Method to display the graph
    public void displayGraph() {
        for (Node node : adjacencyList.keySet()) {
            System.out.print(node + " -> ");
            List<Edge> edges = adjacencyList.get(node);
            for (Edge edge : edges) {
                System.out.print(edge.getDestination() + " (" + edge.getTravelTime() + " mins), ");
            }
            System.out.println();
        }
    }
}