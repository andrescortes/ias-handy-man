package co.com.ias.hourscalculator.api.reportserviceapi.helper;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiErrorTemplate {

    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;

    public ApiErrorTemplate(HttpStatus status, LocalDateTime timestamp, String message) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
    }

}
