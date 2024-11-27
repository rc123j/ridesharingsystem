/**
 * The RideAssignmentOptimizer class optimizes ride assignments in a ride-sharing system,
 * ensuring efficient allocation of passengers to drivers based on the shortest paths
 * and other relevant criteria within the road network graph.
 * It provides a static method optimizeAssignments() that takes lists of drivers, passengers,
 * and a Graph object representing the road network as input. It iterates through each passenger,
 * finds the closest driver using Dijkstra's algorithm for shortest paths, and creates a
 * RideAssignment object for each optimized assignment. Finally, it returns a list of optimized
 * ride assignments.
 */
import java.util.*;

public class RideAssignmentOptimizer {
    // Method to optimize ride assignments based on shortest paths and other relevant criteria
    public static List<RideAssignment> optimizeAssignments(Map<String, Driver> drivers, Map<String, Passenger> passengers, Graph graph) {
        List<RideAssignment> assignments = new ArrayList<>();

        // Create a min heap to store drivers based on their travel time to the closest available passenger
        PriorityQueue<Driver> minHeap = new PriorityQueue<>((d1, d2) -> {
            int timeToPassenger1 = getClosestPassengerTravelTime(d1, passengers.values(), graph);
            int timeToPassenger2 = getClosestPassengerTravelTime(d2, passengers.values(), graph);
            return timeToPassenger1 - timeToPassenger2;
        });

        // Push all drivers into the min heap
        minHeap.addAll(drivers.values());

        for (Passenger passenger : passengers.values()) {
            if (passenger.isDropped()) continue; // Skip already dropped passengers

            // Get the closest driver to the passenger
            Driver closestDriver = minHeap.poll();

            // Find the closest available passenger from the current driver's location
            Passenger closestPassenger = getClosestPassenger(closestDriver, passengers.values(), graph);

            int travelTime = graph.getTravelTime(closestDriver.getCurrentLocation(), closestPassenger.getDestination());

            // Capture the driver's initial location before updating
            Node initialLocation = closestDriver.getCurrentLocation();
            Node currentLocation = closestPassenger.getDestination();

            // Assign the driver to the closest passenger
            assignments.add(new RideAssignment(closestPassenger, closestDriver, initialLocation, currentLocation, travelTime));

            // Update the driver's current location to the passenger's destination
            closestDriver.setCurrentLocation(currentLocation);

            // Mark the passenger as dropped
            closestPassenger.setDropped(true);

            // Push the driver back into the min heap for further assignments
            minHeap.add(closestDriver);
        }

        return assignments;
    }

    // Method to get the travel time to the closest passenger for a given driver
    private static int getClosestPassengerTravelTime(Driver driver, Collection<Passenger> passengers, Graph graph) {
        int minTravelTime = Integer.MAX_VALUE;
        for (Passenger passenger : passengers) {
            if (!passenger.isDropped()) {
                int travelTime = graph.getTravelTime(driver.getCurrentLocation(), passenger.getCurrentLocation());
                minTravelTime = Math.min(minTravelTime, travelTime);
            }
        }
        return minTravelTime;
    }

    // Method to find the closest available passenger from the current driver's location
    private static Passenger getClosestPassenger(Driver driver, Collection<Passenger> passengers, Graph graph) {
        int minTravelTime = Integer.MAX_VALUE;
        Passenger closestPassenger = null;
        for (Passenger passenger : passengers) {
            if (!passenger.isDropped()) {
                int travelTime = graph.getTravelTime(driver.getCurrentLocation(), passenger.getCurrentLocation());
                if (travelTime < minTravelTime) {
                    minTravelTime = travelTime;
                    closestPassenger = passenger;
                }
            }
        }
        return closestPassenger;
    }
}
