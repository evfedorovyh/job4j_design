package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Transport {
    private final boolean isBus;
    private final int seatCount;
    private final String model;
    private final Engine engine;
    private final String[] features;

    public Transport(boolean isBus, int seatCount, String model, Engine engine, String[] features) {
        this.isBus = isBus;
        this.seatCount = seatCount;
        this.model = model;
        this.engine = engine;
        this.features = features;
    }

    @Override
    public String toString() {
        return "Transport{"
                + "isBus=" + isBus
                + ", seatCount=" + seatCount
                + ", model='" + model + '\''
                + ", engine=" + engine
                + ", features=" + Arrays.toString(features)
                + '}';
    }

    public static void main(String[] args) {
        final Transport transport = new Transport(true, 30, "Man", new Engine("diesel"),
                new String[] {"Conditioner", "TV"});
        /*Выводим объект в консоль*/
        System.out.println(transport);
        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(transport));
        /* Создаём новую json-строку с модифицированными данными*/
        String transportJson = gson.toJson(transport);
        Transport transportMod = gson.fromJson(transportJson, Transport.class);
        System.out.println(transportMod);
        /* Создаём новую json-строку с модифицированными данными*/
        transportJson =
                "{"
                        + "\"isBus\":true,"
                        + "\"seatCount\":35,"
                        + "\"model\":\"Volvo\","
                        + "\"engine\":"
                        + "{"
                        + "\"engine\":\"Petrol\""
                        + "},"
                        + "\"features\":"
                        + "[\"Conditioner\",\"TV\", \"Toilet\"]"
                        + "}";
        transportMod = gson.fromJson(transportJson, Transport.class);
        System.out.println(transportMod);
    }
}
