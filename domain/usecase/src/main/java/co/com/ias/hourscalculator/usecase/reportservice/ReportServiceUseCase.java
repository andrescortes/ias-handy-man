package co.com.ias.hourscalculator.usecase.reportservice;

import co.com.ias.hourscalculator.model.reportservicemodel.ReportService;
import co.com.ias.hourscalculator.model.reportservicemodel.gateways.ReportServiceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReportServiceUseCase {

    private final ReportServiceRepository repository;

    public ReportService saveReportServiceModel(ReportService reportService) {
        if(reportService.getReportServiceId() == null ){
            throw new IllegalArgumentException("Invalid Service id");
        }
        return repository.saveReportService(reportService);
    }

    public List<ReportService> getReportServicesModels() {
        return repository.getAllReportService();
    }
}
