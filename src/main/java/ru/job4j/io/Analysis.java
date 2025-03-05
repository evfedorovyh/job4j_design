package ru.job4j.io;

import java.io.*;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter output = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            boolean working = false;
            String readLine = reader.readLine();
            while (!readLine.isEmpty()) {
                String[] line = readLine.split(" ");
                if (!working && ("400".equals(line[0]) || "500".equals(line[0]))) {
                    working = true;
                    output.print(line[1] + ";");
                } else if (working && ("200".equals(line[0]) || "300".equals(line[0]))) {
                    working = false;
                    output.println(line[1] + ";");
                }
                readLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
