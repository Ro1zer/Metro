package org.example.Model;

import java.util.TreeMap;

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
                        stationJSONObject.getString("transplantationTo")));
            }
        }
    }

    public final Station getStation(String name) {
        return stations.get(name);
    }

    public void print() {
        for (Station station : stations.values()) {
            System.out.println(station);
        }
    }

    //TODO: Make a functions which will get array of Stations (it's need for printing a direction)

    @Override
    public String toString() {
        return stations.toString();
    }
}
