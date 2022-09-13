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

        if (reportServiceList == null || reportServiceList.isEmpty()) {
            return null;
        }

        CalculatorHours calculatorHours = new CalculatorHours();
        ResponseHoursCalculated hoursWorked = calculatorHours.getHoursWorked(reportServiceList);
        System.out.println("hoursWorked = " + hoursWorked);
        return hoursWorked;
    }
}
