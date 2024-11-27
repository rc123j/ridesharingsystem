import java.util.List;
import java.util.Scanner;

public class RideSharingSystem {
    private RideSharingService service;
    private Scanner scanner;

    public RideSharingSystem() {
        Graph graph = initializeGraph(); // Initialize the graph here
        this.service = new RideSharingService(graph);
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        RideSharingSystem system = new RideSharingSystem();
        system.start();
    }

    // Method to initialize the graph with predefined nodes and edges
    private Graph initializeGraph() {
        Graph graph = new Graph();

        // Adding nodes (locations)
        Node andheri = new Node("Andheri", "Andheri");
        Node bandra = new Node("Bandra", "Bandra");
        Node colaba = new Node("Colaba", "Colaba");
        Node dadar = new Node("Dadar", "Dadar");
        Node kurla = new Node("Kurla", "Kurla");
        Node powai = new Node("Powai", "Powai");
        Node thane = new Node("Thane", "Thane");

        graph.addNode(andheri);
        graph.addNode(bandra);
        graph.addNode(colaba);
        graph.addNode(dadar);
        graph.addNode(kurla);
        graph.addNode(powai);
        graph.addNode(thane);

        // Adding edges (roads) with travel times (in minutes)
        graph.addEdge(new Edge(andheri, bandra, 20));
        graph.addEdge(new Edge(andheri, kurla, 30));
        graph.addEdge(new Edge(andheri, powai, 25));
        graph.addEdge(new Edge(bandra, colaba, 35));
        graph.addEdge(new Edge(bandra, dadar, 15));
        graph.addEdge(new Edge(colaba, dadar, 20));
        graph.addEdge(new Edge(kurla, dadar, 10));
        graph.addEdge(new Edge(powai, thane, 30));
        graph.addEdge(new Edge(dadar, thane, 40));

        return graph;
    }

    // Method to start the CLI
    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("Welcome to Ride Sharing System");
            System.out.println("1. Add Driver");
            System.out.println("2. Add Passenger");
            System.out.println("3. Find Shortest Path");
            System.out.println("4. Assign Rides");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addDriver();
                    break;
                case 2:
                    addPassenger();
                    break;
                case 3:
                    findShortestPath();
                    break;
                case 4:
                    assignRides();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // Method to add a driver
    private void addDriver() {
        System.out.print("Enter driver name: ");
        String name = scanner.nextLine();
        System.out.print("Enter driver current location: ");
        String location = scanner.nextLine();
        Node locationNode = service.getGraph().getNode(location);
        if (locationNode == null) {
            System.out.println("Location not found in graph.");
            return;
        }
        Driver driver = new Driver(name, locationNode);
        service.addDriver(driver);
        System.out.println("Driver added successfully.");

        System.out.println();
    }

    // Method to add a passenger
    private void addPassenger() {
        System.out.print("Enter passenger name: ");
        String name = scanner.nextLine();

        System.out.print("Enter passenger current location: ");
        String currentLocationName = scanner.nextLine();
        Node currentLocation = service.getGraph().getNode(currentLocationName);
        if (currentLocation == null) {
            System.out.println("Location not found in graph.");
            return;
        }

        System.out.print("Enter passenger destination: ");
        String destinationName = scanner.nextLine();
        Node destination = service.getGraph().getNode(destinationName);
        if (destination == null) {
            System.out.println("Destination not found in graph.");
            return;
        }

        Passenger passenger = new Passenger(name, currentLocation, destination);
        service.addPassenger(passenger);
        System.out.println("Passenger added successfully.");

        System.out.println();
    }

    // Method to find the shortest path
    private void findShortestPath() {
        System.out.print("Enter source location: ");
        String sourceId = scanner.nextLine();
        Node source = service.getGraph().getNode(sourceId);
        if (source == null) {
            System.out.println("Source location not found in graph.");
            return;
        }

        System.out.print("Enter destination location: ");
        String destinationId = scanner.nextLine();
        Node destination = service.getGraph().getNode(destinationId);
        if (destination == null) {
            System.out.println("Destination location not found in graph.");
            return;
        }

        Path path = service.findShortestPath(source, destination);
        if (path == null) {
            System.out.println("No path found between the given locations.");
        } else {
            System.out.println("Shortest path: " + path);
        }

        System.out.println();
    }

    // Method to assign rides
    private void assignRides() {
        List<RideAssignment> assignments = service.assignRides();
        if (assignments.isEmpty()) {
            System.out.println("No rides assigned.");
        } else {
            System.out.println("Rides assigned:");
            for (RideAssignment assignment : assignments) {
                System.out.println(assignment);
            }
        }

        System.out.println();
    }
}