package co.com.ias.hourscalculator.jpa.reportservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ReportServiceDataRepository extends CrudRepository<ReportServiceData, String>,
    QueryByExampleExecutor<ReportServiceData> {

}
