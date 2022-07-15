package com.student.userService.Utils;


import com.student.userService.Dao.User;

public class LocalThread {
    private static final ThreadLocal<User> LOCAL = new ThreadLocal<>();

    private LocalThread() {
    }

    public static void set(User user) {
        LOCAL.set(user);
    }

    public static User get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }
}