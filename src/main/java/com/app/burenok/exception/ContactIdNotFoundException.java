package com.app.burenok.exception;

public class ContactIdNotFoundException extends Exception {
    public ContactIdNotFoundException(long id) {
        super(String.format("Contact with Id %d not found", id));
    }
}
