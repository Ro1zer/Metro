package org.example.Model;

import java.util.TreeMap;
import java.util.ArrayList;

import org.example.Enum.Line;
import org.json.JSONArray;
import org.json.JSONObject;

public class Metro {
    private TreeMap<String, Station> stations;

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
                        stationJSONObject.getBoolean("isTransit")));
            }
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
        return temp.toArray(new Station[temp.size()]);
    }

    public void print() {
        for (Station station : stations.values()) {
            System.out.println(station);
        }
    }

    public void clear() {
        stations.clear();
    }

    @Override
    public String toString() {
        return stations.toString();
    }
}
