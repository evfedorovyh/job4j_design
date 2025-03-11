package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "engine")
public class Engine {
    @XmlAttribute
    private String engine;

    public Engine() {
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
