package co.com.ias.hourscalculator.jpa.reportservice;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportServiceData {

    @Id
    @Column(name = "report_service_id")
    private String reportServiceId;

    @Column(name = "technician_id", nullable = false)
    private String technicianId;

    @Column(name = "service_start_date", nullable = false)
    private LocalDateTime serviceStartDate;

    @Column(name = "service_end_date", nullable = false)
    private LocalDateTime serviceEndDate;

}
