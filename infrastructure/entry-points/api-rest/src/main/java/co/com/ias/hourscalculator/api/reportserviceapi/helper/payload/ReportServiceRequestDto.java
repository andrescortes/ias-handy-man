package co.com.ias.hourscalculator.api.reportserviceapi.helper.payload;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class ReportServiceRequestDto {
    @NotNull
    private String reportServiceId;
    @NotNull
    private String technicianId;
    @NotNull
    @FutureOrPresent
    private LocalDateTime serviceStartDate;
    @NotNull
    @Future
    private LocalDateTime serviceEndDate;
}
