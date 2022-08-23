package co.com.ias.hourscalculator.model.reportservicemodel;


import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ReportService {

    private String reportServiceId;
    private String technicianId;
    private LocalDateTime serviceStartDate;
    private LocalDateTime serviceEndDate;



}

