package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Jakov", "Lebedev", (byte) 23);
        userService.saveUser("Ivan", "Ivanov", (byte) 30);
        userService.saveUser("Aleksey", "Petrov", (byte) 45);
        userService.saveUser("Sergey", "Sidorov", (byte) 59);
        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
