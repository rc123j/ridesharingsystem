/**
 * The RideAssignment class represents an assignment of a passenger to a driver,
 * along with the corresponding travel time. With this class implemented, we can now
 * properly represent and manage ride assignments within the ride-sharing system.
 */
public class RideAssignment {
    private Passenger passenger;
    private Driver driver;
    Node initialLocation;
    Node currentLocation;
    private int travelTime;

    // Constructor to initialize the ride assignment with passenger, driver, and travel time
    public RideAssignment(Passenger passenger, Driver driver, Node initialLocation, Node currentLocation, int travelTime) {
        this.passenger = passenger;
        this.driver = driver;
        this.initialLocation = initialLocation;
        this.currentLocation = currentLocation;
        this.travelTime = travelTime;
    }

    // Override the toString method to provide a readable representation of the ride assignment
    @Override
    public String toString() {
        return  "Passenger: " + passenger.getId() + " (From: " + passenger.getCurrentLocation() + ", To: " + passenger.getDestination() + ")\n" +
                "Driver: " + driver.getId() + " (Location: " + initialLocation + ", Destination: " + currentLocation + ")\n" +
                "Travel Time: " + travelTime + " mins";
    }
}