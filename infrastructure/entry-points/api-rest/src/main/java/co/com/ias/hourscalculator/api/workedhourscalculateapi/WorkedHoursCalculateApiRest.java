package co.com.ias.hourscalculator.api.workedhourscalculateapi;


import co.com.ias.hourscalculator.usecase.workedhourscalculate.WorkedHoursCalculateUseCase;
import co.com.ias.hourscalculator.usecase.workedhourscalculate.utils.dto.ResponseHoursCalculated;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/api/hours-calculate", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class WorkedHoursCalculateApiRest {

    private final WorkedHoursCalculateUseCase workedHoursCalculateUseCase;

    @GetMapping("/technician/{technicianId}/week/{week}")
    public ResponseEntity<?> getHours(@PathVariable final String technicianId,
        @PathVariable final Long week) {
        ResponseHoursCalculated hoursWorkedByTechnician = workedHoursCalculateUseCase
            .getHoursWorkedByTechnician(technicianId, week);
        if (hoursWorkedByTechnician == null) {
            return new ResponseEntity<>(0, HttpStatus.OK);
        }
        return new ResponseEntity<>(hoursWorkedByTechnician, HttpStatus.OK);
    }
}


