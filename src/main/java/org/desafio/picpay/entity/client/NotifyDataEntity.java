package org.desafio.picpay.entity.client;

public class NotifyDataEntity {
    private boolean notify;

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    @Override
    public String toString() {
        return "NotifyDataEntity{" +
                "notify=" + notify +
                '}';
    }
}
