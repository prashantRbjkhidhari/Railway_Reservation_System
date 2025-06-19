package com.railway.service;

import com.railway.model.Train;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainService {
    private Map<String, Train> trains;

    public TrainService() {
        this.trains = new HashMap<>();
    }

    public void addTrain(Train train) {
        trains.put(train.getTrainNumber(), train);
    }

    public Train getTrainByNumber(String trainNumber) {
        return trains.get(trainNumber);
    }

    public List<Train> getAllTrains() {
        return new ArrayList<>(trains.values());
    }

    public boolean updateAvailableSeats(String trainNumber, int seats) {
        Train train = trains.get(trainNumber);
        if (train != null) {
            train.setAvailableSeats(seats);
            return true;
        }
        return false;
    }
}
