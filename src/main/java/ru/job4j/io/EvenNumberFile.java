package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            StringBuilder textOut = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            int value;
            for (String line : lines) {
                value = Integer.parseInt(line);
                textOut.append(value);
                textOut.append((value % 2 == 0) ? " - четное число" : " - нечетное число");
                textOut.append(System.lineSeparator());
            }
            System.out.println(textOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
