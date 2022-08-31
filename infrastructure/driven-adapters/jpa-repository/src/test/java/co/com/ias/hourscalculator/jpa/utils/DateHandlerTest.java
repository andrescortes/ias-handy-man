package co.com.ias.hourscalculator.jpa.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class DateHandlerTest {

    @Test
    void getRangeDateByWeekYear() {
        Long week = 1L;
        String startMonday = "2022-01-03T07:00:00";
        LocalDateTime startWeekDay = LocalDateTime.parse(startMonday);
        String endSunday = "2022-01-09T23:59:59.999999999";
        LocalDateTime endWeekDay = LocalDateTime.parse(endSunday);

        List<LocalDateTime> rangeDateByWeekYear = DateHandler.getRangeDateByWeekYear(week);
        assertEquals(2, rangeDateByWeekYear.size());
        assertEquals(startWeekDay, rangeDateByWeekYear.get(0));
        assertEquals(endWeekDay, rangeDateByWeekYear.get(1));

    }

}
