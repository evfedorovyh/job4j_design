package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.*;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "transport")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transport {
    @XmlAttribute
    private boolean isBus;
    @XmlAttribute
    private int seatCount;
    @XmlAttribute
    private String model;
    private Engine engine;
    @XmlElementWrapper(name = "features")
    @XmlElement(name = "feature")
    private String[] features;

    public Transport() {
    }

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

    public static void main(String[] args) throws Exception {
        final Transport transport = new Transport(true, 30, "Man", new Engine("diesel"),
                new String[] {"Conditioner", "TV"});
        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Transport.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(transport, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Transport result = (Transport) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
