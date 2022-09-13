package co.com.ias.hourscalculator.jpa.reportservice;

import co.com.ias.hourscalculator.jpa.entity.ReportServiceData;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ReportServiceDataRepository extends CrudRepository<ReportServiceData, String>,
    QueryByExampleExecutor<ReportServiceData> {

    //@Query("select (count(r) > 0) from ReportServiceData r where  r.serviceStartDate between ?1  and r.serviceEndDate = ?2")
    //@Query(value = "select count(*) > 0 from reportservicedata where ((service_start_date between ?1 and ?2) or (service_end_date between ?1 and ?2)) and technician_id=?3", nativeQuery = true)
    @Query(value="select count(*) > 0 from reportservicedata where service_start_date < ?2 and service_end_date > ?1 and technician_id=?3", nativeQuery = true)
    boolean getInstanceBetweenTwoDates(LocalDateTime serviceStartDate, LocalDateTime serviceEndDate,
        String technicianId);

    Optional<ReportServiceData> getReportServiceDataByReportServiceId(String reportServiceId);


}
