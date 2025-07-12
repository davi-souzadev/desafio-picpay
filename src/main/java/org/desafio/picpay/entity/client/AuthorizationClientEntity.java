package org.desafio.picpay.entity.client;

public class AuthorizationClientEntity {
    private boolean authorizationData;

    public boolean isAuthorizationData() {
        return authorizationData;
    }

    public void setAuthorizationData(boolean authorizationData) {
        this.authorizationData = authorizationData;
    }

    @Override
    public String toString() {
        return "AuthorizationClientEntity{" +
                "authorizationData=" + authorizationData +
                '}';
    }
}
