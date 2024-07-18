package org.openjfx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ConnectorDb {
    Configs conf = new Configs();
    Statement statement;
    PreparedStatement insert;

    public void setCon () { //connection to database with dbName
        try {
            Connection con = DriverManager.getConnection(conf.dbAddress,conf.dbUserName,conf.dbPassword);
            statement = con.createStatement();
            insert = con.prepareStatement("INSERT INTO test_result(date, name, result, log) VALUES (?, ?, ?, ?)");
        } catch (Exception e) {
            System.out.println("Failed to connect to the database");
        }
    }
}
