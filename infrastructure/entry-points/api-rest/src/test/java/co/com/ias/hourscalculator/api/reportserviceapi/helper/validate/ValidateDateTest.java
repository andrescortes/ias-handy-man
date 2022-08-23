package co.com.ias.hourscalculator.api.reportserviceapi.helper.validate;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class ValidateDateTest {

    @Test
    void dateValidateStartDateAndEndDate() {
        LocalDateTime serviceStartDate = LocalDateTime.now();
        LocalDateTime serviceEndDate = serviceStartDate.plusHours(3);
        boolean isValid = ValidateDate.dateValidateStartDateAndEndDate(serviceStartDate,
            serviceEndDate);
        assertTrue(isValid);
    }

    @Test
    void dateStartIsGreaterThenDateEnd() {
        LocalDateTime serviceStartDate = LocalDateTime.now();
        LocalDateTime serviceEndDate = serviceStartDate.minusHours(3);
        boolean isValid = ValidateDate.dateValidateStartDateAndEndDate(serviceStartDate,
            serviceEndDate);
        assertFalse(isValid);
    }
}
