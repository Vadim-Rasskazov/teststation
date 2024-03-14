package org.openjfx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class ConnectorDb {
    Configs conf = new Configs();
    Statement statement;

    public void setCon () { //connection to database with dbName
        try {
            Connection con = DriverManager.getConnection(conf.dbAddress,conf.dbUserName,conf.dbPassword);
            statement = con.createStatement();
        } catch (Exception e) {
            System.out.println("Failed to connect to the database");
        }
    }
}
