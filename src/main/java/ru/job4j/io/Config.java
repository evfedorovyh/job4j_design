package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(a -> {
                if (!a.startsWith("#") && !a.isEmpty()) {
                    String[] line = a.split("=", 2);
                    if (line.length < 2 || line[0].isEmpty() || line[1].isEmpty()) {
                        throw new IllegalArgumentException("Wrong key or value");
                    }
                    values.put(line[0], line[1]);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        String result = values.get(key);
        if (result == null) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        return result;
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
        System.out.println(System.lineSeparator());
        Config config = new Config("data/app.properties");
        config.load();
        for (String key : config.values.keySet()) {
            System.out.println(key + "\t" + config.value(key));
        }
    }
}
