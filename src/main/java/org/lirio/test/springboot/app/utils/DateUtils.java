package org.lirio.test.springboot.app.utils;

import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static final String PATTERN = "yyyy-MM-dd-HH.mm.ss";


    private DateUtils() {
        throw new IllegalStateException("DateUtils class");
    }

    public static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(PATTERN);
    }

}
