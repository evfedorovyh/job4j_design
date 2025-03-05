package ru.job4j.io;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintUsage {
    public static void main(String[] args) {
        try (PrintStream stream = new PrintStream("data/print.txt");
             PrintWriter writer = new PrintWriter("data/write.txt")) {
            stream.println("Из PrintStream в FileOutputStream");
            stream.write("New line".getBytes());
            writer.write("New writer line");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
