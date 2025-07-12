package org.desafio.picpay.exception;

public class UserTypeTransactionNotAllowed extends RuntimeException {
    public UserTypeTransactionNotAllowed(String message) {
        super(message);
    }
}
