package com.example.demo.domain.models.value.objects.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import static com.example.demo.domain.models.value.objects.utils.ConstantsValueObjects.DATE_FORMAT_PATTERN;

public class DateUtils {

    public static String format(LocalDateTime date) {
        if (DATE_FORMAT_PATTERN == null || DATE_FORMAT_PATTERN.isEmpty()) {
            return date.toString();
        }
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        return formatter.format(date);
    }
}
