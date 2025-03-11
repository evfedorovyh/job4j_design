package ru.job4j.serialization.json;

public class Engine {
    private String engine;

    public Engine() {
    }

    public String getEngine() {
        return engine;
    }

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
