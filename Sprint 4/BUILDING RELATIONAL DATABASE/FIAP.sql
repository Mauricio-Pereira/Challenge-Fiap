-- Mauricio Vieira Pereira    RM553748
-- Vitor Onofre Ramos         RM553241
-- Luiz Otávio Leitão         RM553542


-- DROP TABLES PARA ELIMINAR POSSIVEIS TABELAS JA EXISTENTES
DROP TABLE ch_bairro CASCADE CONSTRAINTS;

DROP TABLE ch_cidade CASCADE CONSTRAINTS;

DROP TABLE ch_cliente CASCADE CONSTRAINTS;

DROP TABLE ch_endereco CASCADE CONSTRAINTS;

DROP TABLE ch_estado CASCADE CONSTRAINTS;

DROP TABLE ch_pais CASCADE CONSTRAINTS;

DROP TABLE ch_produto CASCADE CONSTRAINTS;
DROP TABLE ch_estoque CASCADE CONSTRAINTS;


-- CRIAÇÃO DAS TABELAS E CONFIGURAÇÃO DAS PRIMARY KEYS
CREATE TABLE ch_bairro (
    cod_bairro           NUMBER GENERATED AS IDENTITY,
    nome                 VARCHAR2(50),
    ch_cidade_cod_cidade NUMBER NOT NULL
);

ALTER TABLE ch_bairro ADD CONSTRAINT ch_bairro_pk PRIMARY KEY ( cod_bairro );

CREATE TABLE ch_cidade (
    cod_cidade           NUMBER GENERATED ALWAYS AS IDENTITY,
    nome                 VARCHAR2(50),
    ch_estado_cod_estado NUMBER NOT NULL
);

ALTER TABLE ch_cidade ADD CONSTRAINT ch_cidade_pk PRIMARY KEY ( cod_cidade );

CREATE TABLE ch_cliente (
    cod_cliente       NUMBER GENERATED ALWAYS AS IDENTITY,
    nome              VARCHAR2(50),
    sobrenome         VARCHAR2(80),
    data_nascimento   DATE,
    telefone          VARCHAR2(14),
    email_corporativo VARCHAR2(50),
    nome_usuario      VARCHAR2(20),
    senha             VARCHAR2(50)
);

ALTER TABLE ch_cliente ADD CONSTRAINT ch_cliente_pk PRIMARY KEY ( cod_cliente );

CREATE TABLE ch_endereco (
    cod_endereco           NUMBER GENERATED ALWAYS AS IDENTITY,
    logradouro             VARCHAR2(250),
    numero                 NUMBER,
    complemento            VARCHAR2(50),
    cep                    NUMBER,
    ch_cliente_cod_cliente NUMBER NOT NULL,
    ch_bairro_cod_bairro   NUMBER NOT NULL
);

CREATE UNIQUE INDEX ch_endereco__idx ON
    ch_endereco (
        ch_cliente_cod_cliente
    ASC );

ALTER TABLE ch_endereco ADD CONSTRAINT ch_endereco_pk PRIMARY KEY ( cod_endereco );

CREATE TABLE ch_estado (
    cod_estado       NUMBER GENERATED ALWAYS AS IDENTITY,
    nome             VARCHAR2(50),
    ch_pais_cod_pais NUMBER NOT NULL
);

ALTER TABLE ch_estado ADD CONSTRAINT ch_estado_pk PRIMARY KEY ( cod_estado );

CREATE TABLE ch_pais (
    cod_pais NUMBER GENERATED ALWAYS AS IDENTITY,
    nome     VARCHAR2(50)
);

ALTER TABLE ch_pais ADD CONSTRAINT ch_pais_pk PRIMARY KEY ( cod_pais );

CREATE TABLE ch_estoque (
    cod_estoque            NUMBER NOT NULL,
    cod_produto            NUMBER,
    quantidade             NUMBER,
    ch_cliente_cod_cliente NUMBER NOT NULL
);

