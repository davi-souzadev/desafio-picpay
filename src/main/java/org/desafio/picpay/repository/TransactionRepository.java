package org.desafio.picpay.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.desafio.picpay.entity.TransactionEntity;

@ApplicationScoped
public class TransactionRepository implements PanacheRepositoryBase<TransactionEntity, Long> {
}
