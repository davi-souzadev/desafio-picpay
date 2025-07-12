package org.desafio.picpay.exception;

public class NotAuthorizedTransactionException extends RuntimeException {
    public NotAuthorizedTransactionException(String message) {
        super(message);
    }
}
