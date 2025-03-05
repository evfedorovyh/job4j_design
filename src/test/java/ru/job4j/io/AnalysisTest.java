package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {
    @Test
    void availableTest(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.print("200 10:56:01\n"
                    + "500 10:57:01\n"
                    + "400 10:58:01\n"
                    + "300 10:59:01\n"
                    + "500 11:01:02\n"
                    + "200 11:02:02\n");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> result = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::add);
        }
        assertThat("10:57:01;10:59:01;").hasToString(result.get(0));
        assertThat("11:01:02;11:02:02;").hasToString(result.get(1));
    }
}