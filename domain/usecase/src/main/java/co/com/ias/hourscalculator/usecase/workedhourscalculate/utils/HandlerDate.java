package co.com.ias.hourscalculator.usecase.workedhourscalculate.utils;

import co.com.ias.hourscalculator.usecase.workedhourscalculate.utils.dto.ResponseHoursCalculated;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class HandlerDate {

    public static long getDiffDates(LocalDateTime startDate, LocalDateTime endDate) {
        return ChronoUnit.SECONDS.between(startDate, endDate);
    }

    public static boolean isDawn(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.plusNanos(1).compareTo(startDate.with(LocalTime.of(0, 0))) > 0 &&
            endDate.plusNanos(1).compareTo(startDate.with(LocalTime.of(0, 0))) > 0 &&
            startDate.minusNanos(1).compareTo(startDate.with(LocalTime.of(7, 0))) < 0 &&
            endDate.minusNanos(1).compareTo(startDate.with(LocalTime.of(7, 0))) < 0
        ) {
            return true;
        }
        return false;
    }

    public static boolean isNight(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.plusNanos(1).compareTo(startDate.with(LocalTime.of(20, 0))) > 0 &&
            endDate.plusNanos(1).compareTo(startDate.with(LocalTime.of(20, 0))) > 0 &&
            startDate.minusNanos(1).compareTo(startDate.with(LocalTime.of(0, 0)).plusDays(1)) < 0 &&
            endDate.minusNanos(1).compareTo(startDate.with(LocalTime.of(0, 0)).plusDays(1)) < 0
        ) {
            return true;
        }
        return false;
    }

    public static boolean isOnDay(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.plusNanos(1).compareTo(startDate.with(LocalTime.of(7, 0))) > 0 &&
            endDate.plusNanos(1).compareTo(startDate.with(LocalTime.of(7, 0))) > 0 &&
            startDate.minusNanos(1).compareTo(startDate.with(LocalTime.of(20, 0))) < 0 &&
            endDate.minusNanos(1).compareTo(startDate.with(LocalTime.of(20, 0))) < 0
        ) {
            return true;
        }
        return false;
    }

    public static boolean isNormalDay(LocalDateTime dateTime) {
        if (dateTime.plusNanos(1).compareTo(dateTime.with(LocalTime.of(7, 0))) > 0 &&
            dateTime.minusNanos(1).compareTo(dateTime.with(LocalTime.of(20, 0))) < 0
        ) {
            return true;
        }
        return false;
    }

    public static boolean isSunday(LocalDateTime dateTime) {
        return dateTime.getDayOfWeek().getValue() == 7;
    }

    public static int cumulativeTime(ResponseHoursCalculated response) {
        return response.getNormal() + response.getNight() + response.getSunday();
    }
}
