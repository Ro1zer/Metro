package org.example.Engine;

import org.example.Model.*;

import java.math.*;

public class MetroEngine {
    private Metro metro;
    private Station startStation;
    private Station endStation;

    public MetroEngine(Metro metro) {
        this.metro = metro;
    }

    public void setStartStation(String startStationName) {
        if (isHasStation(startStationName)) {
            startStation = metro.getStation(startStationName);
        }
    }

    public void setEndStation(String endStationName) {
        if (isHasStation(endStationName)) {
            endStation = metro.getStation(endStationName);
        }
    }

    //TODO: Make here a function which print a direction from startStation to endStation

    private boolean isHasStation(String name) {
        return metro.getStation(name) != null;
    }
}
