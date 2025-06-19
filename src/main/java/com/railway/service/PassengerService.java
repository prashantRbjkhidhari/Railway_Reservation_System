package com.railway.service;

import com.railway.model.Passenger;
import java.util.ArrayList;
import java.util.List;

public class PassengerService {
    private List<Passenger> passengers;

    public PassengerService() {
        this.passengers = new ArrayList<>();
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public List<Passenger> getAllPassengers() {
        return new ArrayList<>(passengers);
    }
}
