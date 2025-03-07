package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<String>> search = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty key = new FileProperty(Files.size(file) / 1024, file.getFileName().toString());
        search.computeIfAbsent(key, a -> new ArrayList<>()).add(file.toAbsolutePath().toString());
        return super.visitFile(file, attrs);
    }

    public void getDuplicates() {
        search.entrySet().stream()
                .filter(a -> a.getValue().size() > 1)
                .peek(e -> System.out.println(e.getKey()))
                .flatMap(a -> a.getValue().stream())
                .forEach(System.out::println);
    }
}
