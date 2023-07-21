package org.openjfx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class ConnectorDb extends Configs {
    Connection con;
    Statement statement;

    public void setCon () { //connection to database with dbName
        try {
            con = DriverManager.getConnection(dbAddress,dbUserName,dbPassword);
            statement = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
