package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transport {
    private boolean bus;
    private int seatCount;
    private String model;
    private Engine engine;
    private String[] features;

    public Transport() {
    }

    public boolean getBus() {
        return bus;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public String getModel() {
        return model;
    }

    public Engine getEngine() {
        return engine;
    }

    public String[] getFeatures() {
        return features;
    }

    public Transport(boolean bus, int seatCount, String model, Engine engine, String[] features) {
        this.bus = bus;
        this.seatCount = seatCount;
        this.model = model;
        this.engine = engine;
        this.features = features;
    }

    @Override
    public String toString() {
        return "Transport{"
                + "isBus=" + bus
                + ", seatCount=" + seatCount
                + ", model='" + model + '\''
                + ", engine=" + engine
                + ", features=" + Arrays.toString(features)
                + '}';
    }

    public static void main(String[] args) {
        /* JSONObject из json-строки строки */
        JSONObject jsonEngine = new JSONObject("{\"engine\":\"diesel\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Conditioner");
        list.add("TV");
        JSONArray jsonFeatures = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Transport transport = new Transport(true, 30, "Man",
                new Engine("diesel"),
                new String[] {"Conditioner", "TV"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bus", transport.getBus());
        jsonObject.put("seatCount", transport.getSeatCount());
        jsonObject.put("model", transport.getModel());
        jsonObject.put("engine", jsonEngine);
        jsonObject.put("features", jsonFeatures);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект transport в json-строку */
        System.out.println(new JSONObject(transport).toString());
    }
}
