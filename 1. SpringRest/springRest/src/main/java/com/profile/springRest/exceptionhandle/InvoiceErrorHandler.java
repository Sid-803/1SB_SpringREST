package com.profile.springRest.exceptionhandle;

import com.profile.springRest.entity.ErrorType;
import com.profile.springRest.exception.InvoiceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class InvoiceErrorHandler {
    /*
    *
    * In case of InvoiceNotFoundException is thrown
    * from any controller method, this logic gets executed
    * reusable
    * clear code (Code Modularity)
    * @param nfe
    * @return ResponseEntity
     */
    //@ResponseBody
    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<ErrorType> handleNotFound(InvoiceNotFoundException nfe){
        return new ResponseEntity<ErrorType>(
                new ErrorType(
                        new Date(System.currentTimeMillis()).toString(),
                      "404- NOT FOUND IN",
                        nfe.getMessage()),
                HttpStatus.NO_CONTENT);
    }
}
