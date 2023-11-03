package org.openjfx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StatDb {
    private final StringProperty period;
    private final IntegerProperty total;
    private final IntegerProperty count;
    private final StringProperty name;

    StatDb(String period, int total, int count, String name) {
        this.period = new SimpleStringProperty(period);
        this.total = new SimpleIntegerProperty(total);
        this.count = new SimpleIntegerProperty(count);
        this.name = new SimpleStringProperty(name);
    }

    public String getPeriod() {
        return period.get();
    }

    public StringProperty periodProperty() {
        return period;
    }

    public void setPeriod(String period) {
        this.period.set(period);
    }

    public int getTotal() {
        return total.get();
    }

    public IntegerProperty totalProperty() {
        return total;
    }

    public void setTotal(int total) {
        this.total.set(total);
    }

    public int getCount() {
        return count.get();
    }

    public IntegerProperty countProperty() {
        return count;
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
