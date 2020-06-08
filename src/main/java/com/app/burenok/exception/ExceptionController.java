package com.app.burenok.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ContactIdNotFoundException.class)
    public ResponseEntity<Object> handleContactIdNotFoundException(
            ContactIdNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Contact not found");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ExceptionJSONInfo handleOtherException(Exception ex) {
        return ExceptionJSONInfo.of()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .error("Server error.")
                .errorMessage(ex.getMessage())
                .create();
    }

}
