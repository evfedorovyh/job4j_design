package ru.job4j.consolechat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String inPhrase = "";
        String outPhrase;
        boolean silent = false;
        List<String> outPhrases = readPhrases();
        List<String> log = new ArrayList<>();
        Random random = new Random();
        while (!OUT.equalsIgnoreCase(inPhrase)) {
            if (STOP.equalsIgnoreCase(inPhrase)) {
                silent = true;
            } else if (CONTINUE.equalsIgnoreCase(inPhrase)) {
                silent = false;
            }
            if (!silent && !outPhrases.isEmpty() && !inPhrase.isEmpty()) {
                outPhrase = outPhrases.get(random.nextInt(outPhrases.size() - 1));
                System.out.println(outPhrase);
                log.add(outPhrase);
            }
            inPhrase = scanner.nextLine();
            log.add(inPhrase);
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            reader.lines().forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("./data/chat_log.txt", "./data/out_phrases.txt");
        consoleChat.run();
    }
}