ALTER TABLE ch_estoque ADD CONSTRAINT ch_estoque_pk PRIMARY KEY ( cod_estoque );

CREATE TABLE ch_produto (
    cod_produto            NUMBER GENERATED ALWAYS AS IDENTITY,
    nome                   VARCHAR2(100),
    descricao              VARCHAR2(250),
    preco                  NUMBER,
    estoque                NUMBER,
    ch_estoque_cod_estoque NUMBER NOT NULL
);

ALTER TABLE ch_produto ADD CONSTRAINT ch_produto_pk PRIMARY KEY ( cod_produto );



-- ADIÇÃO DAS CONSTRAINTS E FOREIGN KEYS
ALTER TABLE ch_bairro
    ADD CONSTRAINT ch_bairro_ch_cidade_fk FOREIGN KEY ( ch_cidade_cod_cidade )
        REFERENCES ch_cidade ( cod_cidade );

ALTER TABLE ch_cidade
    ADD CONSTRAINT ch_cidade_ch_estado_fk FOREIGN KEY ( ch_estado_cod_estado )
        REFERENCES ch_estado ( cod_estado );

ALTER TABLE ch_endereco
    ADD CONSTRAINT ch_endereco_ch_bairro_fk FOREIGN KEY ( ch_bairro_cod_bairro )
        REFERENCES ch_bairro ( cod_bairro );

ALTER TABLE ch_endereco
    ADD CONSTRAINT ch_endereco_ch_cliente_fk FOREIGN KEY ( ch_cliente_cod_cliente )
        REFERENCES ch_cliente ( cod_cliente );

ALTER TABLE ch_estado
    ADD CONSTRAINT ch_estado_ch_pais_fk FOREIGN KEY ( ch_pais_cod_pais )
        REFERENCES ch_pais ( cod_pais );

ALTER TABLE ch_estoque
    ADD CONSTRAINT ch_estoque_ch_cliente_fk FOREIGN KEY ( ch_cliente_cod_cliente )
        REFERENCES ch_cliente ( cod_cliente );

ALTER TABLE ch_produto
    ADD CONSTRAINT ch_produto_ch_estoque_fk FOREIGN KEY ( ch_estoque_cod_estoque )
        REFERENCES ch_estoque ( cod_estoque );



INSERT INTO CH_PAIS(NOME) VALUES('Brasil');

--Inserir os estados do Brasil
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Acre','1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Alagoas', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Amapá', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Amazonas', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Bahia', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Ceará', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Distrito Federal', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Espírito Santo', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Goiás', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Maranhão', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Mato Grosso', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Mato Grosso do Sul', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Minas Gerais', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Pará', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Paraíba', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Paraná', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Pernambuco', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Piauí', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Rio de Janeiro', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Rio Grande do Norte', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Rio Grande do Sul', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Rondônia', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Roraima', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Santa Catarina', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('São Paulo', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Sergipe', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Tocantins', '1');


-- Inserir as 10 principais cidades de São Paulo
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('São Paulo', 25);
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('Guarulhos', 25);
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('Campinas', 25);
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('São Bernardo do Campo', 25);
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('Santo André', 25);
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('São José dos Campos', 25);
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('Osasco', 25);
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('Ribeirão Preto', 25);
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('Sorocaba', 25);
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('Mauá', 25);


-- Inserir 10 bairros na cidade de São Paulo
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Moema', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Pinheiros', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Itaim Bibi', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Vila Madalena', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Vila Mariana', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Jardins', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Mooca', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Perdizes', 1);


--Inserindo dados duplicados e com nomes incorretos para Update e Delete
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Brooklinnn', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Tatuapééé', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Casa Verdeee', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Brooklinnn', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Tatuapééé', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Casa Verdeee', 1);

