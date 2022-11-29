--Run script sql when start

INSERT INTO profile (profile) VALUES('ADMIN');
INSERT INTO profile (profile) VALUES('CLIENT');

--Insert admin default

INSERT INTO users (cel, cpf, email, name, password, role, username) 
VALUES ('00000000', '00000000000', 'admin@admin.com', 'Admin Complete Name', '$2a$10$yF10JCfnFV.aTJvcP08cBOHV9Ab1sQ5ekK4DlGCTLrrp5ob0CkMQa', 'ADMIN', 'admin');

INSERT INTO users_profiles (user_id_user, profiles_id)
VALUES ( 1, 1);

--Insert cliend default

--INSERT INTO client (cel, cpf, email, name, password, profile) 
--VALUES ('00000001', '00000000001', 'client1@gmail.com', 'client', 'client', 2);
insert into users (cel, cpf, email, name, password, role, username) 
values ('00000000', '00000000000', 'admin@admin.com', 'Admin Complete Name', '$2a$10$yF10JCfnFV.aTJvcP08cBOHV9Ab1sQ5ekK4DlGCTLrrp5ob0CkMQa', 'ADMIN', 'admin');

insert into users (cel, cpf, email, name, password, role, username) 
values ('(19)900000003', '00000000003', 'guilherme3@email.com', 'Guilherme3', '$2a$10$pWKlX90/JxhbOtrxWiTtPume/J1GrVHvze99feDjOaS1P8YlRtMsi', 'CLIENT', 'guilherme3');

insert into users (cel, cpf, email,  name, password, role, username) 
values ('(19)900000004', '00000000004', 'guilherme4@email.com',  'Guilherme4', '$2a$10$5UeBdmUUyoQFbKEaAo1XG.YIDPoTFDkE9PmMEd7SzsPuTONjHncYO', 'CLIENT', 'guilherme4');

insert into users (cel, cpf, email,  name, password, role, username) 
values ('(19)900000005', '00000000005', 'guilherme5@email.com',  'Guilherme5', '$2a$10$MgYEMPTXgyzvyaXB4Gyn6OTuxhuouZyq0LEbMp0tSSD9QapiYlReq', 'CLIENT', 'guilherme5');

insert into users (cel, cpf, email,  name, password, role, username) 
values ('(19)900000006', '00000000006', 'guilherme6@email.com',  'Guilherme6', '$2a$10$iMCjYuMULUarPkEue.eLyeVb1wi1imbjIgfcGyX6jBhihxErqfeEW', 'CLIENT', 'guilherme6');

insert into users (cel, cpf, email,  name, password, role, username) 
values ('(19)900000007', '00000000007', 'guilherme7@email.com',  'Guilherme7', '$2a$10$deS1x5IpqXO2zchlqMp9Nevlzj3Wc6MBkNN6q/5cCy/GlzQnvGn5q', 'CLIENT', 'guilherme7');

insert into users (cel, cpf, email,  name, password, role, username) 
values ('(19)900000008', '00000000008', 'guilherme8@email.com',  'Guilherme8', '$2a$10$XPEkgCnIW9Odo7p0/c5UuuO/tpYTXUEcdMxxSs.PE0vWWp1QPCnqi', 'CLIENT', 'guilherme8');

insert into users (cel, cpf, email,  name, password, role, username) 
values ('(19)900000009', '00000000009', 'guilherme9@email.com',  'Guilherme9', '$2a$10$q3oi3Zik.43yg8jDypqfZOT0Uvo1CJ4km1mNGVmbNwk9Q9Hx1prLm', 'CLIENT', 'guilherme9');

insert into users (cel, cpf, email,  name, password, role, username) 
values ('(19)9000000011', '000000000011', 'guilherme11@email.com',  'Guilherme11', '$2a$10$1C8.8MCBTCQ83WVeMW.JGeoyQmy0mqw2Ikbjdgrr9IUFEIIAuUHlC', 'CLIENT', 'guilherme11');

insert into users (cel, cpf, email,  name, password, role, username) 
values ('(19)9000000012', '000000000012', 'guilherme12@email.com',  'Guilherme12', '$2a$10$hPDdWUCtdMMaCK7poZf9YOqa.LIw0vtfGFhfT.VeMcZMTr5ZsjDuq', 'CLIENT', 'guilherme12');

insert into users (cel, cpf, email,  name, password, role, username) 
values ('(19)9000000013', '000000000013', 'guilherme13@email.com',  'Guilherme13', '$2a$10$90q4aGP4p6rVEtUsTS6Mzu0WnPQBEmr9vm0sDwAQ75ZVBUkWBJPxW', 'CLIENT', 'guilherme13');

insert into users (cel, cpf, email,  name, password, role, username) 
values ('(19)9000000014', '000000000014', 'guilherme14@email.com',  'Guilherme14', '$2a$10$ByMPwEhfWXX5a3JkPFo5l.XrqumeebQkuDjxuPGKMTDoRKod1s4pC', 'CLIENT', 'guilherme14');


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
