package org.example.View;

import org.example.Engine.Metro;
import org.example.Model.Station;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.Scanner;

public class MetroView {
    private final Metro metro;

    public MetroView(String path) {
        metro = new Metro(setup(path));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the start station: ");
        String start = scanner.nextLine();
        metro.setFrom(start);
        System.out.println("Enter the name of the end station: ");
        String end = scanner.nextLine();
        metro.setTo(end);
        scanner.close();
        final Station[] result = metro.direction();
        final int FIRST_INDEX = 0;
        final int LAST_INDEX = result.length - 1;
        for (Station station : result) {
            System.out.printf(station.equals(result[LAST_INDEX]) ? "%s" : "%s -> ", station.name());
        }
    }

    private JSONArray setup(String path) {
        try {
            //TODO: I know that is the bad way for reading json file, I will change that soon
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
