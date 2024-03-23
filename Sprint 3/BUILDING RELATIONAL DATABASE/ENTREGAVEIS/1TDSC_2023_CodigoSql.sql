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


-- CRIAÇÃO DAS TABELAS E CONFIGURAÇÃO DAS PRIMARY KEYS
CREATE TABLE ch_bairro (
    cod_bairro           NUMBER NOT NULL,
    nome                 VARCHAR2(50),
    ch_cidade_cod_cidade NUMBER NOT NULL
);

ALTER TABLE ch_bairro ADD CONSTRAINT ch_bairro_pk PRIMARY KEY ( cod_bairro );

CREATE TABLE ch_cidade (
    cod_cidade           NUMBER NOT NULL,
    nome                 VARCHAR2(50),
    ch_estado_cod_estado NUMBER NOT NULL
);

ALTER TABLE ch_cidade ADD CONSTRAINT ch_cidade_pk PRIMARY KEY ( cod_cidade );

CREATE TABLE ch_cliente (
    cod_cliente       NUMBER NOT NULL,
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
    cod_endereco           NUMBER NOT NULL,
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
    cod_estado       NUMBER NOT NULL,
    nome             VARCHAR2(50),
    ch_pais_cod_pais NUMBER NOT NULL
);

ALTER TABLE ch_estado ADD CONSTRAINT ch_estado_pk PRIMARY KEY ( cod_estado );

CREATE TABLE ch_pais (
    cod_pais NUMBER NOT NULL,
    nome     VARCHAR2(50)
);

ALTER TABLE ch_pais ADD CONSTRAINT ch_pais_pk PRIMARY KEY ( cod_pais );

CREATE TABLE ch_produto (
    cod_produto            NUMBER NOT NULL,
    nome                   VARCHAR2(100),
    descricao              VARCHAR2(250),
    preco                  NUMBER,
    estoque                NUMBER,
    ch_cliente_cod_cliente NUMBER NOT NULL
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

ALTER TABLE ch_produto
    ADD CONSTRAINT ch_produto_ch_cliente_fk FOREIGN KEY ( ch_cliente_cod_cliente )
        REFERENCES ch_cliente ( cod_cliente );



