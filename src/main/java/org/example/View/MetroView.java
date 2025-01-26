package org.example.View;

import org.example.Engine.Metro;
import org.example.Model.Station;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.Scanner;

public class MetroView {
    private final Metro metro;
    private boolean isRunning;

    public MetroView(String path) {
        metro = new Metro(setup(path));
        isRunning = false;
    }

    public void run() {
        System.out.println("--Metro View--");
        isRunning = true;
        enterRoute();
        displayRoute();
        isRunning = false;
    }

    private void enterRoute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the start station: ");
        String start = scanner.nextLine();
        metro.setFrom(start);
        System.out.println("Enter the name of the end station: ");
        String end = scanner.nextLine();
        metro.setTo(end);
        scanner.close();
    }

    private void displayRoute() {
        final Station[] route = metro.direction();
        for (int i = 0; i < route.length; i++) {
            if ( route[i] != null && route[i].transplantation() == route[route.length - 1].line()) {
                System.out.printf("%s <==> ", route[i].name());
            } else if (i == route.length - 1) {
                System.out.println(route[i].name());
            } else {
                System.out.printf("%s -> ", route[i].name());
            }
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
