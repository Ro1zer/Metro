package org.example.View;

import org.example.Service.Metro;
import org.example.Service.Command;
import org.example.Controller.*;

import java.util.Scanner;
import java.util.TreeMap;

public class MetroView {
    private TreeMap<String, Command> system;

    public MetroView(Metro metro) {
        this.system = new TreeMap<String, Command>();
        system.put("enter", new EnterRouteCommand(metro));
        system.put("display", new DisplayRouteCommand(metro));
        system.put("enter & display", new EnterAndDisplayRouteCommand(metro));
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        boolean trigger = false;
        while (!trigger) {
            System.out.println("--Metro View--");
            String command = input.nextLine();
            if (command.equals("exit")) {
                trigger = true;
            }
            else if (command.equals("help")) {
                help();
            }
            else if (system.containsKey(command)) {
                system.get(command).execute(input);
            }
        }
        input.close();
    }

    private void help() {
        for (String s : system.keySet()) {
            System.out.println(s);
        }
    }
}
