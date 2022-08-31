package co.com.ias.hourscalculator.jpa.workedhourscalculate;

import co.com.ias.hourscalculator.jpa.entity.ReportServiceData;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ReportServiceDataWorkedHoursCalculateRepository extends
    CrudRepository<ReportServiceData, String>,
    QueryByExampleExecutor<ReportServiceData> {

    @Query(value = "select count(*) > 0 from reportservicedata where technician_id=?1", nativeQuery = true)
    boolean findByTechnicianId(String technicianId);

    @Query(value = "select * from reportservicedata where service_start_date >= ?1 and service_end_date <= ?2 and technician_id=?3",nativeQuery = true)
    List<ReportServiceData> getReportsByTechnician(LocalDateTime firstDayOfWeek,
        LocalDateTime lastDayOfWeek, String technicianId);


}
