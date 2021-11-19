INSERT INTO account.bank (name, number,created_at,update_at) values ("Itáu", "144", '2021-05-08 21:31:32', '2021-05-08 21:31:32');
INSERT INTO account.bank (name, number,created_at, update_at) values ("Banco do Brasil", "522", '2021-05-08 21:31:32', '2021-05-08 21:31:32');

INSERT INTO account.account(id, agency, balance, address_city, address_complement, address_district, address_number, address_place, address_state, address_zip_code, client_cpf, client_name, created_at, `number`, reference_transaction_id, updated_at, bank_id)
VALUES(1, '7389', 2000.00, 'Belém', 'Px ao Supermercado Eldorado', 'Coqueiro', '85', 'Passagem Maria', 'PA', '67015-300', '842.245.280-45', 'Maria da Silva', '2021-11-19 00:03:59.990128000', '18627-6', '3b77822b-5f66-48ca-b3fe-9bd6661984c7', '2021-11-19 00:03:59.990185000', 1);

INSERT INTO account.account(id, agency, balance, address_city, address_complement, address_district, address_number, address_place, address_state, address_zip_code, client_cpf, client_name, created_at, `number`, reference_transaction_id, updated_at, bank_id)
VALUES(2, '0071', 1000.00, 'Belém', 'Px ao Supermercado Eldorado', 'Coqueiro', '85', 'Passagem Maria', 'PA', '67015-300', '696.506.700-41', 'João da Silva', '2021-11-19 00:04:45.880048000', '1164808-2', 'f0941f86-73df-46af-9f5d-a399ac972337', '2021-11-19 00:04:45.880063000', 2);
