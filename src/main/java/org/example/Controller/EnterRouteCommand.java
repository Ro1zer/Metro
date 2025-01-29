package org.example.Controller;

import org.example.Service.Metro;
import org.example.Service.Command;
import java.util.Scanner;

public class EnterRouteCommand implements Command {
    private final Metro metro;

    public EnterRouteCommand(Metro metro) {
        this.metro = metro;
    }

    @Override
    public void execute(Scanner input) {
        System.out.println("Enter the name of the start station: ");
        String start = input.nextLine();
        metro.setFrom(start);
        System.out.println("Enter the name of the end station: ");
        String end = input.nextLine();
        metro.setTo(end);
    }
}
