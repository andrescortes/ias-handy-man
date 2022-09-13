package co.com.ias.hourscalculator.jpa.reportservice;

import co.com.ias.hourscalculator.jpa.config.helper.AdapterOperations;
import co.com.ias.hourscalculator.jpa.entity.ReportServiceData;
import co.com.ias.hourscalculator.jpa.utils.DateHandler;
import co.com.ias.hourscalculator.jpa.utils.FactoryReportService;
import co.com.ias.hourscalculator.model.reportservicemodel.ReportService;
import co.com.ias.hourscalculator.model.reportservicemodel.gateways.ReportServiceRepository;
import java.util.List;
import java.util.Optional;
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

        Optional<ReportServiceData> optionalReportServiceData = super.repository.getReportServiceDataByReportServiceId(
            reportService.getReportServiceId());
        //update report
        if (optionalReportServiceData.isPresent()) {
            //equals day
            if (DateHandler.isDateSameWeek(reportService.getServiceStartDate(),
                reportService.getServiceEndDate())) {
                return super.save(reportService);
            }
            //not equals day
            List<ReportService> reportServices = FactoryReportService.divideReportServiceEachWeek(
                reportService);
            return super.saveAllEntities(reportServices).get(0);
        }
        if (super.repository.getInstanceBetweenTwoDates(reportService.getServiceStartDate(),
            reportService.getServiceEndDate(), reportService.getTechnicianId())) {
            throw new IllegalArgumentException(
                "The date selected have been used by other report service.");
        }
        if (!DateHandler.isDateSameWeek(reportService.getServiceStartDate(),
            reportService.getServiceEndDate())) {
            List<ReportService> reportServices = FactoryReportService.divideReportServiceEachWeek(
                reportService);
            return super.saveAllEntities(reportServices).get(0);
        }
        return super.save(reportService);
    }

    @Override
    public List<ReportService> getAllReportService() {
        return super.findAll();
    }

}
