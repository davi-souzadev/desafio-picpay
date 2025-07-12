package org.desafio.picpay.exception;

public class CnpjNotFoundException extends RuntimeException {
    public CnpjNotFoundException(String message) {
        super(message);
    }
}
