package ru.job4j.io;

import java.io.*;

public class Buffered {
    public static void main(String[] args) {
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream("data/input.txt"));
             BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream("data/output.txt", true))) {
            output.write(input.readAllBytes());
            output.write(System.lineSeparator().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
