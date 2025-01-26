package org.example.Enum;

public enum Line {
    Red("Red"),
    Blue("Blue"),
    Green("Green");

    private final String color;

    Line(String color) {
        this.color = color;
    }

    public String color() {
        return color;
    }

    public static Line fromString(String color) {
        for (Line b : Line.values()) {
            if (b.color.equalsIgnoreCase(color)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Line{" + color + '}';
    }
}
