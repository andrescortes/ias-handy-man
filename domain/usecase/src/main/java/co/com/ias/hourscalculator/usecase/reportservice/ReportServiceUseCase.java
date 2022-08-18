package co.com.ias.hourscalculator.usecase.reportservice;

import co.com.ias.hourscalculator.model.reportservicemodel.ReportServiceModel;
import co.com.ias.hourscalculator.model.reportservicemodel.gateways.ReportServiceModelRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReportServiceUseCase {

    private final ReportServiceModelRepository repository;

    public ReportServiceModel saveReportServiceModel(ReportServiceModel reportServiceModel) {
        return repository.saveReportServiceModel(reportServiceModel);
    }
}
