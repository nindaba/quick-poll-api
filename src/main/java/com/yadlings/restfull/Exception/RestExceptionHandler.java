package com.yadlings.restfull.Exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@ControllerAdvice
public class RestExceptionHandler{ //extends ResponseEntityExceptionHandler{
    @Autowired
    private MessageSource messageSource;
    @ExceptionHandler(ResourceException.NotFound.class)
    public ResponseEntity<?> handleResourceNotFoundException(
            ResourceException.NotFound resourceException,
            HttpServletRequest httpServletRequest)
    {
        RestException exception = new RestException
                .Builder()
                .setTitle("Recourse Not Found")
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setMessage(resourceException.getMessage())
                .setTimestamp(new Date().getTime())
                .setDevDetails(resourceException.getClass().getName())
                .setPath(httpServletRequest.getServletPath())
                .build();
        return new ResponseEntity<>(exception,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceException.AlreadyExist.class)
    public ResponseEntity<?> alreadyHandle(
            ResourceException.AlreadyExist alreadyExist,
            HttpServletRequest request
            )
    {
        RestException questionAlreadyExist = new RestException
                .Builder()
                .setDevDetails(alreadyExist.getClass().getName())
                .setMessage(alreadyExist.getMessage())
                .setStatus(HttpStatus.CONFLICT.value())
                .setTimestamp(new Date().getTime())
                .setTitle("Already Exist")
                .setPath(request.getServletPath())
                .build();
        return new ResponseEntity<>(questionAlreadyExist, HttpStatus.CONFLICT);
    }
//    @Override
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException argumentNotValidException, HttpHeaders headers, HttpStatus status, WebRequest request
            )
    {
        Map<String, ValidationError> errorMap = argumentNotValidException
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        keyMapper -> keyMapper.getField(),
                        valueMapper -> new ValidationError(
                                valueMapper.getCode(),
                                messageSource.getMessage(valueMapper, Locale.ENGLISH))
                        )
                );
        RestException questionAlreadyExist = new RestException
                .Builder()
                .setDevDetails(argumentNotValidException.getClass().getName())
                .setMessage("The Data You Provided Is Not Valid")
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setTimestamp(new Date().getTime())
                .setTitle("Validation Failed")
                .setErrors(errorMap)
                .setPath(request.getContextPath())
                .build();
        return new ResponseEntity<>(questionAlreadyExist, HttpStatus.BAD_REQUEST);

    }
//    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RestException failedToConvert = new RestException
                .Builder()
                .setDevDetails(ex.getClass().getName())
                .setMessage(ex.getMessage())
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setTimestamp(new Date().getTime())
                .setTitle("Failed To Convert")
                .setPath(request.getContextPath())
                .build();
        return new ResponseEntity<>(failedToConvert, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(UsernameNotFoundException.class)
    private ResponseEntity<?> handleUserNotFound(UsernameNotFoundException foundException, HttpServletRequest request){
        RestException exception = new RestException
                .Builder()
                .setTitle("Failed to Authenticate")
                .setStatus(HttpStatus.UNAUTHORIZED.value())
                .setMessage(foundException.getMessage())
                .setTimestamp(new Date().getTime())
                .setDevDetails(foundException.getClass().getName())
                .setPath(request.getServletPath())
                .build();
        return new ResponseEntity<>(exception,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(SecurityException.class)
    private ResponseEntity<?> handleUserNotFound(SecurityException securityException, HttpServletRequest request){
        RestException exception = new RestException
                .Builder()
                .setTitle("Failed to Authenticate")
                .setStatus(HttpStatus.UNAUTHORIZED.value())
                .setMessage(securityException.getMessage())
                .setTimestamp(new Date().getTime())
                .setDevDetails(securityException.getClass().getName())
                .setPath(request.getServletPath())
                .build();
        return new ResponseEntity<>(exception,HttpStatus.UNAUTHORIZED);
    }
}
