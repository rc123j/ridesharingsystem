/**
 * The Node class provides a foundational representation of a node within the graph,
 * which represent a location in the road network,
 * to use in our graph data structure and other components of the ride-sharing system.
 */
public class Node {
    private String id;
    private String name;

    // Constructor to initialize the node with an id and an optional name
    public Node(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter for the id
    public String getId() {
        return id;
    }

    // Override the toString method to provide a readable representation of the node,
    // displaying the name if available, otherwise the id
    @Override
    public String toString() {
        return name != null ? name : id;
    }

    // Override the equals method to compare nodes based on their id
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return id.equals(node.id);
    }

    // Override the hashCode method to use the id's hash code
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}