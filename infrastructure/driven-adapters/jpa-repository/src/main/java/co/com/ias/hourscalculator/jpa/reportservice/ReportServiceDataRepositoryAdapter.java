package co.com.ias.hourscalculator.jpa.reportservice;

import co.com.ias.hourscalculator.jpa.config.helper.AdapterOperations;
import co.com.ias.hourscalculator.model.reportservicemodel.ReportService;
import co.com.ias.hourscalculator.model.reportservicemodel.gateways.ReportServiceRepository;
import java.util.List;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ReportServiceDataRepositoryAdapter extends
    AdapterOperations<ReportService, ReportServiceData, String, ReportServiceDataRepository> implements
    ReportServiceRepository {

    public ReportServiceDataRepositoryAdapter(ReportServiceDataRepository repository,
        ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper,
            //d -> mapper.mapBuilder(d, ReportService.ReportServiceBuilder.class).build());
            d -> mapper.map(d, ReportService.class));
    }

    @Override
    public ReportService saveReportService(ReportService reportService) {
        return super.save(reportService);
    }

    @Override
    public List<ReportService> getAllReportService() {
        return super.findAll();
    }
}
