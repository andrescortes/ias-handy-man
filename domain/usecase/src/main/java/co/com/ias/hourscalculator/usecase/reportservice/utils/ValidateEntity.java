package co.com.ias.hourscalculator.usecase.reportservice.utils;

import co.com.ias.hourscalculator.model.reportservicemodel.ReportService;

public class ValidateEntity {
    public static void isValid(ReportService reportService){
        if (reportService.getReportServiceId() == null
            || reportService.getTechnicianId() == null
            || reportService.getServiceStartDate() == null
            || reportService.getServiceEndDate() == null) {
            throw new IllegalArgumentException("The entered field(s) must be non-null.");
        }
    }
}
