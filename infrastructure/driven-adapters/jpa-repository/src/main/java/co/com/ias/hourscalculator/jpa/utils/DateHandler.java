package co.com.ias.hourscalculator.jpa.utils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class DateHandler {

    public static final int TWENTY_HOURS_TO_SECONDS = 72000;
    public static final int SEVEN_HOURS_TO_SECONDS = 25200;
    public static final int EIGHT_HOURS_TO_SECONDS = 28800;

    public static List<LocalDateTime> getRangeDateByWeekYear(Long weekYear) {
        LocalDateTime firstDayOfWeek = firstDayOfWeek(weekYear);
        LocalDateTime lastDayOfWeek = lastDayOfWeek(weekYear);
        return List.of(firstDayOfWeek.with(LocalTime.of(7, 0, 0)), lastDayOfWeek);
    }

    public static boolean isSavingStartMonday(LocalDateTime dateTime) {
        if (dateTime.getDayOfWeek().getValue() == 1) {
            return dateTime.compareTo(dateTime.with(LocalTime.of(0, 0, 0))) == 0 ||
                (dateTime.compareTo(dateTime.with(LocalTime.of(0, 0, 0))) > 0 &&
                    dateTime.compareTo(dateTime.with(LocalTime.of(7, 0, 0))) < 0
                );
        }
        return false;
    }

    public static boolean isValidRangeHoursToSave(LocalDateTime startDate, LocalDateTime endDate) {
        //to save at sunday
        if (startDate.getDayOfWeek().getValue() == 7) {
            if ((
                startDate.compareTo(startDate.with(LocalTime.of(0, 0, 0))) > 0
                    ||
                    startDate.compareTo(startDate.with(LocalTime.of(0, 0, 0))) == 0
            )
                && (
                endDate.compareTo(endDate.with(LocalTime.of(23, 59, 59))) < 0
                    ||
                    endDate.compareTo(endDate.with(LocalTime.of(23, 59, 59))) == 0
            )) {
                return true;
            }
        }
        //to save in day normal
        if (
            (
                startDate.compareTo(startDate.with(LocalTime.of(7, 0, 0))) > 0
                    ||
                    startDate.compareTo(startDate.with(LocalTime.of(7, 0, 0))) == 0
            )
                && (
                startDate.compareTo(startDate.with(LocalTime.of(20, 0, 0))) < 0
                    ||
                    startDate.compareTo(startDate.with(LocalTime.of(20, 0, 0))) == 0
            )
                && (
                endDate.compareTo(endDate.with(LocalTime.of(20, 0, 0))) < 0
                    ||
                    endDate.compareTo(endDate.with(LocalTime.of(20, 0, 0))) == 0
            )
        ) {
            return true;
        }
        //during night
        if (startDate.getDayOfMonth() == endDate.getDayOfMonth()) {
            if (hoursToSeconds(startDate) >= TWENTY_HOURS_TO_SECONDS) {
                return true;
            }
            if (hoursToSeconds(startDate) <= SEVEN_HOURS_TO_SECONDS
                && hoursToSeconds(endDate) <= SEVEN_HOURS_TO_SECONDS) {
                return true;
            }
        } else {
            if (hoursToSeconds(startDate) >= TWENTY_HOURS_TO_SECONDS
                && hoursToSeconds(endDate) <= SEVEN_HOURS_TO_SECONDS) {
                return true;
            }
            if (hoursToSeconds(startDate) <= SEVEN_HOURS_TO_SECONDS) {
                return true;
            }
        }

        return false;
    }

    public static int hoursToSeconds(LocalDateTime dateTime) {
        return dateTime.toLocalTime().toSecondOfDay();
    }

    public static LocalDateTime firstDayOfWeek(Long weekYear) {
        //date: 2022-01-03T00:00:00 Monday
        return LocalDateTime.now()
            .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, weekYear)
            .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).toLocalDate().atStartOfDay();
    }

    public static LocalDateTime lastDayOfWeek(Long weekYear) {
        //date: 2022-01-09T59:59:59 Sunday
        return LocalDateTime.now()
            .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, weekYear)
            .with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).toLocalDate()
            .atTime(LocalTime.MAX);
    }

    public static int getWeekBasedDate(LocalDateTime time) {
        return time.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
    }

    public static boolean isDateSameWeek(LocalDateTime serviceStartDateTime,
        LocalDateTime serviceEndDate) {
        return getWeekBasedDate(serviceStartDateTime) == getWeekBasedDate(serviceEndDate);
    }

    public static boolean isValidTimeWorkedByTechnician(LocalDateTime serviceStartDateTime,
        LocalDateTime serviceEndDate) {
        long seconds = ChronoUnit.SECONDS.between(serviceStartDateTime, serviceEndDate);
        return seconds > EIGHT_HOURS_TO_SECONDS;
    }

}
