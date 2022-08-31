package co.com.ias.hourscalculator.usecase.reportservice;

import co.com.ias.hourscalculator.model.reportservicemodel.ReportService;
import co.com.ias.hourscalculator.model.reportservicemodel.gateways.ReportServiceRepository;
import co.com.ias.hourscalculator.usecase.reportservice.utils.ValidateDates;
import co.com.ias.hourscalculator.usecase.reportservice.utils.ValidateEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReportServiceUseCase {

    private final ReportServiceRepository repository;

    public ReportService saveReportServiceModel(ReportService reportService) {
        ValidateEntity.isValid(reportService);
        ValidateDates.dateValidateStartDateAndEndDate(reportService.getServiceStartDate(),
            reportService.getServiceEndDate());
        return repository.saveReportService(reportService);
    }

    public List<ReportService> getReportServicesModels() {
        return repository.getAllReportService();
    }
}
