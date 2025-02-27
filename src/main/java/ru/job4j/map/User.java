package ru.job4j.map;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        Calendar birthday = Calendar.getInstance();
        User userOne = new User("Ivan", 2, birthday);
        User userTwo = new User("Ivan", 2, birthday);
        map.put(userOne, new Object());
        map.put(userTwo, new Object());
        System.out.println(map.toString());
    }
}
