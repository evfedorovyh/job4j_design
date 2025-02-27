package ru.job4j.question;

import java.util.HashSet;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Set<User> setUser = new HashSet<>(previous);
        setUser.addAll(current);
        Set<Integer> setUserID = new HashSet<>();
        for (User user : setUser) {
            setUserID.add(user.getId());
        }
        int changed = setUser.size() - setUserID.size();
        int added = setUserID.size() - previous.size();
        int deleted = setUserID.size() - current.size();
        return new Info(added, changed, deleted);
    }
}