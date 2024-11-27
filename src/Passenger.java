/**
 * The Passenger class represents a passenger in the ride-sharing system. Passengers are identified by
 * unique identifiers and have current location and destination within the road network,
 * similar to drivers. This class provides a convenient way to manage passenger information,
 * facilitating the assignment of passengers to drivers and tracking their locations during rides.
 */
public class Passenger {
    private String id;
    private Node currentLocation;
    private Node destination;
    private boolean dropped; // Indicates if the passenger has been dropped off

    // Constructor to initialize the passenger with an id, current location, and destination
    public Passenger(String id, Node currentLocation, Node destination) {
        this.id = id;
        this.currentLocation = currentLocation;
        this.destination = destination;
        this.dropped = false; // Initially set to false
    }

    // Getter for the id
    public String getId() {
        return id;
    }

    // Getter for the current location
    public Node getCurrentLocation() {
        return currentLocation;
    }

    // Getter for the destination
    public Node getDestination() {
        return destination;
    }

    public boolean isDropped() {
        return dropped;
    }

    // Setter method to update the dropped status
    public void setDropped(boolean dropped) {
        this.dropped = dropped;
    }

    // Override the toString method to provide a readable representation of the passenger
    @Override
    public String toString() {
        return "Passenger{" +
                "id='" + id + '\'' +
                ", currentLocation=" + currentLocation +
                ", destination=" + destination +
                '}';
    }

    // Override the equals method to compare passengers based on their id
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Passenger passenger = (Passenger) obj;
        return id.equals(passenger.id);
    }

    // Override the hashCode method to use the id's hash code
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}