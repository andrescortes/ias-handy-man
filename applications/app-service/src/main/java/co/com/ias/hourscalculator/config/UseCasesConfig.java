package co.com.ias.hourscalculator.config;

import co.com.ias.hourscalculator.model.reportservicemodel.gateways.ReportServiceHoursWorkedGateway;
import co.com.ias.hourscalculator.model.reportservicemodel.gateways.ReportServiceRepository;
import co.com.ias.hourscalculator.usecase.reportservice.ReportServiceUseCase;
import co.com.ias.hourscalculator.usecase.workedhourscalculate.WorkedHoursCalculateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    public ReportServiceUseCase getReportServiceUseCase(
        ReportServiceRepository serviceModelRepository) {
        return new ReportServiceUseCase(serviceModelRepository);
    }

    @Bean
    public WorkedHoursCalculateUseCase getWorkedHoursCalculateUseCase(
        ReportServiceHoursWorkedGateway reportServiceHoursWorkedGateway) {
        return new WorkedHoursCalculateUseCase(reportServiceHoursWorkedGateway);
    }

}
