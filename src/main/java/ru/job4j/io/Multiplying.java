package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringJoiner;

public class Multiplying {
    public static void main(String[] args) {
        try (FileOutputStream output = new FileOutputStream("data/multiplying.txt")) {
            StringJoiner stringOut;
            for (int i = 1; i < 10; i++) {
                stringOut = new StringJoiner("\t");
                for (int j = 1; j < 10; j++) {
                    stringOut.add(String.format("%d * %d = %d", j, i, i * j));
                }
                output.write(stringOut.toString().getBytes());
                output.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
