package co.com.ias.hourscalculator.usecase.workedhourscalculate;

import co.com.ias.hourscalculator.model.reportservicemodel.ReportService;
import co.com.ias.hourscalculator.model.reportservicemodel.gateways.ReportServiceHoursWorkedGateway;
import co.com.ias.hourscalculator.usecase.workedhourscalculate.utils.CalculatorHours;
import co.com.ias.hourscalculator.usecase.workedhourscalculate.utils.dto.ResponseHoursCalculated;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkedHoursCalculateUseCase {

    private final ReportServiceHoursWorkedGateway reportServiceHoursWorkedGateway;

    public ResponseHoursCalculated getHoursWorkedByTechnician(String technicianId, Long weekYear) {
        List<ReportService> reportServiceList = reportServiceHoursWorkedGateway.getTechnicianAndWeekYear(
            technicianId, weekYear);
        List<ReportService> reportServiceMonday = reportServiceHoursWorkedGateway.getTechnicianAndWeekYearToMonday(
            technicianId, weekYear);

        if ((reportServiceList == null || reportServiceList.isEmpty())
            && reportServiceMonday.isEmpty()) {
            return null;
        }

        Long secondsToMonday = CalculatorHours.getSecondsToMonday(reportServiceMonday);
        int secondsOfDayNormal = CalculatorHours.getSecondsNormalDay(reportServiceList);
        int secondsOfNight = CalculatorHours.getSecondsNight(reportServiceList);
        Long hoursToSunday = CalculatorHours.getSecondsToSunday(reportServiceList);
        if (secondsToMonday != 0) {
            if (secondsOfNight == 0) {
                secondsOfDayNormal += secondsToMonday;
            } else {
                secondsOfNight += secondsToMonday;
            }
        }
        return ResponseHoursCalculated.builder()
            .secondsAtNormalDay(secondsOfDayNormal)
            .secondsAtNight(secondsOfNight)
            .secondsSunday(Math.toIntExact(hoursToSunday))
            .secondsOvertime(CalculatorHours.getOvertime(secondsOfDayNormal))
            .secondsOvertimeNight(CalculatorHours.getOvertime(secondsOfNight))
            .secondsOvertimeSunday(CalculatorHours.getOvertimeSunday(secondsOfDayNormal,
                Math.toIntExact(hoursToSunday)))
            .build();
    }
}
