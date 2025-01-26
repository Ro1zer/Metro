package org.example;

import org.example.View.MetroView;
import org.example.Engine.Metro;
import org.example.Engine.MetroSetup;

public class Main {
    public static void main(String[] args) {
        Metro engine = new Metro();
        MetroSetup.setupMetro(engine, "./src/main/resources/json/example.json");
        MetroView metroView = new MetroView(engine);
        metroView.run();
    }
}