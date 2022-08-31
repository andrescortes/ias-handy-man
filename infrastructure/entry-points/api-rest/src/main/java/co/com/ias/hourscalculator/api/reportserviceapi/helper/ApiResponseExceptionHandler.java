package co.com.ias.hourscalculator.api.reportserviceapi.helper;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApiResponseExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(
        IllegalArgumentException exception) {
        ApiErrorTemplate apiErrorTemplate = new ApiErrorTemplate(HttpStatus.BAD_REQUEST,
            LocalDateTime.now(),exception.getMessage());
        return buildResponseEntity(apiErrorTemplate);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiErrorTemplate apiErrorTemplate) {
        return new ResponseEntity<>(apiErrorTemplate, apiErrorTemplate.getStatus());
    }
}
