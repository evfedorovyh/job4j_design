package ru.job4j.spammer;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private final Properties config;
    private final String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines().map(s -> s.split(";"))
                    .peek(s -> {
                        if (s.length != 2 || s[0].isEmpty() || s[1].isEmpty()) {
                            throw new IllegalArgumentException(String.format("Wrong data format in file '%s'", dump));
                        }
                    })
                    .forEach(s -> users.add(new User(s[0], s[1])));
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "CREATE TABLE IF NOT EXISTS %s (%s, %s, %s);",
                        "users",
                        "id SERIAL PRIMARY KEY",
                        "name TEXT",
                        "email VARCHAR(100)"
                );
                statement.execute(sql);
            }
            for (User user : users) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO users(name, email) VALUES (?, ?)")) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("app_sp.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, "data/dump.txt");
        dataBase.save(dataBase.load());
    }
}
