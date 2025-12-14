package ie.atu.userservice.userservice.ErrorHandling;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(MethodArgumentNotValidException.class) // Handles Invalid Data
    public ResponseEntity<List<ExceptionDetails>> showErrorDetails(MethodArgumentNotValidException mae)
    {
        List<ExceptionDetails> errorList = new ArrayList<>();
        for(FieldError fieldError : mae.getBindingResult().getFieldErrors()) {
            ExceptionDetails exceptionDetails = new ExceptionDetails();
            exceptionDetails.setFieldName(fieldError.getField());
            exceptionDetails.setFieldValue(fieldError.getDefaultMessage());
            errorList.add(exceptionDetails);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
    }

    @ExceptionHandler(DuplicateException.class) // Handles Duplicate Data
    public ResponseEntity<String> showDuplicateError(DuplicateException de)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(de.getMessage()); // CONFLICT for http error 409
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> showUserNotFound(UserNotFoundException ue)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ue.getMessage());
    }
}
