package org.openjfx;

public class ResultToDb {

    ConnectorDb connectorDb = new ConnectorDb(); //new object dbConnector

    public void insertToDb(String date, String name, String result, String log) {
        try {
            connectorDb.setCon();
            connectorDb.insert.setString(1, date);
            connectorDb.insert.setString(2, name);
            connectorDb.insert.setString(3, result);
            connectorDb.insert.setString(4, log);
            connectorDb.insert.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
