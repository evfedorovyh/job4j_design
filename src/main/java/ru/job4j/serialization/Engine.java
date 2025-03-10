package ru.job4j.serialization;

public class Engine {
    private final String engine;

    public Engine(String engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "phone='" + engine + '\''
                + '}';
    }
}
