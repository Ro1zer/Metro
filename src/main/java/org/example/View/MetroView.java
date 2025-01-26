package org.example.View;

import org.example.Engine.Metro;
import org.example.Model.Station;

import java.util.Scanner;

public class MetroView {
    private final Metro metro;
    private boolean isRunning;

    public MetroView(Metro metro) {
        this.metro = metro;
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
}
