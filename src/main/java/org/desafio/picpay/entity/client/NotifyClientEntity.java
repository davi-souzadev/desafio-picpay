package org.desafio.picpay.entity.client;

import jakarta.validation.constraints.Pattern;

public class NotifyClientEntity {
    @Pattern(regexp = "success|fail", message = "Padrão inválido!")
    private String status;

    private NotifyDataEntity data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public NotifyDataEntity getData() {
        return data;
    }

    public void setData(NotifyDataEntity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NotifyClientEntity{" +
                "status='" + status + '\'' +
                ", data=" + data.toString() +
                '}';
    }
}
