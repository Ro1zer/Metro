package org.example;

import java.io.*;

import org.json.JSONArray;

import org.example.Model.Metro;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Main.setup();
    }

    public static void setup() {
        try {
            BufferedReader bw = new BufferedReader(new FileReader("C:\\JavaSelf\\Metro\\src\\main\\resources\\json\\example.json"));
            StringBuilder jsonString = new StringBuilder();
            String s = "";
            while ((s = bw.readLine()) != null) {
                s = s + "\n";
                jsonString.append(s);
            }
            bw.close();
            Metro metro = new Metro(new JSONArray(jsonString.toString()));
            metro.print();
        } catch (Exception ex) {
            return;
        }
    }
}