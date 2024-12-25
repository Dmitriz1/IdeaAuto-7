package ru.netology.mode;

public class DataHelper {
    public static User getValidUser() {
        return new User("vasya", "qwerty123");
    }

    public static User getInvalidUser() {
        return new User("petya", "qwerty123");
    }
}
