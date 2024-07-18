package org.openjfx;

import java.time.LocalDate;
import java.time.ZoneId;

public class CurrentDate {
    String today = LocalDate.now(ZoneId.of("Europe/Moscow")).toString();
}