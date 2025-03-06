package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.job4j.io.Search.search;

public class Zip {
    public void packFiles(List<Path> sources, File target) {

        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkArgs(ArgsName argsName) {
        if (!Files.isDirectory(Path.of(argsName.get("d")))) {
            throw new IllegalArgumentException(String.format("This key: '%s' is not directory", argsName.get("d")));
        }
        if (!argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException(String.format("This key: '%s' is not extension", argsName.get("e")));
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("This key: '%s' has wrong extension", argsName.get("o")));
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        Zip zip = new Zip();
        zip.checkArgs(argsName);
        Path start = Paths.get(argsName.get("d"));
        List<Path> sources = search(start, path -> !path.toFile().getName().endsWith(argsName.get("e")));
        zip.packFiles(sources, Path.of(argsName.get("o")).toFile());
    }
}
