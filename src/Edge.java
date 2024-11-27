/**
 * The Edge class provides a representation of an edge within the graph,
 * which represents a road connecting two nodes with a specific travel time.
 * It will be used in our graph data structure and other components of the ride-sharing system.
 */
public class Edge {
    private Node source;
    private Node destination;
    private int travelTime;

    // Constructor to initialize the edge with a source node, destination node, and travel time
    public Edge(Node source, Node destination, int travelTime) {
        this.source = source;
        this.destination = destination;
        this.travelTime = travelTime;
    }

    // Getter for the source node
    public Node getSource() {
        return source;
    }

    // Getter for the destination node
    public Node getDestination() {
        return destination;
    }

    // Getter for the travel time
    public int getTravelTime() {
        return travelTime;
    }

    // Override the toString method to provide a readable representation of the edge
    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source +
                ", destination=" + destination +
                ", travelTime=" + travelTime +
                '}';
    }

    // Override the equals method to compare edges based on their source, destination, and travel time
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge edge = (Edge) obj;
        return travelTime == edge.travelTime &&
                source.equals(edge.source) &&
                destination.equals(edge.destination);
    }

    // Override the hashCode method to use the combined hash codes of the source, destination, and travel time
    @Override
    public int hashCode() {
        int result = source.hashCode();
        result = 31 * result + destination.hashCode();
        result = 31 * result + travelTime;
        return result;
    }
}