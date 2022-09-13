package co.com.ias.hourscalculator.usecase.workedhourscalculate.utils.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseHoursCalculated {
    private int normal;
    private int overtime;
    private int night;
    private int overtimeNight;
    private int sunday;
    private int overtimeSunday;
}
