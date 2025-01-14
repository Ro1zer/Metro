package org.example.Model;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.ArrayList;

import org.example.Enum.Line;

public class Metro {
    private TreeMap<String, Station> stations;

    public Metro() {
        stations = new TreeMap<>();
    }

    public Metro(Station ... stationsArray) {
        stations = new TreeMap<>();
        for (Station station : stationsArray) {
            if (station == null) {
                break;
            }
            stations.put(station.name(), station);
        }
    }

    public void putStation(Station station) {
        if (stations.containsKey(station.name())) {
            return;
        }
        stations.put(station.name(), station);
    }

    public final Station getStation(String name) {
        return stations.get(name);
    }

    public Station[] getStationArray(Line line) {
        ArrayList<Station> temp = new ArrayList<>();
        for (Station station : stations.values()) {
            if (station.line() == line) {
                temp.add(station);
            }
        }
        return Arrays.copyOf(temp.toArray(), temp.size(), Station[].class);
    }

    public void clear() {
        stations.clear();
    }
}
