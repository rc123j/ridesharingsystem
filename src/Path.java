/**
 * The Path class represents a path between two nodes in a graph, containing information about the
 * sequence of nodes and possibly other attributes such as the total travel time. This class encapsulates
 * the information about a specific route or journey within the graph, allowing for easy storage and
 * retrieval of path-related data. It provides methods to manipulate the sequence of nodes and to retrieve
 * information about the path, facilitating the implementation of graph algorithms such as
 * Dijkstra's algorithm for finding the shortest paths.
 */

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<Node> nodes;
    private int totalTravelTime;

    // Constructor to initialise the nodes list and sets the totalTravelTime to 0
    public Path() {
        this.nodes = new ArrayList<>();
        this.totalTravelTime = 0;
    }

    // Method to return the list of nodes in the path
    public List<Node> getNodes() {
        return nodes;
    }

    // Method to add a node to the path
    public void addNode(Node node) {
        nodes.add(node);
    }

    // Method to return the total travel time along the path
    public int getTotalTravelTime() {
        return totalTravelTime;
    }

    // Method to set the total travel time along the path
    public void setTotalTravelTime(int totalTravelTime) {
        this.totalTravelTime = totalTravelTime;
    }

    // Override the toString() method to provide a readable representation of the path,
    // showing the sequence of nodes and the total travel time
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nodes.size(); i++) {
            sb.append(nodes.get(i));
            if (i < nodes.size() - 1) {
                sb.append(" -> ");
            }
        }
        sb.append(" (Total Travel Time: ").append(totalTravelTime).append(" mins)");
        return sb.toString();
    }
}