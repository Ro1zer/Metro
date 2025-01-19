package org.example.Model;

import org.example.Enum.Line;

public class Station {
    private final int id;
    private final String name;
    private final Line line;
    private final String transplantation;

    public Station() {
        id = 0;
        name = "";
        line = null;
        transplantation = "";
    }

    public Station(int id, String name, String line, String transplantation) {
        if (id < 0) throw new IllegalArgumentException();
        this.id = id;
        this.name = name;
        this.line = Line.fromString(line);
        this.transplantation = transplantation;
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Line line() {
        return line;
    }

    @Override
    public String toString() {
        return "Station{" + "id=" + id + ", name=" + name + ", line=" + line + ", transplantation=" + transplantation + '}';
    }
}
