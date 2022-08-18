package co.com.ias.hourscalculator.model.reportservicemodel;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ReportServiceModel {

    private String reportServiceModelId;
    private String technicianId;
    private LocalDate serviceStartDate;
    private LocalDate serviceEndDate;

}

