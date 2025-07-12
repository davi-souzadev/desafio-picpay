package org.desafio.picpay.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.desafio.picpay.entity.WalletEntity;

@ApplicationScoped
public class WalletRepository implements PanacheRepositoryBase<WalletEntity, Long> {
    public WalletEntity findByUserId(Long id) {
        return (WalletEntity) find("user.id", id);
    }
}
