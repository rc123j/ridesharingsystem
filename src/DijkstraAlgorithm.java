/**
 * The DijkstraAlgorithm class implements Dijkstra's algorithm for finding the shortest path between
 * two nodes in a graph. Utilizing a priority queue and dynamic programming techniques, it efficiently
 * explores the graph's nodes, updating the shortest distances from the source node and previous nodes
 * in the shortest path. This class encapsulates the functionality required to compute optimal routes
 * within a transportation network, facilitating efficient navigation and resource allocation in
 * various applications such as ride-sharing systems and route planning tools.
 */
import java.util.*;

public class DijkstraAlgorithm {
    // Method to find the shortest path between two nodes using Dijkstra's algorithm
    public static Path findShortestPath(Graph graph, Node source, Node destination) {
        Map<Node, Integer> distances = new HashMap<>();  // Map to store the shortest distances from the source node
        Map<Node, Node> previousNodes = new HashMap<>();  // Map to store the previous node in the shortest path

        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(distances::get));  // Priority queue to select the node with minimum distance
        Set<Node> visited = new HashSet<>();  // Set to keep track of visited nodes

        // Initialization
        for (Node node : graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
            previousNodes.put(node, null);
        }
        distances.put(source, 0);
        minHeap.add(source);

        // Dijkstra's algorithm
        while (!minHeap.isEmpty()) {
            Node current = minHeap.poll();
            if (current.equals(destination)) {
                break;  // Found the shortest path to destination, exit loop
            }
            visited.add(current);

            for (Edge neighbor : graph.getNeighbors(current)) {
                if (visited.contains(neighbor.getDestination())) {
                    continue;  // Skip visited nodes
                }

                int newDistance = distances.get(current) + neighbor.getTravelTime();
                if (newDistance < distances.get(neighbor.getDestination())) {
                    distances.put(neighbor.getDestination(), newDistance);
                    previousNodes.put(neighbor.getDestination(), current);
                    minHeap.add(neighbor.getDestination());
                }
            }
        }

        // Construct the shortest path from source to destination
        Path shortestPath = new Path();
        Node current = destination;
        while (current != null) {
            shortestPath.addNode(current);
            current = previousNodes.get(current);
        }

        Collections.reverse(shortestPath.getNodes());  // Reverse the path to get it in correct order
        shortestPath.setTotalTravelTime(distances.get(destination));

        return shortestPath;
    }
}