/**
 * The Driver class represents a driver in the ride-sharing system.
 * Each driver has a unique identifier and a current location within the road network.
 * This class encapsulates the information related to drivers,
 * allowing for easy management of their locations and identification within the system.
 */
public class Driver {
    private String id;
    private Node currentLocation;
    private Node previousLocation;

    // Constructor to initialize the driver with an id and current location
    public Driver(String id, Node currentLocation) {
        this.id = id;
        this.currentLocation = currentLocation;
        this.previousLocation = currentLocation;
    }

    // Getter for the id
    public String getId() {
        return id;
    }

    // Getter for the current location
    public Node getCurrentLocation() {
        return currentLocation;
    }

    // Setter for the current location
    public void setCurrentLocation(Node currentLocation) {
        this.currentLocation = currentLocation;
        this.previousLocation = this.currentLocation;
    }

    // Override the toString method to provide a readable representation of the driver
    @Override
    public String toString() {
        return "Driver{" +
                "id='" + id + '\'' +
                ", currentLocation=" + currentLocation +
                '}';
    }

    // Override the equals method to compare drivers based on their id
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Driver driver = (Driver) obj;
        return id.equals(driver.id);
    }

    // Override the hashCode method to use the id's hash code
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}