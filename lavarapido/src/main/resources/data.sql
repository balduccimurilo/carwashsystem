INSERT INTO admin (id_user, cel, cpf, email, name, password, profile) 
VALUES (1, '00000000', '00000000000', 'admin@washmanager.com', 'admin', 'admin', 1);

INSERT INTO fornecedor (id_fornecedor, address, cel, cnpj, fantasy_name, name, razao_social) 
VALUES (1, 'Rua Augusta, 10', '11990901010', '10000000000101', 'Pretinho Do Bom', 'Joao', 'Pretinhos Pneus Ltda');

INSERT INTO product (id_product, description, name, price, fornecedor_id) 
VALUES (1, 'Pretinho para pneus', 'Pretinho Master 1', '10.9', 1);
