/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j.da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DSLP
 */
public class DataConnector {

    private Connection connection = null;

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        if (connection == null) {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String user = "root";
            String pass = "0412";
            String url = "jdbc:derby://localhost:1527/BookLibrary";
            connection = DriverManager.getConnection(url, user, pass);
            return connection;
        }
        return connection;
    }
}
