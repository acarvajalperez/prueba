package es.opplus.application.services.users.state;

public enum UserState {
    BUSY("busy", "red"),
    AVAILABLE("available", "green"),
    UNAVAILABLE("unavailable", "grey");

    private final String description;
    private final String color;

    UserState(String description, String color) {
        this.description = description;
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public String getColor() {
        return color;
    }
};