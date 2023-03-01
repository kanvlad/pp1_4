package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String HOST = "jdbc:postgresql://localhost:5432/pp";
    private static final String USERNAME = "ppuser";
    private static final String PASS = "pppass";

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String DIALECT = "org.hibernate.dialect.PostgreSQLDialect";
    private static SessionFactory SESSIONFACTORY;


    public static Connection getConnection() {

        try {

            return DriverManager.getConnection(HOST, USERNAME, PASS);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }


    public SessionFactory getSessionFactory(){

        if(SESSIONFACTORY == null){

            Properties properties = new Properties();
            properties.put(Environment.URL, HOST);
            properties.put(Environment.USER, USERNAME);
            properties.put(Environment.PASS, PASS);
            properties.put(Environment.DIALECT, DIALECT);
            properties.put(Environment.DRIVER, DRIVER);

            Configuration configuration = new Configuration();
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            SESSIONFACTORY = configuration.buildSessionFactory(serviceRegistry);

        }

        return SESSIONFACTORY;

    }

}
