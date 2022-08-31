package co.com.ias.hourscalculator.jpa.workedhourscalculate;

import co.com.ias.hourscalculator.jpa.config.helper.AdapterOperations;
import co.com.ias.hourscalculator.jpa.entity.ReportServiceData;
import co.com.ias.hourscalculator.jpa.utils.DateHandler;
import co.com.ias.hourscalculator.model.reportservicemodel.ReportService;
import co.com.ias.hourscalculator.model.reportservicemodel.gateways.ReportServiceHoursWorkedGateway;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ReportServiceDataWorkedHoursCalculateRepositoryAdapter extends
    AdapterOperations<ReportService, ReportServiceData, String, ReportServiceDataWorkedHoursCalculateRepository> implements
    ReportServiceHoursWorkedGateway {

    public ReportServiceDataWorkedHoursCalculateRepositoryAdapter(
        ReportServiceDataWorkedHoursCalculateRepository repository,
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

    public boolean getTechnician(String technicianId) {
        return super.repository.findByTechnicianId(technicianId);
    }

    @Override
    public List<ReportService> getTechnicianAndWeekYear(String technicianId, Long weekYear) {
        if (!getTechnician(technicianId)) {
            return null;
            /*throw new IllegalArgumentException(
                "The technician with id " + technicianId + " could be not found.");*/
        }
        List<LocalDateTime> rangeDateByWeekYear = DateHandler.getRangeDateByWeekYear(weekYear);

        return super.repository.getReportsByTechnician(rangeDateByWeekYear.get(0),
            rangeDateByWeekYear.get(1), technicianId).stream().map(super::toEntity).collect(
            Collectors.toList());
    }

    @Override
    public List<ReportService> getTechnicianAndWeekYearToMonday(String technicianId, Long weekYear) {
        List<LocalDateTime> rangeDateByWeekYear = DateHandler.getRangeDateByWeekYear(weekYear);

        return super.repository.getReportsByTechnician(rangeDateByWeekYear.get(0).with(LocalTime.of(0,0,0)),
            rangeDateByWeekYear.get(0).with(LocalTime.of(7,0,0)), technicianId).stream().map(super::toEntity).collect(
            Collectors.toList());
    }



}
