package com.dilki.classmanagementbe.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {
    public Connection Connect() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/classmanagement? user=root&password=sara1999");
        return conn;
    }

}
