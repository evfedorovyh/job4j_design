package ru.job4j.io;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        String delimiter = argsName.get("delimiter");
        String path = argsName.get("out");
        Map<String, String> data = new LinkedHashMap<>();
        Scanner keyScanner;
        Scanner lineScanner;
        StringJoiner stringJoiner;
        try (PrintWriter writer = ("stdout".equals(path)) ? new PrintWriter(System.out)
                : new PrintWriter(new FileWriter(path), true);
             Scanner scanner = new Scanner(Path.of(argsName.get("path"))).useDelimiter(delimiter)
        ) {
            for (String arg : argsName.get("filter").split(",")) {
                data.put(arg, "");
            }
            writer.println(argsName.get("filter").replace(",", delimiter));
            String firstLine = scanner.nextLine();
            while (scanner.hasNextLine()) {
                keyScanner = new Scanner(firstLine).useDelimiter(delimiter);
                lineScanner = new Scanner(scanner.nextLine()).useDelimiter(delimiter);
                stringJoiner = new StringJoiner(delimiter);
                while (keyScanner.hasNext()) {
                    data.replace(keyScanner.next(), lineScanner.next());
                }
                for (String value : data.values()) {
                    stringJoiner.add(value);
                }
                writer.println(stringJoiner);
                keyScanner.close();
                lineScanner.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (!Files.isRegularFile(Paths.get(argsName.get("path")))) {
            throw new IllegalArgumentException(String.format("Key \"%s\" is not a filepath", argsName.get("path")));
        }
        if (!Files.isRegularFile(Paths.get(argsName.get("out"))) && !"stdout".equals(argsName.get("out"))) {
            throw new IllegalArgumentException(String.format("Key \"%s\" is wrong", argsName.get("out")));
        }
        handle(argsName);
    }
}
