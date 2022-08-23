package co.com.ias.hourscalculator.config;

import co.com.ias.hourscalculator.model.reportservicemodel.gateways.ReportServiceRepository;
import co.com.ias.hourscalculator.usecase.reportservice.ReportServiceUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/*@ComponentScan(basePackages = "co.com.ias.hourscalculator.usecase",
    includeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
    },
    useDefaultFilters = false)*/
public class UseCasesConfig {

    @Bean
    public ReportServiceUseCase getReportServiceUseCase(
        ReportServiceRepository serviceModelRepository) {
        return new ReportServiceUseCase(serviceModelRepository);
    }

}
