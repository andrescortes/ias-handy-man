package co.com.ias.hourscalculator.usecase.workedhourscalculate.utils;


import co.com.ias.hourscalculator.model.reportservicemodel.ReportService;
import co.com.ias.hourscalculator.usecase.workedhourscalculate.utils.dto.ResponseHoursCalculated;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class CalculatorHours {

    public static final int FORTY_EIGHT = 172800;

    public ResponseHoursCalculated getHoursWorked(List<ReportService> list) {
        ResponseHoursCalculated response = new ResponseHoursCalculated(0, 0, 0, 0, 0, 0);
        list.forEach(report -> {
            LocalDateTime startDate = report.getServiceStartDate();
            LocalDateTime endDate = report.getServiceEndDate();
            //total time to service current
            int totalTime = (int) HandlerDate.getDiffDates(startDate, endDate);
            int isOvertime = 0;
            while (totalTime != 0) {
                int sumFieldsObj = HandlerDate.cumulativeTime(response);

                //validating that total time of service 48 hours
                // to activate flag of isOvertime
                if (sumFieldsObj == FORTY_EIGHT && isOvertime == 0) {
                    isOvertime = 1;
                }
                //reduce time of endDate one day
                //simplify to calculate
                if (sumFieldsObj + totalTime > FORTY_EIGHT && isOvertime == 0) {
                    int aux = FORTY_EIGHT - sumFieldsObj;
                    int leftDay = totalTime - aux;
                    endDate = endDate.minusSeconds(leftDay);
                }
                //to ask if the service is on sunday startDate and endDate
                if (HandlerDate.isSunday(startDate) && HandlerDate.isSunday(
                    report.getServiceEndDate().minusNanos(1))) {
                    long accum = HandlerDate.getDiffDates(startDate, endDate);
                    if (isOvertime == 1) {
                        int aux = response.getOvertimeSunday();
                        response.setOvertimeSunday((int) (aux + accum));
                    } else {
                        response.setSunday((int) (response.getSunday() + accum));
                    }
                    //decrease the accum and update startDate
                    totalTime -= accum;
                    startDate = endDate;
                    //to ask if the service between 00-07 || 07-20 || 20-00
                } else if (HandlerDate.isDawn(startDate, endDate) || HandlerDate.isOnDay(startDate,
                    endDate) || HandlerDate.isNight(
                    startDate, endDate)) {
                    long accum = HandlerDate.getDiffDates(startDate, endDate);
                    // normal day between 07-20
                    if (HandlerDate.isNormalDay(startDate)
                        && HandlerDate.isNormalDay(endDate)) {
                        if (isOvertime == 1) {
                            int aux = response.getOvertime();
                            response.setOvertime((int) (aux + accum));
                        } else {
                            response.setNormal((int) (response.getNormal() + accum));
                        }

                    } else {
                        //between 20-00
                        if (isOvertime == 1) {
                            int aux = response.getOvertimeNight();
                            response.setOvertimeNight((int) (aux + accum));
                        } else {
                            response.setNight((int) (response.getNight() + accum));
                        }
                    }
                    totalTime -= accum;
                    startDate = endDate;
                } else {
                    //to ask if service between startDate less than 07 hours
                    if (startDate.compareTo(startDate.with(LocalTime.of(7, 0))) < 0) {
                        long accum = HandlerDate.getDiffDates(startDate, startDate.toLocalDate().atTime(7, 0));
                        if (isOvertime == 1) {
                            int aux = response.getOvertimeNight();
                            response.setOvertimeNight((int) (aux + accum));
                        } else {
                            response.setNight((int) (response.getNight() + accum));
                        }
                        totalTime -= accum;
                        startDate = startDate.toLocalDate().atTime(7, 0);
                        //startDate less than 20 hours
                    } else if (startDate.compareTo(
                        startDate.with(LocalTime.of(20, 0))) < 0 ) {
                        long accum = HandlerDate.getDiffDates(startDate,startDate.toLocalDate().atTime(20, 0));
                        if (isOvertime == 1) {
                            int aux = response.getOvertime();
                            response.setOvertime((int) (aux + accum));
                        } else {
                            response.setNormal((int) (response.getNormal() + accum));
                        }

                        totalTime -= accum;
                        startDate = startDate.toLocalDate().atTime(20, 0);
                        //less than 00 hours
                    } else {
                        long accum = HandlerDate.getDiffDates(startDate, startDate.toLocalDate().atTime(0, 0).plusDays(1));
                        if (isOvertime == 1) {
                            int aux = response.getOvertimeNight();
                            response.setOvertimeNight((int) (aux + accum));
                        } else {
                            response.setNight((int) (response.getNight() + accum));
                        }
                        totalTime -= accum;
                        startDate = startDate.toLocalDate().atTime(0, 0).plusDays(1);
                    }
                }
                //if the service is greater than 48 hours
                //the flag is active, so we have reset endStart
                if (!endDate.equals(report.getServiceEndDate())) {
                    endDate = report.getServiceEndDate();
                }
            }
        });
        return response;
    }

}
