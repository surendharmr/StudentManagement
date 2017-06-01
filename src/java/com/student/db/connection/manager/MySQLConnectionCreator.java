package com.student.db.connection.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionCreator {

    public static Connection conn = null;

    private MySQLConnectionCreator() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?autoReconnect=true",
                    "root", "MySQL123$$");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getDBConnection() {
        new MySQLConnectionCreator();
        return conn;
    }
}