--Dando Update nos dados incorretos e corrigindo eles
UPDATE ch_bairro SET nome = 'Brooklin' WHERE nome = 'Brooklinnn' AND ch_cidade_cod_cidade = 1;
UPDATE ch_bairro SET nome = 'Tatuapé' WHERE nome = 'Tatuapééé' AND ch_cidade_cod_cidade = 1;
UPDATE ch_bairro SET nome = 'Casa Verde' WHERE nome = 'Casa Verdeee' AND ch_cidade_cod_cidade = 1;

--Deletando os dados duplicados
-- Deletando um dos registros duplicados de 'Brooklin'
DELETE FROM ch_bairro
WHERE ROWID IN (
    SELECT ROWID 
    FROM ch_bairro
    WHERE nome = 'Brooklin' AND ch_cidade_cod_cidade = 1
    AND ROWNUM = 1
);

select * from ch_bairro where nome = 'Brooklin';
-- Deletando um dos registros duplicados de 'Tatuapé'
DELETE FROM ch_bairro
WHERE ROWID IN (
    SELECT ROWID 
    FROM ch_bairro
    WHERE nome = 'Tatuapé' AND ch_cidade_cod_cidade = 1
    AND ROWNUM = 1
);

-- Deletando um dos registros duplicados de 'Casa Verde'
DELETE FROM ch_bairro
WHERE ROWID IN (
    SELECT ROWID 
    FROM ch_bairro
    WHERE nome = 'Casa Verde' AND ch_cidade_cod_cidade = 1
    AND ROWNUM = 1
);

-- Inserindo dois registros na tabela ch_cliente
INSERT INTO ch_cliente (nome, sobrenome, data_nascimento, telefone, email_corporativo, nome_usuario, senha)
VALUES ('João', 'Silva', TO_DATE('1985-05-15', 'YYYY-MM-DD'), '1198765-4321', 'joao.silva@empresa.com', 'joaosilva', 'senha123');
select*from ch_cliente;

INSERT INTO ch_cliente (nome, sobrenome, data_nascimento, telefone, email_corporativo, nome_usuario, senha)
VALUES ('Maria', 'Oliveira', TO_DATE('1990-10-20', 'YYYY-MM-DD'), '1191234-5678', 'maria.oliveira@empresa.com', 'mariaoliveira', 'senha456');


-- Inserindo dois registros na tabela ch_estoque
INSERT INTO ch_estoque (cod_estoque, cod_produto, quantidade, ch_cliente_cod_cliente) VALUES (1, NULL, 100, 1);
INSERT INTO ch_estoque (cod_estoque, cod_produto, quantidade, ch_cliente_cod_cliente) VALUES (2, NULL, 200, 2);



-- Inserindo quinze registros na tabela ch_produto, associando a cada um um dos estoques criados
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 1', 'Descrição do Produto 1', 10.00, 50, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 2', 'Descrição do Produto 2', 15.00, 30, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 3', 'Descrição do Produto 3', 20.00, 20, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 4', 'Descrição do Produto 4', 25.00, 40, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 5', 'Descrição do Produto 5', 30.00, 60, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 6', 'Descrição do Produto 6', 35.00, 70, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 7', 'Descrição do Produto 7', 40.00, 80, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 8', 'Descrição do Produto 8', 45.00, 90, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 9', 'Descrição do Produto 9', 50.00, 100, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 10', 'Descrição do Produto 10', 55.00, 110, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 11', 'Descrição do Produto 11', 60.00, 120, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 12', 'Descrição do Produto 12', 65.00, 130, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 13', 'Descrição do Produto 13', 70.00, 140, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 14', 'Descrição do Produto 14', 75.00, 150, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 15', 'Descrição do Produto 15', 80.00, 160, 2);


SELECT 
    p.cod_produto, 
    p.nome, 
    p.descricao, 
    e.quantidade 
FROM 
    ch_produto p
JOIN 
    ch_estoque e ON p.ch_estoque_cod_estoque = e.cod_estoque
ORDER BY 
    p.cod_produto;

select* from ch_estoque;
