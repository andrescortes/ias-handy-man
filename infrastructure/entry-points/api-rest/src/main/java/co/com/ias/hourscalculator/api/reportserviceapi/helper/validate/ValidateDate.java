package co.com.ias.hourscalculator.api.reportserviceapi.helper.validate;

import java.time.LocalDateTime;


public class ValidateDate {

    public static boolean dateValidateStartDateAndEndDate(LocalDateTime startDate,
        LocalDateTime endDate) {
        int diffBetweenDates = startDate.compareTo(endDate);
        if (diffBetweenDates > 0) {
            return false;
        } else {
            return diffBetweenDates < 0;
        }
    }


}
