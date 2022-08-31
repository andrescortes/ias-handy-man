package co.com.ias.hourscalculator.jpa.utils;

import co.com.ias.hourscalculator.model.reportservicemodel.ReportService;
import java.time.LocalDateTime;
import java.util.List;

public class FactoryReportService {

    public static List<ReportService> divideReportServiceEachWeek(ReportService reportService) {

        int weekBasedDateToSunday = DateHandler.getWeekBasedDate(
            reportService.getServiceStartDate());//monday week
        int weekBasedDateToMonday = DateHandler.getWeekBasedDate(
            reportService.getServiceEndDate());//sunday week

        LocalDateTime dateSundayTimeZero = DateHandler.lastDayOfWeek((long) weekBasedDateToSunday);
        LocalDateTime dateMondayTimeZero = DateHandler.firstDayOfWeek((long) weekBasedDateToMonday);

        ReportService serviceDataToLastDayOfWeek = new ReportService();
        serviceDataToLastDayOfWeek.setReportServiceId(reportService.getReportServiceId());
        serviceDataToLastDayOfWeek.setTechnicianId(reportService.getTechnicianId());
        serviceDataToLastDayOfWeek.setServiceStartDate(reportService.getServiceStartDate());
        serviceDataToLastDayOfWeek.setServiceEndDate(dateSundayTimeZero.withNano(0));

        ReportService serviceDataToFirstDayOfWeek = new ReportService();
        serviceDataToFirstDayOfWeek.setReportServiceId(reportService.getReportServiceId() + ".1");
        serviceDataToFirstDayOfWeek.setTechnicianId(reportService.getTechnicianId());
        serviceDataToFirstDayOfWeek.setServiceStartDate(dateMondayTimeZero);
        serviceDataToFirstDayOfWeek.setServiceEndDate(reportService.getServiceEndDate());

        return List.of(serviceDataToLastDayOfWeek, serviceDataToFirstDayOfWeek);
    }

}
