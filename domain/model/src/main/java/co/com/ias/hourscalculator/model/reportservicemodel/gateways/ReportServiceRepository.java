package co.com.ias.hourscalculator.model.reportservicemodel.gateways;

import co.com.ias.hourscalculator.model.reportservicemodel.ReportService;
import java.util.List;

public interface ReportServiceRepository {
    ReportService saveReportService(ReportService reportService);
    List<ReportService> getAllReportService();
}
