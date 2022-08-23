package co.com.ias.hourscalculator.api.reportserviceapi;


import co.com.ias.hourscalculator.api.reportserviceapi.helper.payload.ReportServiceRequestDto;
import co.com.ias.hourscalculator.api.reportserviceapi.helper.validate.ValidateDate;
import co.com.ias.hourscalculator.model.reportservicemodel.ReportService;
import co.com.ias.hourscalculator.usecase.reportservice.ReportServiceUseCase;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/report-service", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ReportServiceApi {

    private final ReportServiceUseCase useCase;

    @GetMapping
    public ResponseEntity<List<ReportService>> getAllReportServices() {
        return new ResponseEntity<>(useCase.getReportServicesModels(), HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<ReportService> addReportService(
        @RequestBody
        @DateTimeFormat(iso = ISO.DATE_TIME)
        @Valid final ReportServiceRequestDto reportServiceRequestDTO
    ) {

        boolean isValid = ValidateDate.dateValidateStartDateAndEndDate(
            reportServiceRequestDTO.getServiceStartDate(),
            reportServiceRequestDTO.getServiceEndDate());
        if (isValid) {

            ReportService reportService = ReportService.builder()
                .reportServiceId(reportServiceRequestDTO.getReportServiceId())
                .technicianId(reportServiceRequestDTO.getTechnicianId())
                .serviceStartDate(reportServiceRequestDTO.getServiceStartDate())
                .serviceEndDate(reportServiceRequestDTO.getServiceEndDate())
                .build();
            return new ResponseEntity<>(useCase.saveReportServiceModel(reportService),
                HttpStatus.CREATED);
        } else {
            throw new IllegalArgumentException("Start date must be less than end date.");
        }

    }

}
