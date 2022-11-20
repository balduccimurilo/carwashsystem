--Run script sql when start

INSERT INTO profile(profile) VALUES('ADMIN');
INSERT INTO profile(profile) VALUES('CLIENT');

--Insert admin default

INSERT INTO user_tb (cel, cpf, email, name, password, username) 
VALUES ('00000000', '00000000000', 'admin@admin.com', 'Admin Complete Name', '$2a$10$yF10JCfnFV.aTJvcP08cBOHV9Ab1sQ5ekK4DlGCTLrrp5ob0CkMQa', 'admin');

INSERT INTO user_profiles (user_id, profile_id)
VALUES ( 1, 1);

--Insert cliend default

--INSERT INTO client (cel, cpf, email, name, password, profile) 
--VALUES ('00000001', '00000000001', 'client1@gmail.com', 'client', 'client', 2);

-- Insert Fornecedores-----------------------------------------------------------------------------

INSERT INTO fornecedor (address, cel, cnpj, fantasy_name, name, razao_social) 
VALUES ('Rua Augusta, 10', '11990901010', '10000000000101', 'Pretinho Do Bom', 'Joao', 'Pretinhos Pneus Ltda');

INSERT INTO fornecedor (address, cel, cnpj, fantasy_name, name, razao_social) 
VALUES ('Rua Unip, 20', '11990901022', '20000000000102', 'Shampoo Clean', 'Marcos', 'Shampoo Clean Ltda');

INSERT INTO fornecedor (address, cel, cnpj, fantasy_name, name, razao_social) 
VALUES ('Rua Brasil, 30', '11990901033', '30000000000103', 'Panos macios', 'Guilherme', 'Panos Ltda');

-- Insert Products---------------------------------------------------------------------------------

INSERT INTO product (description, name, price, fornecedor_id) 
VALUES ('Pretinho para pneus', 'Pretinho Master 1', '10.9', 1);

INSERT INTO product (description, name, price, fornecedor_id) 
VALUES ('Shampoo Importado', 'Shampoo para todos os carros', '25.0', 2);

INSERT INTO product (description, name, price, fornecedor_id) 
VALUES ('Cera Black', 'Cera Carnaúba carros pretos', '90', 2);

INSERT INTO product (description, name, price, fornecedor_id) 
VALUES ('Flanela', 'Flanela que não risca', '1.99', 3);
