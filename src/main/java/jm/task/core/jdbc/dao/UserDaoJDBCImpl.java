package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String createTable = "CREATE TABLE IF NOT EXISTS users" +
            "(id serial primary key, " +
            "name VARCHAR(255), " +
            "lastName VARCHAR(255)," +
            "age INTEGER)";


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (PreparedStatement statement = Util.getConnection().prepareStatement(createTable)) {

            statement.execute();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement statement = Util.getConnection().prepareStatement("DROP TABLE IF EXISTS users;")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = Util.getConnection()
                .prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?,?,?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = Util.getConnection()
                .prepareStatement("DELETE FROM users WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {

        try(
                ResultSet resultSet = Util.getConnection().createStatement().executeQuery("SELECT * FROM users")
        ) {

            List<User> users = new ArrayList<>();

            while (resultSet.next()){
                User user = new User();
                user.setAge(resultSet.getByte("age"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setId(resultSet.getLong("id"));

                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void cleanUsersTable() {
        try(PreparedStatement statement = Util.getConnection().prepareStatement("TRUNCATE TABLE users")) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
