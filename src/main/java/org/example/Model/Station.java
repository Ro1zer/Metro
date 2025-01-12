package org.example.Model;

import org.example.Enum.Line;

public class Station {
    private final int id;
    private final String name;
    private final Line line;
    private final boolean isTransit;

    public Station(int id, String name, Line line, boolean isTransit) {
        if (id  < 0) throw new IllegalArgumentException();
        this.id = id;
        this.name = name;
        this.line = line;
        this.isTransit = isTransit;
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
        return "Station{" + "id=" + id + ", name=" + name + ", line=" + line + ", isTransit=" + isTransit + '}';
    }
}
