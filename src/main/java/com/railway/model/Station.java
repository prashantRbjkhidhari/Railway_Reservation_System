package com.railway.model;

public class Station {
    private String stationCode;
    private String stationName;

    public Station(String stationCode, String stationName) {
        this.stationCode = stationCode;
        this.stationName = stationName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    @Override
    public String toString() {
        return stationCode + " - " + stationName;
    }
}
