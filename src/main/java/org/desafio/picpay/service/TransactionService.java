package org.desafio.picpay.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.desafio.picpay.client.MockiClient;
import org.desafio.picpay.dto.TransactionRequestDTO;
import org.desafio.picpay.entity.*;
import org.desafio.picpay.exception.*;
import org.desafio.picpay.repository.TransactionRepository;
import org.desafio.picpay.repository.UserRepository;
import org.desafio.picpay.repository.WalletRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Optional;

@ApplicationScoped
public class TransactionService {

    TransactionRepository transactionRepository;
    UserRepository userRepository;
    WalletRepository walletRepository;
    MockiClient mockiClient;

    @Inject
    public TransactionService(TransactionRepository transactionRepository,
                              UserRepository userRepository,
                              WalletRepository walletRepository,
                              @RestClient MockiClient mockiClient
    ) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.mockiClient = mockiClient;
    }

    private boolean authorizeTransaction() {
        var response = mockiClient.authorize();
        return response.getStatus().equalsIgnoreCase("success");
    }

    private void sendNotification() {
        mockiClient.notifyClient();
    }

    public TransactionEntity create(TransactionRequestDTO transactionRequestDTO) {
        UserEntity payer = userRepository.findByIdOptional(transactionRequestDTO.payer)
                .orElseThrow(() -> new UserNotFoundException("Payer não encontrado"));
        UserEntity payee = userRepository.findByIdOptional(transactionRequestDTO.payee)
                .orElseThrow(() -> new UserNotFoundException("Payee não encontrado"));

        WalletEntity payerWallet = walletRepository.findByUserId(payer.getId());
        WalletEntity payeeWallet = walletRepository.findByUserId(payee.getId());
        TransactionEntity transactionEntity = new TransactionEntity();

        if (payer.getUserType().equals(UserType.MERCHANT)) {
           throw new UserTypeTransactionNotAllowed("Ação não permitida!");
        }

        if (transactionRequestDTO.amount <= 0.0) {
            throw new InvalidAmountException("Valor inválido!");
        }

        if (payerWallet.getBalance() < transactionRequestDTO.amount) {
            throw new InsufficientAmountException("Saldo insuficiente!");
        }

        if(!authorizeTransaction()) {
            throw new NotAuthorizedTransactionException("Transação não autorizada");
        }

        var transactionAmount = transactionRequestDTO.amount;
        payerWallet.setBalance(payerWallet.getBalance() - transactionAmount);
        payeeWallet.setBalance(payeeWallet.getBalance() + transactionAmount);

        walletRepository.persist(payerWallet);
        walletRepository.persist(payeeWallet);

        transactionEntity.setAmount(transactionAmount);
        transactionEntity.setPayerId(payer.getId());
        transactionEntity.setPayeeId(payee.getId());
        transactionEntity.setStatus(TransactionStatus.COMPLETED);
        transactionEntity.setExternalAuthorization(true);
        transactionRepository.persist(transactionEntity);

        sendNotification();

        return transactionEntity;
    }
}
