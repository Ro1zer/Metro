package org.example.Engine;

import org.example.Enum.Line;
import org.example.Model.Station;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONArray;
import org.json.JSONObject;

public class Metro {
    private final TreeMap<String, Station> stationTreeMap;
    private Station from;
    private Station to;

    public Metro(JSONArray jsonArray) {
        stationTreeMap = new TreeMap<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject lineJSONObject = jsonArray.getJSONObject(i);
            String lineStr = lineJSONObject.getString("line");
            JSONArray stationsJSONArray = lineJSONObject.getJSONArray("stations");
            for (int j = 0; j < stationsJSONArray.length(); j++) {
                JSONObject stationJSONObject = stationsJSONArray.getJSONObject(j);
                stationTreeMap.put(stationJSONObject.getString("name"), new Station(
                        stationJSONObject.getInt("id"),
                        stationJSONObject.getString("name"),
                        lineStr,
                        stationJSONObject.getString("transplantationTo")));
            }
        }
    }

    public void setFrom(String from) {
        if (isValid(from)) {
            this.from = stationTreeMap.get(from);
        }
    }

    public void setTo(String to) {
        if (isValid(to)) {
            this.to = stationTreeMap.get(to);
        }
    }

    public void putStation(Station station) {
        stationTreeMap.put(station.name(), station);
    }

    public Station[] direction() {
        if (from.line().equals(to.line())) {
            return rangeOfStation(from, to);
        }
        Station stationTransitFrom = new Station(), stationTransitTo = new Station();
        for (Station station : stationTreeMap.values()) {
            if (station.line() == from.line() && station.transplantation() == to.line()) {
                stationTransitFrom = station;
            } else if (station.line() == to.line() && station.transplantation() == from.line()) {
                stationTransitTo = station;
            }
        }
        final Station[] oneLineStations = rangeOfStation(from, stationTransitFrom);
        final Station[] twoLineStations = rangeOfStation(stationTransitTo, to);
        final Station[] array = Arrays.copyOf(oneLineStations, oneLineStations.length + twoLineStations.length);
        System.arraycopy(twoLineStations, 0, array, oneLineStations.length, twoLineStations.length);
        return array;
    }

    private boolean isValid(String stationName) {
        return stationTreeMap.containsKey(stationName);
    }

    private Station[] rangeOfStation(Station from, Station to) {
        Station[] result = new Station[lineSize(from.line())];
        AtomicInteger index = new AtomicInteger(0);
        stationTreeMap.values().forEach(station -> {
            if (station.line() == from.line()) {
                result[index.getAndIncrement()] = station;
            }
        });
        Comparator<Station> comparator = Comparator.comparing(Station::id);
        if (from.id() > to.id()) {
            Arrays.sort(result, comparator.reversed());
        } else {
            Arrays.sort(result, comparator);
        }
        int fromIndex = -1, toIndex = -1;
        for (index.set(0); index.get() < result.length; index.getAndIncrement()) {
            if (result[index.get()].equals(from)) {
                fromIndex = index.get();
            } else if (result[index.get()].equals(to)) {
                toIndex = index.get();
            }
        }
        return Arrays.copyOfRange(result, fromIndex, toIndex + 1);
    }

    private int lineSize(Line line) {
        AtomicInteger index = new AtomicInteger(0);
        stationTreeMap.values().forEach(station -> {
            if (station.line() == line) {
                index.getAndIncrement();
            }
        });
        return index.get();
    }

    @Override
    public String toString() {
        return stationTreeMap.toString();
    }
}
