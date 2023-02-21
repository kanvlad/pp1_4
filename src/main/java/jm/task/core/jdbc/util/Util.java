package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String HOST = "jdbc:postgresql://localhost:5432/pp";
    private static final String USERNAME = "ppuser";
    private static final String PASS = "pppass";


    public static Connection getConnection() {

        try {

            return DriverManager.getConnection(HOST, USERNAME, PASS);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
}
