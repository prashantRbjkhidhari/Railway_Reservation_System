import java.util.*;

public class RailwayReservationSystem {
    private String systemID;
    private String response;
    private List<Train> trains;
    private List<Passenger> passengers;
    private Admin admin;
    
    public RailwayReservationSystem(String systemID) {
        this.systemID = systemID;
        this.response = "System Initialized";
        this.trains = new ArrayList<>();
        this.passengers = new ArrayList<>();
        this.admin = new Admin("ADMIN001", "System Administrator");
    }
    
    // Getters and Setters
    public String getSystemID() {
        return systemID;
    }
    
    public void setSystemID(String systemID) {
        this.systemID = systemID;
    }
    
    public String getResponse() {
        return response;
    }
    
    public void setResponse(String response) {
        this.response = response;
    }
    
    public List<Train> getTrains() {
        return trains;
    }
    
    public List<Passenger> getPassengers() {
        return passengers;
    }
    
    public Admin getAdmin() {
        return admin;
    }
    
    // Uses system functionality
    public void uses() {
        this.response = "Railway Reservation System is being used";
        System.out.println("Welcome to Railway Reservation System!");
    }
    
    // Works functionality
    public void works() {
        this.response = "System is working properly";
        System.out.println("Railway Reservation System is operational and ready to serve!");
    }
    
    // Add train to system
    public void addTrain(Train train) {
        trains.add(train);
        System.out.println("Train added successfully: " + train.getTrainName());
    }
    
    // Add passenger to system
    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
        System.out.println("Passenger registered successfully: " + passenger.getName());
    }
    
    // Find passenger by ID
    public Passenger findPassenger(String paxId) {
        for (Passenger passenger : passengers) {
            if (passenger.getPaxId().equals(paxId)) {
                return passenger;
            }
        }
        return null;
    }
    
    // Find train by code
    public Train findTrain(String trainCode) {
        for (Train train : trains) {
            if (train.getTrainCode().equals(trainCode)) {
                return train;
            }
        }
        return null;
    }
    
    // Display all trains
    public void displayAllTrains() {
        System.out.println("\n=== AVAILABLE TRAINS ===");
        if (trains.isEmpty()) {
            System.out.println("No trains available.");
            return;
        }
        
        for (Train train : trains) {
            System.out.println(train);
        }
    }
    
    @Override
    public String toString() {
        return "Railway Reservation System ID: " + systemID + ", Status: " + response;
    }
}
