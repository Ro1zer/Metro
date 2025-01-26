package org.example.Model;

import org.example.Enum.Line;

public class Station {
    private final int id;
    private final String name;
    private final Line line;
    private final Line transplantation;

    public Station() {
        id = -1;
        name = null;
        line = null;
        transplantation = null;
    }

    public Station(int id, String name, String line, String transplantation) {
        if (id < 0)
            throw new IllegalArgumentException();
        this.id = id;
        this.name = name;
        this.line = Line.fromString(line);
        this.transplantation = Line.fromString(transplantation);
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

    public Line transplantation() {
        return transplantation;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Station s = (Station) obj;
        return id == s.id && name == s.name && line == s.line && transplantation == s.transplantation;
    }

    @Override
    public String toString() {
        return "Station{" + "id=" + id + ", name=" + name + ", line=" + line + ", transplantation=" + transplantation + '}';
    }
}
