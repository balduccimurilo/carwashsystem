--Run script sql when start

--Insert profile roles

INSERT INTO profile (profile) VALUES('ADMIN');
INSERT INTO profile (profile) VALUES('CLIENT');

--Insert admin default

INSERT INTO users (cel, cpf, email, name, password, role, username) 
VALUES ('00000000', '00000000000', 'admin@admin.com', 'Admin Complete Name', '$2a$10$pWKlX90/JxhbOtrxWiTtPume/J1GrVHvze99feDjOaS1P8YlRtMsi', 'ADMIN', 'admin');

--Insert profiles relation user

INSERT INTO users_profiles (user_id_user, profiles_id)
VALUES ( 1, 1);

INSERT INTO users_profiles (user_id_user, profiles_id)
VALUES ( 2, 2);

INSERT INTO users_profiles (user_id_user, profiles_id)
VALUES ( 3, 2);

INSERT INTO users_profiles (user_id_user, profiles_id)
VALUES ( 4, 2);

INSERT INTO users_profiles (user_id_user, profiles_id)
VALUES ( 5, 2);

INSERT INTO users_profiles (user_id_user, profiles_id)
VALUES ( 6, 2);

--Insert cliend default

--INSERT INTO client (cel, cpf, email, name, password, profile) 
--VALUES ('00000001', '00000000001', 'client1@gmail.com', 'client', 'client', 2);
--insert into users (cel, cpf, email, name, password, role, username) 
--values ('00000000', '00000000000', 'admin@admin.com', 'Admin Complete Name', '$2a$10$yF10JCfnFV.aTJvcP08cBOHV9Ab1sQ5ekK4DlGCTLrrp5ob0CkMQa', 'ADMIN', 'admin');

insert into users (cel, cpf, email, name, password, role, username) 
values ('(19)900000003', '00000000003', 'guilherme@email.com', 'Guilherme Araujo', '$2a$10$pWKlX90/JxhbOtrxWiTtPume/J1GrVHvze99feDjOaS1P8YlRtMsi', 'CLIENT', 'guilherme');

insert into users (cel, cpf, email,  name, password, role, username) 
values ('(19)900000004', '00000000004', 'lucas@email.com',  'Lucas Seixas', '$2a$10$5UeBdmUUyoQFbKEaAo1XG.YIDPoTFDkE9PmMEd7SzsPuTONjHncYO', 'CLIENT', 'lucas');

insert into users (cel, cpf, email,  name, password, role, username) 
values ('(19)900000005', '00000000005', 'isadora@email.com',  'Isadora Covalenco', '$2a$10$MgYEMPTXgyzvyaXB4Gyn6OTuxhuouZyq0LEbMp0tSSD9QapiYlReq', 'CLIENT', 'isadora');

insert into users (cel, cpf, email,  name, password, role, username) 
values ('(19)900000006', '00000000006', 'murilo@email.com',  'Murilo Balducci', '$2a$10$iMCjYuMULUarPkEue.eLyeVb1wi1imbjIgfcGyX6jBhihxErqfeEW', 'CLIENT', 'murilo');

insert into users (cel, cpf, email,  name, password, role, username) 
values ('(19)900000006', '00000000006', 'gabriel@email.com',  'Gabriel Quinaglia', '$2a$10$iMCjYuMULUarPkEue.eLyeVb1wi1imbjIgfcGyX6jBhihxErqfeEW', 'CLIENT', 'gabriel');

-- Insert Fornecedores-----------------------------------------------------------------------------

INSERT INTO fornecedor (address, cel, cnpj, email, fantasy_name, name, razao_social) 
VALUES ('Rua Augusta, 10', '11990901010', '10000000000101','fornecedor1@mail.com' , 'Pretinho Do Bom', 'Joao', 'Pretinhos Pneus Ltda');

INSERT INTO fornecedor (address, cel, cnpj, email, fantasy_name, name, razao_social) 
VALUES ('Rua Unip, 20', '11990901022','20000000000102','fornecedor2@mail.com' , 'Shampoo Clean', 'Marcos', 'Shampoo Clean Ltda');

INSERT INTO fornecedor (address, cel, cnpj, email, fantasy_name, name, razao_social) 
VALUES ('Rua Brasil, 30', '11990901033', '30000000000103', 'fornecedor3@mail.com' , 'Panos macios', 'Guilherme', 'Panos Ltda');

-- Insert Products---------------------------------------------------------------------------------

INSERT INTO product (description, name, price, fornecedor_id) 
VALUES ('Pretinho para pneus', 'Pretinho Master 1', '10.9', 1);

INSERT INTO product (description, name, price, fornecedor_id) 
VALUES ('Shampoo Importado', 'Shampoo para todos os carros', '25.0', 2);

INSERT INTO product (description, name, price, fornecedor_id) 
VALUES ('Cera Black', 'Cera Carnaúba carros pretos', '90', 2);

INSERT INTO product (description, name, price, fornecedor_id) 
VALUES ('Flanela', 'Flanela que não risca', '1.99', 3);
