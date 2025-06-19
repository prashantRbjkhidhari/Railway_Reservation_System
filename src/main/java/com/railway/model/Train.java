package com.railway.model;

public class Train {
    private String trainNumber;
    private String trainName;
    private Station source;
    private Station destination;
    private int totalSeats;
    private int availableSeats;
    private double ticketPrice;

    public Train(String trainNumber, String trainName, Station source, Station destination, 
                int totalSeats, double ticketPrice) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.ticketPrice = ticketPrice;
    }

    // Getters and Setters
    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public Station getSource() {
        return source;
    }

    public Station getDestination() {
        return destination;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public String toString() {
        return "Train [" + trainNumber + "] " + trainName + 
               " from " + source.getStationName() + 
               " to " + destination.getStationName() + 
               " (Available Seats: " + availableSeats + ")";
    }
}
