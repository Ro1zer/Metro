package org.example.View;

import org.example.Model.Station;
import org.example.Enum.Line;
import org.example.Service.Metro;

import java.util.Scanner;

public class MetroView {
    private final Metro metro;
    private Scanner input;

    public MetroView(Metro metro) {
        this.metro = metro;
        this.input = new Scanner(System.in);
    }

    public void run() {
        boolean trigger = false;
        while (!trigger) {
            System.out.println("--Metro View--");
            String command = input.nextLine();
            if (command.equals("exit")) {
                trigger = true;
            }
            else if (command.equals("route")) {
                enterRoute();
                displayRoute();

            }
            else if (command.equals("list")) {
                listOfLine();
            }

        }
        input.close();
    }

    private void listOfLine() {
        System.out.println("Enter the line which you interesting: ");
        displayList(Line.fromString(input.nextLine()));
    }

    private void enterRoute() {
        System.out.println("Enter the name of the start station: ");
        String start = input.nextLine();
        metro.setFrom(start);
        System.out.println("Enter the name of the end station: ");
        String end = input.nextLine();
        metro.setTo(end);
    }

    private void displayList(Line line) {
        System.out.println("--List of stations from line (" + line.color() + ")--");
        for (Station station : metro.arrayOfStationByLine(line)) {
            System.out.println(station.name());
        }
    }

    private void displayRoute() {
        final Station[] route = metro.direction();
        for (int i = 0; i < route.length; i++) {
            if (route[i] != null && route[i].transplantation() == route[route.length - 1].line()) {
                System.out.printf("%s <==> ", route[i].name());
            }
            else if (i == route.length - 1) {
                System.out.println(route[i].name());
            }
            else {
                System.out.printf("%s -> ", route[i].name());
            }
        }
    }
}
