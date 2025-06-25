import java.util.*;
import java.time.LocalTime;

public class Train {
    private String trainCode;
    private String trainName;
    private int frequency;
    private String source;
    private String destination;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private double distance;
    private int totalSeats;
    private int availableSeats;
    private double baseFare;
    private List<Passenger> passengers;
      // Constructor with basic information (backward compatibility)
    public Train(String trainCode, String trainName, int frequency) {
        this.trainCode = trainCode;
        this.trainName = trainName;
        this.frequency = frequency;
        this.source = "Not specified";
        this.destination = "Not specified";
        this.departureTime = LocalTime.of(0, 0);
        this.arrivalTime = LocalTime.of(0, 0);
        this.distance = 0.0;
        this.totalSeats = 100;
        this.availableSeats = 100;
        this.baseFare = 50.0;
        this.passengers = new ArrayList<>();
    }
    
    // Enhanced constructor with all details
    public Train(String trainCode, String trainName, int frequency, String source, 
                 String destination, LocalTime departureTime, LocalTime arrivalTime, 
                 double distance, int totalSeats, double baseFare) {
        this.trainCode = trainCode;
        this.trainName = trainName;
        this.frequency = frequency;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.distance = distance;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.baseFare = baseFare;
        this.passengers = new ArrayList<>();
    }
    // Getters and Setters
    public String getTrainCode() {
        return trainCode;
    }
    
    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }
    
    public String getTrainName() {
        return trainName;
    }
    
    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
    
    public int getFrequency() {
        return frequency;
    }
    
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    
    public String getSource() {
        return source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public LocalTime getDepartureTime() {
        return departureTime;
    }
    
    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
    
    public LocalTime getArrivalTime() {
        return arrivalTime;
    }
    
    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    
    public double getDistance() {
        return distance;
    }
    
    public void setDistance(double distance) {
        this.distance = distance;
    }
    
    public int getTotalSeats() {
        return totalSeats;
    }
    
    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }
    
    public int getAvailableSeats() {
        return availableSeats;
    }
    
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    
    public double getBaseFare() {
        return baseFare;
    }
    
    public void setBaseFare(double baseFare) {
        this.baseFare = baseFare;
    }
    
    public List<Passenger> getPassengers() {
        return passengers;
    }
    
    public void addPassenger(Passenger passenger) {
        if (availableSeats > 0) {
            this.passengers.add(passenger);
            this.availableSeats--;
        }
    }
    
    public void removePassenger(Passenger passenger) {
        if (this.passengers.remove(passenger)) {
            this.availableSeats++;
        }
    }
    
    // Calculate journey duration
    public String getJourneyDuration() {
        long hours = java.time.Duration.between(departureTime, arrivalTime).toHours();
        long minutes = java.time.Duration.between(departureTime, arrivalTime).toMinutes() % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
    
    // Check seat availability
    public boolean hasAvailableSeats() {
        return availableSeats > 0;
    }
    
    @Override
    public String toString() {
        return String.format("Train Code: %s | %s | %s → %s | Dep: %s | Arr: %s | Duration: %s | Seats: %d/%d | Fare: $%.2f", 
                           trainCode, trainName, source, destination, 
                           departureTime, arrivalTime, getJourneyDuration(), 
                           availableSeats, totalSeats, baseFare);
    }
}
