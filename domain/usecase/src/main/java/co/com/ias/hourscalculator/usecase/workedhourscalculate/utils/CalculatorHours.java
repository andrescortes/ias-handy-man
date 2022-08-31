package co.com.ias.hourscalculator.usecase.workedhourscalculate.utils;

import co.com.ias.hourscalculator.model.reportservicemodel.ReportService;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Predicate;

public class CalculatorHours {

    public static final int TWENTY_HOURS_TO_SECONDS = 72000;
    public static final int SEVEN_HOURS_TO_SECONDS = 25200;
    public static final int FORTY_EIGHT_HOURS_TO_SECONDS = 172800;

    public static int getSecondsNormalDay(List<ReportService> reportServices) {

        int day = 6;
        int sum = 0;
        Predicate<ReportService> isValid = reportService -> {
            LocalDateTime startDate = reportService.getServiceStartDate();
            LocalDateTime endDate = reportService.getServiceEndDate();
            if (startDate.getDayOfWeek().getValue() == endDate.getDayOfWeek().getValue()) {
                if (hoursToSeconds(startDate) >= SEVEN_HOURS_TO_SECONDS
                    && hoursToSeconds(endDate) <= TWENTY_HOURS_TO_SECONDS) {
                    return true;
                }
            }
            return false;
        };

        return calculateByDays(reportServices, day, sum, isValid);
    }

    public static int getSecondsNight(List<ReportService> reportServices) {
        int day = 6;
        int sum = 0;

        Predicate<ReportService> isValid = reportService -> {
            LocalDateTime startDate = reportService.getServiceStartDate();
            LocalDateTime endDate = reportService.getServiceEndDate();
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
        };

        return calculateByDays(reportServices, day, sum, isValid);
    }

    public static Long getSecondsToSunday(List<ReportService> reportServices) {
        Predicate<ReportService> isValidToSunday = reportService ->
            (reportService.getServiceStartDate()
                .compareTo(reportService.getServiceStartDate().with(LocalTime.of(0, 0, 0))) == 0
                || reportService.getServiceStartDate()
                .compareTo(reportService.getServiceStartDate().with(LocalTime.of(0, 0, 0))) > 0
            )
                &&
                (
                    reportService.getServiceEndDate()
                        .compareTo(reportService.getServiceEndDate()
                            .with(LocalTime.of(23, 59, 59))) == 0
                        || reportService.getServiceEndDate()
                        .compareTo(reportService.getServiceEndDate()
                            .with(LocalTime.of(23, 59, 59))) < 0
                );
        return reportServices.stream()
            .filter(r -> r.getServiceStartDate().getDayOfWeek().getValue() == 7)
            .filter(isValidToSunday)
            .map(t -> ChronoUnit.SECONDS.between(t.getServiceStartDate(), t.getServiceEndDate()))
            .reduce(0L, Long::sum);
    }

    public static Long getSecondsToMonday(List<ReportService> reportServices) {
        Predicate<ReportService> isValidToSunday = reportService ->
            (reportService.getServiceStartDate()
                .compareTo(reportService.getServiceStartDate().with(LocalTime.of(0, 0, 0))) == 0
                || reportService.getServiceStartDate()
                .compareTo(reportService.getServiceStartDate().with(LocalTime.of(0, 0, 0))) > 0
            )
                &&
                (
                    reportService.getServiceEndDate()
                        .compareTo(reportService.getServiceEndDate()
                            .with(LocalTime.of(7, 0, 0))) == 0
                        || reportService.getServiceEndDate()
                        .compareTo(reportService.getServiceEndDate()
                            .with(LocalTime.of(7, 0, 0))) < 0
                );
        return reportServices.stream()
            .filter(r -> r.getServiceStartDate().getDayOfWeek().getValue() == 1)
            .filter(isValidToSunday)
            .map(t -> ChronoUnit.SECONDS.between(t.getServiceStartDate(), t.getServiceEndDate()))
            .reduce(0L, Long::sum);
    }

    private static int calculateByDays(List<ReportService> reportServices, int day, int sum,
        Predicate<ReportService> isValid) {

        for (int i = 0; i < day; i++) {

            int finalI = i;
            sum += reportServices.stream()
                .filter(t -> t.getServiceStartDate().getDayOfWeek().getValue() == finalI + 1)
                .filter(isValid).map(
                    report -> ChronoUnit.SECONDS.between(report.getServiceStartDate(),
                        report.getServiceEndDate()))
                .reduce(0L, Long::sum);
        }
        return sum;
    }

    public static int getOvertime(int totalHoursNormal) {
        if (totalHoursNormal > FORTY_EIGHT_HOURS_TO_SECONDS) {
            return totalHoursNormal - FORTY_EIGHT_HOURS_TO_SECONDS;
        }
        return 0;
    }

    public static int getOvertimeSunday(int totalHoursNormal, int totalHoursSunday) {
        if (totalHoursNormal > FORTY_EIGHT_HOURS_TO_SECONDS) {
            if (totalHoursSunday > 0) {
                return totalHoursSunday;
            }
        }
        return 0;
    }


    public static int hoursToSeconds(LocalDateTime dateTime) {
        return dateTime.toLocalTime().toSecondOfDay();
    }

}
