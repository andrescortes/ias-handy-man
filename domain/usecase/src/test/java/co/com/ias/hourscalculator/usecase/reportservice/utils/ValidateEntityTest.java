package co.com.ias.hourscalculator.usecase.reportservice.utils;

import static org.junit.jupiter.api.Assertions.*;

import co.com.ias.hourscalculator.model.reportservicemodel.ReportService;
import org.junit.jupiter.api.Test;

class ValidateEntityTest {

    @Test
    void isValid() {
        ReportService reportService = ReportService.builder()
            .build();
        assertThrows(IllegalArgumentException.class, () -> ValidateEntity.isValid(reportService),
            "The entered field(s) must be non-null.");

    }
}
