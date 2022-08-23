package co.com.ias.hourscalculator.api.reportserviceapi.helper.handler;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ApiResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();

        List<ApiValidationError> subErrors = new ArrayList<>();
        fieldErrors.forEach(error -> subErrors.add(
            new ApiValidationError(error.getField(), error.getDefaultMessage())));

        ApiErrorTemplate apiErrorTemplate = new ApiErrorTemplate(HttpStatus.BAD_REQUEST,
            "Arguments are not valid!");
        apiErrorTemplate.setFieldErrors(subErrors);
        return buildResponseEntity(apiErrorTemplate);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(
        IllegalArgumentException exception) {
        ApiErrorTemplate apiErrorTemplate = new ApiErrorTemplate(HttpStatus.BAD_REQUEST,
            exception.getMessage());
        return buildResponseEntity(apiErrorTemplate);
    }

    @ExceptionHandler(DateTimeException.class)
    protected ResponseEntity<Object> handleDateTimeParseException(DateTimeException e) {
        ApiErrorTemplate apiErrorTemplate = new ApiErrorTemplate(HttpStatus.BAD_REQUEST,
            e.getMessage());
        return buildResponseEntity(apiErrorTemplate);
    }


    private ResponseEntity<Object> buildResponseEntity(ApiErrorTemplate apiErrorTemplate) {
        return new ResponseEntity<>(apiErrorTemplate, apiErrorTemplate.getStatus());
    }
}
