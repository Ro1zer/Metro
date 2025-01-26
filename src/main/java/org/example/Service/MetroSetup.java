package org.example.Service;

import java.io.BufferedReader;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;

import org.example.Model.Station;

public class MetroSetup {
    public static void setupMetro(Metro metro, String path) {
        JSONArray jsonArray = setup(path);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject lineJSONObject = jsonArray.getJSONObject(i);
            String lineStr = lineJSONObject.getString("line");
            JSONArray stationsJSONArray = lineJSONObject.getJSONArray("stations");
            for (int j = 0; j < stationsJSONArray.length(); j++) {
                JSONObject stationJSONObject = stationsJSONArray.getJSONObject(j);
                metro.putStation(new Station(
                        stationJSONObject.getInt("id"),
                        stationJSONObject.getString("name"),
                        lineStr,
                        stationJSONObject.getString("transplantationTo")));
            }
        }
    }

    private static JSONArray setup(String path) {
        try {
            // TODO: I know that is the bad way for reading json file, I will change that soon
            BufferedReader bw = new BufferedReader(new FileReader(path));
            StringBuilder jsonString = new StringBuilder();
            String s = "";
            while ((s = bw.readLine()) != null) {
                s = s + "\n";
                jsonString.append(s);
            }
            bw.close();
            return new JSONArray(jsonString.toString());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        throw new Error("Something went wrong");
    }
}
