package co.com.ias.hourscalculator.usecase.reportservice.utils;

import java.time.LocalDateTime;

public class Dates {
    public static void dateValidateStartDateAndEndDate(LocalDateTime startDate,
        LocalDateTime endDate) {
        int diffBetweenDates = startDate.compareTo(endDate);
        if (diffBetweenDates > 0) {
            throw new IllegalArgumentException(
                "Check the date entry of the fields.");
        }
    }
}
