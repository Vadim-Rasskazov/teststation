package org.openjfx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ErrorLogDb {
    private final IntegerProperty number;
    private final StringProperty log;

    ErrorLogDb(int number, String log) {
        this.number = new SimpleIntegerProperty(number);
        this.log = new SimpleStringProperty(log);
    }

    public int getNumber() {
        return number.get();
    }

    public IntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public String getLog() {
        return log.get();
    }

    public StringProperty logProperty() {
        return log;
    }

    public void setLog(String log) {
        this.log.set(log);
    }
}
