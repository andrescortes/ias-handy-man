package co.com.ias.hourscalculator.jpa.utils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class DateHandler {

    public static List<LocalDateTime> getRangeDateByWeekYear(Long weekYear) {
        LocalDateTime firstDayOfWeek = firstDayOfWeek(weekYear);
        LocalDateTime lastDayOfWeek = lastDayOfWeek(weekYear);
        return List.of(firstDayOfWeek.with(LocalTime.of(0, 0, 0)), lastDayOfWeek);
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

}
