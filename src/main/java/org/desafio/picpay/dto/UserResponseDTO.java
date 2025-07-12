package org.desafio.picpay.dto;

import org.desafio.picpay.entity.UserType;

public class UserResponseDTO {
    public String name;

    public String cpf;

    public String cnpj;

    public String email;

    public String password;

    public UserType type;

    public Double balance;

    public UserResponseDTO(String name, String cpf, String cnpj, String email, String password, UserType type, Double balance) {
        this.name = name;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.email = email;
        this.password = password;
        this.type = type;
        this.balance = balance;
    }
}
