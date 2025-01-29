package org.example;

import org.example.Service.Metro;
import org.example.Service.MetroSetup;
import org.example.View.MetroView;

public class Main {
    public static void main(String[] args) {
        Metro engine = MetroSetup.setupMetro("./src/main/resources/json/example.json");
        MetroView metroView = new MetroView(engine);
        metroView.run();
    }
}