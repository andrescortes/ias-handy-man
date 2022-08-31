package co.com.ias.hourscalculator.usecase.workedhourscalculate.utils.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseHoursCalculated {
    private int secondsAtNormalDay;
    private int secondsOvertime;
    private int secondsAtNight;
    private int secondsOvertimeNight;
    private int secondsSunday;
    private int secondsOvertimeSunday;

}
