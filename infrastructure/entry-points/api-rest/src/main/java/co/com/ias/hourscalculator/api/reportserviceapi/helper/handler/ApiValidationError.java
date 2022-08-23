package co.com.ias.hourscalculator.api.reportserviceapi.helper.handler;

import lombok.Data;

@Data
public class ApiValidationError {
    private String field;
    private String message;

    ApiValidationError(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
