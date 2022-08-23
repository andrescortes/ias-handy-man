package co.com.ias.hourscalculator.api.reportserviceapi.helper.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiErrorTemplate {
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
    private List<ApiValidationError> fieldErrors;

    private ApiErrorTemplate() {
        timestamp = LocalDateTime.now();
        fieldErrors = new ArrayList<>();
    }

    ApiErrorTemplate(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }
}
