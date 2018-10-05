CREATE TABLE cliente(
   id  SERIAL PRIMARY KEY,
   nome           VARCHAR(100)  NOT NULL,
   cpf_cnpj       VARCHAR(20) NOT NULL,
   data_nasc      DATE
);

CREATE UNIQUE INDEX unique_cpf_cnpj ON cliente(cpf_cnpj);

CREATE TABLE endereco(
    id SERIAL PRIMARY KEY NOT NULL,
    logradouro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    uf VARCHAR(100) NOT NULL,
    cliente_id INTEGER  REFERENCES cliente(id)
);

CREATE TABLE contato(
    id SERIAL PRIMARY KEY NOT NULL,
    fixo VARCHAR(100) NOT NULL,
    celular VARCHAR(100) NOT NULL,
    cliente_id INTEGER  REFERENCES cliente(id)
);