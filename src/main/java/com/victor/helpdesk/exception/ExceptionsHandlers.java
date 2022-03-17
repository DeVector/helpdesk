package com.victor.helpdesk.exception;

import com.victor.helpdesk.util.MessagesUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionsHandlers {

    @ExceptionHandler(BussinesException.class)
    protected ResponseEntity<ExceptionResponse> handlerSecurity(BussinesException e, HttpServletRequest request){
        ExceptionResponse exResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
                "Validation dates", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ExceptionResponse> notFoundPerson(NotFoundException e, HttpServletRequest request){
        ExceptionResponse exResponse = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(),
                "Not found Person", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionResponse> methodArgumentNotValidException(MethodArgumentNotValidException e,
                                                                                HttpServletRequest request){
        ValidationError errors = new ValidationError(HttpStatus.BAD_REQUEST.value(),
                "Erro na validação dos campos", "Validation Field", request.getRequestURI());
        for (FieldError x : e.getBindingResult().getFieldErrors()){
            errors.addErrors(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ExceptionResponse> dataIntegrityViolationException(DataIntegrityViolationException e,
                                                                                HttpServletRequest request){
        ExceptionResponse exReponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                e.getMessage(), MessagesUtils.FIELD_VALIDATION, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exReponse);
    }
}
