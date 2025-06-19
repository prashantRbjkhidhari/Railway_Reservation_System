package com.railway.model;

import java.time.LocalDate;
import java.util.UUID;

public class Ticket {
    private String pnrNumber;
    private Train train;
    private Passenger passenger;
    private LocalDate journeyDate;
    private double fare;
    private String status; // CONFIRMED, WAITING, CANCELLED

    public Ticket(Train train, Passenger passenger, LocalDate journeyDate) {
        this.pnrNumber = generatePNR();
        this.train = train;
        this.passenger = passenger;
        this.journeyDate = journeyDate;
        this.fare = train.getTicketPrice();
        this.status = "CONFIRMED";
    }

    private String generatePNR() {
        return "PNR" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // Getters
    public String getPnrNumber() {
        return pnrNumber;
    }

    public Train getTrain() {
        return train;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public LocalDate getJourneyDate() {
        return journeyDate;
    }

    public double getFare() {
        return fare;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket [PNR: " + pnrNumber + "]\n" +
               "Passenger: " + passenger.getName() + "\n" +
               "Train: " + train.getTrainName() + " (" + train.getTrainNumber() + ")\n" +
               "Journey: " + train.getSource().getStationName() + " to " + 
               train.getDestination().getStationName() + "\n" +
               "Date: " + journeyDate + "\n" +
               "Status: " + status + "\n" +
               "Fare: Rs. " + fare;
    }
}
