package org.openjfx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ResultDb {
    private final IntegerProperty number;
    private final StringProperty date;
    private final StringProperty result;
    private final StringProperty log;

    ResultDb(int number, String date, String result, String log) {
        this.number = new SimpleIntegerProperty(number);
        this.date = new SimpleStringProperty(date);
        this.result = new SimpleStringProperty(result);
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

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getResult() {
        return result.get();
    }

    public StringProperty resultProperty() {
        return result;
    }

    public void setResult(String result) {
        this.result.set(result);
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
