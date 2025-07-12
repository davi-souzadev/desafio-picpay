-- Inserir usuário do tipo MERCHANT
INSERT INTO users (name, cpf, cnpj, email, password, user_type)
VALUES ('Zé Frias', '12345678901', '12345678000199', 'loja@exemplo.com', 'senha123', 'MERCHANT');

-- Inserir usuário do tipo COMMON
INSERT INTO users (name, cpf, email, password, user_type)
VALUES ('João Silva', '98765432100', 'joao@exemplo.com', 'senha456', 'COMMON');

-- Inserir carteira para o usuário MERCHANT (id=1, saldo=500)
INSERT INTO wallets (user_id, balance)
VALUES (1, 500.0);

-- Inserir carteira para o usuário COMMON (id=2, saldo=750)
INSERT INTO wallets (user_id, balance)
VALUES (2, 750.0);