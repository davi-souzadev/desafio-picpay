package org.desafio.picpay.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.desafio.picpay.dto.UserResponseDTO;
import org.desafio.picpay.entity.WalletEntity;
import org.desafio.picpay.exception.CnpjNotFoundException;
import org.desafio.picpay.dto.UserRequestDTO;
import org.desafio.picpay.entity.UserEntity;
import org.desafio.picpay.entity.UserType;
import org.desafio.picpay.exception.InvalidUserTypeException;
import org.desafio.picpay.repository.UserRepository;
import org.desafio.picpay.repository.WalletRepository;

import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class RegisterService {

    final UserRepository personRepository;
    final WalletRepository walletRepository;
    private final Double INITIAL_BALANCE = 0.0;

    @Inject
    public RegisterService(UserRepository personRepository, WalletRepository walletRepository) {
        this.personRepository = personRepository;
        this.walletRepository = walletRepository;
    }

    public List<UserEntity> getAll() {
        return personRepository.findAll().list();
    }

    public UserResponseDTO register(UserRequestDTO userRequestDTO) {
        UserEntity user = new UserEntity();
        user.setName(userRequestDTO.name);
        user.setCpf(userRequestDTO.cpf);
        user.setCnpj(userRequestDTO.cnpj);
        user.setUserType(userRequestDTO.type);
        user.setPassword(userRequestDTO.password);
        user.setEmail(userRequestDTO.email);

        if (Arrays.stream(UserType.values()).noneMatch(value -> value.equals(userRequestDTO.type))) {
            throw new InvalidUserTypeException("Tipo de usuário inválido");
        }

        if (userRequestDTO.type == UserType.MERCHANT) {
            if(userRequestDTO.cnpj == null) {
                throw new CnpjNotFoundException("Lojistas devem ter CNPJ");
            }

            if(userRequestDTO.cnpj.isEmpty()) {
                throw new CnpjNotFoundException("Lojistas devem ter CNPJ");
            }
        }

        personRepository.persist(user);

        WalletEntity wallet = new WalletEntity();
        wallet.setBalance(INITIAL_BALANCE);
        wallet.setUserEntity(user);

        walletRepository.persist(wallet);

        return new UserResponseDTO(user.getName(),
                user.getCpf(),
                user.getCnpj(),
                user.getEmail(),
                user.getPassword(),
                user.getUserType(),
                wallet.getBalance());
    }
    
    public List<WalletEntity> getWallets() {
        return walletRepository.findAll().list();
    }
}
