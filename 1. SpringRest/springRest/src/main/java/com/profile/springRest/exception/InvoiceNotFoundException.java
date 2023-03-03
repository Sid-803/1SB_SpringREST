package com.profile.springRest.exception;

public class InvoiceNotFoundException extends RuntimeException{

    private static final long SerialVersionUID = 1L;

    public InvoiceNotFoundException(){
        super();
    }

    public InvoiceNotFoundException(String message){
        super(message);
    }
}
