package org.example.Controller;

import org.example.Service.Metro;
import org.example.Service.Command;
import java.util.Scanner;

public class EnterAndDisplayRouteCommand implements Command{
    private final Metro metro;

    public EnterAndDisplayRouteCommand(Metro metro) {
        this.metro = metro;
    }

    @Override
    public void execute(Scanner input) {
        new EnterRouteCommand(metro).execute(input);
        new DisplayRouteCommand(metro).execute(input);
    }
}
