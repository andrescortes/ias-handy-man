package co.com.ias.hourscalculator.model.reportservicemodel.gateways;

import co.com.ias.hourscalculator.model.reportservicemodel.ReportService;
import java.util.List;

public interface ReportServiceHoursWorkedGateway {

    List<ReportService> getTechnicianAndWeekYear(String technicianId, Long weekYear);
    List<ReportService> getTechnicianAndWeekYearToMonday(String technicianId, Long weekYear);



}
