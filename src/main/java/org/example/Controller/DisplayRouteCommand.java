package org.example.Controller;

import org.example.Service.Metro;
import org.example.Model.Station;
import org.example.Service.Command;
import java.util.Scanner;

public class DisplayRouteCommand implements Command {
    private final Metro metro;

    public DisplayRouteCommand(Metro metro) {
        this.metro = metro;
    }

    @Override
    public void execute(Scanner input) {
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
