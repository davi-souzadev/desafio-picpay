package org.desafio.picpay.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.desafio.picpay.dto.TransactionRequestDTO;
import org.desafio.picpay.entity.TransactionEntity;
import org.desafio.picpay.entity.UserEntity;
import org.desafio.picpay.entity.UserType;
import org.desafio.picpay.entity.WalletEntity;
import org.desafio.picpay.exception.InsufficientAmountException;
import org.desafio.picpay.exception.InvalidAmountException;
import org.desafio.picpay.exception.UserTypeTransactionNotAllowed;
import org.desafio.picpay.repository.TransactionRepository;
import org.desafio.picpay.repository.UserRepository;
import org.desafio.picpay.repository.WalletRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TransactionService {

    TransactionRepository transactionRepository;
    UserRepository userRepository;
    WalletRepository walletRepository;

    @Inject
    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, WalletRepository walletRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    public TransactionEntity create(TransactionRequestDTO transactionRequestDTO) {
        UserEntity payer = userRepository.findById(transactionRequestDTO.payer);
        UserEntity payee = userRepository.findById(transactionRequestDTO.payee);
        WalletEntity wallet = walletRepository.findByUserId(payer.getId());
        TransactionEntity transactionEntity = new TransactionEntity();

        if (payer.getUserType() == UserType.MERCHANT) {
           throw new UserTypeTransactionNotAllowed("Ação não permitida!");
        }

        if (transactionRequestDTO.amount <= 0.0) {
            throw new InvalidAmountException("Valor inválido!");
        }

        if (wallet.getBalance() < transactionRequestDTO.amount) {
            throw new InsufficientAmountException("Saldo insuficiente!");
        }

        return transactionEntity;
    }
}
