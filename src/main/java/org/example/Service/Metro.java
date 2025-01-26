package org.example.Service;

import org.example.Enum.Line;
import org.example.Model.Station;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Metro {
    private final TreeMap<String, Station> stationTreeMap;
    private Station from;
    private Station to;

    public Metro() {
        stationTreeMap = new TreeMap<>();
        from = null;
        to = null;
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
        Station[] result = arrayOfStationByLine(from.line());
        Comparator<Station> comparator = Comparator.comparing(Station::id);
        if (from.id() > to.id()) {
            Arrays.sort(result, comparator.reversed());
        } else {
            Arrays.sort(result, comparator);
        }
        return Arrays.copyOfRange(result, linearSearch(result, from), linearSearch(result, to) + 1);
    }

    private int linearSearch(final Station[] array, final Station key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(key)) {
                return i;
            }
        }
        throw new Error("The Station haven't been found: " + key.name());
    }

    private Station[] arrayOfStationByLine(Line line) {
        AtomicInteger counter = new AtomicInteger(0);
        stationTreeMap.values().forEach(station -> {
            if (station.line() == line) {
                counter.getAndIncrement();
            }
        });
        Station[] array = new Station[counter.get()];
        counter.set(0);
        stationTreeMap.values().forEach(station -> {
            if (station.line() == line) {
                array[counter.getAndIncrement()] = station;
            }
        });
        return array;
    }

    @Override
    public String toString() {
        return stationTreeMap.toString();
    }
}
