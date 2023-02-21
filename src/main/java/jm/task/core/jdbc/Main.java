package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl service = new UserServiceImpl();

        service.createUsersTable();

        service.saveUser("Vladimir", "Canarian", (byte) 36);
        service.saveUser("Name 1", "lastname 1", (byte) 25);
        service.saveUser("name 2", "Last name 2", (byte) 45);
        service.saveUser("Name 3", "Last name 3", (byte) 67);

        for (User user:
                service.getAllUsers()) {
            System.out.println(user);
        }


        service.cleanUsersTable();

        //service.dropUsersTable();


        // реализуйте алгоритм здесь
    }
}
