package org.example.Engine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.Arrays;

import org.example.Model.Station;
import org.json.JSONArray;
import org.json.JSONObject;

public class Metro {
    private final TreeMap<String, Station> stations;
    private Station from;
    private Station to;

    public Metro(JSONArray jsonArray) {
        stations = new TreeMap<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject lineJSONObject = jsonArray.getJSONObject(i);
            String lineStr = lineJSONObject.getString("line");
            JSONArray stationsJSONArray = lineJSONObject.getJSONArray("stations");
            for (int j = 0; j < stationsJSONArray.length(); j++) {
                JSONObject stationJSONObject = stationsJSONArray.getJSONObject(j);
                stations.put(stationJSONObject.getString("name"), new Station(
                        stationJSONObject.getInt("id"),
                        stationJSONObject.getString("name"),
                        lineStr,
                        stationJSONObject.getString("transplantationTo")));
            }
        }
    }

    public void setFrom(String from) {
        if (isValid(from)) {
            this.from = stations.get(from);
        }
    }

    public void setTo(String to) {
        if (isValid(to)) {
            this.to = stations.get(to);
        }
    }

    public Station[] direction() {
        if (from.line().equals(to.line())) {
            return getStationArray(from, to);
        }
        Station stationTransitFor = new Station(), stationTransitTo = new Station();
        for (Station station : stations.values()) {
            if (station.line() == from.line() && station.transplantation() == to.line()) {
                stationTransitFor = station;
            } else if (station.line() == to.line() && station.transplantation() == from.line()) {
                stationTransitTo = station;
            }
        }
        final Station[] oneLineStations = getStationArray(from, stationTransitFor);
        final Station[] twoLineStations = getStationArray(stationTransitTo, to);
        Station[] array = new Station[oneLineStations.length + twoLineStations.length];
        System.arraycopy(oneLineStations, 0, array, 0, oneLineStations.length);
        System.arraycopy(twoLineStations, 0, array, oneLineStations.length, twoLineStations.length);
        return array;
    }

    public void print() {
        for (Station station : direction()) {
            System.out.println(station);
        }
    }

    private boolean isValid(String stationName) {
        return stations.containsKey(stationName);
    }

    private Station[] getStationArray(Station from, Station to) {
        ArrayList<Station> arrayList = new ArrayList<>();
        stations.values().forEach(station -> {
            if (station.line() == from.line()) {
                arrayList.add(station);
            }
        });
        Comparator<Station> comparator = Comparator.comparing(Station::id);
        if (from.id() > to.id()) {
            arrayList.sort(comparator.reversed());
        } else {
            arrayList.sort(comparator);
        }
        final int fromIndex = arrayList.indexOf(from);
        final int toIndex = arrayList.indexOf(to);
        return Arrays.copyOfRange(arrayList.toArray(new Station[0]), fromIndex, toIndex + 1);
    }

    @Override
    public String toString() {
        return stations.toString();
    }
}
