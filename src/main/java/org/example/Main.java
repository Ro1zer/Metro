package org.example;

import org.example.View.MetroView;

public class Main {
    public static void main(String[] args) {
        MetroView metroView = new MetroView("./src/main/resources/json/example.json");
        metroView.run();
    }
}