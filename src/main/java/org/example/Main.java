package org.example;

import org.example.View.MetroView;

public class Main {
    public static void main(String[] args) {
        MetroView program = new MetroView("./src/main/resources/json/example.json");
        program.run();
    }
}