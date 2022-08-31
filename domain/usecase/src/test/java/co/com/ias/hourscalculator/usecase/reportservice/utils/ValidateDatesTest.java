package co.com.ias.hourscalculator.usecase.reportservice.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

class ValidateDatesTest {

    @Test
    void dateValidateStartDateAndEndDateThrowsException() {
        LocalDateTime startDate = LocalDateTime.now().with(LocalTime.of(12,0,0));
        LocalDateTime endDate = LocalDateTime.now().with(LocalTime.of(11,59,59));

        assertThrows(IllegalArgumentException.class,()->ValidateDates.dateValidateStartDateAndEndDate(startDate,endDate),"Check the date entry of the fields.");
    }

    @Test
    void validDateStartAndEnd() {
        LocalDateTime startDate = LocalDateTime.now().with(LocalTime.of(11,59,59));
        LocalDateTime endDate = LocalDateTime.now().with(LocalTime.of(12,0,0));

        assertDoesNotThrow(()-> ValidateDates.dateValidateStartDateAndEndDate(startDate,endDate));
    }
}
