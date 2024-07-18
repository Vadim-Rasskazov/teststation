package org.openjfx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ResultFromDb {
    private final IntegerProperty id;
    private final StringProperty date;
    private final StringProperty name;
    private final StringProperty result;
    private final StringProperty log;

    ResultFromDb(int id, String date, String name, String result, String log) {
        this.id = new SimpleIntegerProperty(id);
        this.date = new SimpleStringProperty(date);
        this.name = new SimpleStringProperty(name);
        this.result = new SimpleStringProperty(result);
        this.log = new SimpleStringProperty(log);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty dateProperty() {
        return date;
    }

    public StringProperty nameProperty() {return name;}

    public StringProperty resultProperty() {return result;}

    public StringProperty logProperty() {return log;}
}
