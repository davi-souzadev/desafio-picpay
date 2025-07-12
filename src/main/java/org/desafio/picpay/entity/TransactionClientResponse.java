package org.desafio.picpay.entity;


import jakarta.validation.constraints.Pattern;

public class TransactionClientResponse {
    @Pattern(regexp = "success|fail", message = "Status inválido")
    private String status;

    private AuthorizationClientEntity data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AuthorizationClientEntity getData() {
        return data;
    }

    public void setData(AuthorizationClientEntity data) {
        this.data = data;
    }
}
