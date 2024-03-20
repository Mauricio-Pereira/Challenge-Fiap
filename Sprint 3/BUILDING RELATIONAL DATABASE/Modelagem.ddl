-- Mauricio Vieira Pereira    RM553748
-- Vitor Onofre Ramos         RM553241
-- Luiz Otávio Leitão         RM553542


-- DROP TABLES PARA ELIMINAR POSSIVEIS TABELAS JA EXISTENTES
DROP TABLE tb_bairro CASCADE CONSTRAINTS;

DROP TABLE tb_cidade CASCADE CONSTRAINTS;

DROP TABLE tb_cliente CASCADE CONSTRAINTS;

DROP TABLE tb_endereco CASCADE CONSTRAINTS;

DROP TABLE tb_estado CASCADE CONSTRAINTS;

DROP TABLE tb_pais CASCADE CONSTRAINTS;

DROP TABLE tb_produto CASCADE CONSTRAINTS;


-- CRIAÇÃO DAS TABELAS E ADIÇÃO DAS CONSTRAINTS
CREATE TABLE tb_bairro (
    cod_bairro   NUMBER NOT NULL,
    nome         VARCHAR2(50),
    tb_cidade_id NUMBER NOT NULL
);

CREATE UNIQUE INDEX tb_bairro__idx ON
    tb_bairro (
        tb_cidade_id
    ASC );

ALTER TABLE tb_bairro ADD CONSTRAINT tb_bairro_pk PRIMARY KEY ( cod_bairro );

CREATE TABLE tb_cidade (
    cod_cidade   NUMBER NOT NULL,
    nome         VARCHAR2(50),
    tb_estado_id NUMBER NOT NULL
);

CREATE UNIQUE INDEX tb_cidade__idx ON
    tb_cidade (
        tb_estado_id
    ASC );

ALTER TABLE tb_cidade ADD CONSTRAINT tb_cidade_pk PRIMARY KEY ( cod_cidade );

CREATE TABLE tb_cliente (
    cod_cliente       NUMBER NOT NULL,
    nome              VARCHAR2(50),
    sobrenome         VARCHAR2(80),
    data_nascimento   DATE,
    telefone          VARCHAR2(14),
    email_corporativo VARCHAR2(50),
    nome_usuario      VARCHAR2(20),
    senha             VARCHAR2(50),
    tb_endereco_id    NUMBER NOT NULL
);

CREATE UNIQUE INDEX tb_cliente__idx ON
    tb_cliente (
        tb_endereco_id
    ASC );

ALTER TABLE tb_cliente ADD CONSTRAINT tb_cliente_pk PRIMARY KEY ( cod_cliente );

CREATE TABLE tb_endereco (
    cod_endereco NUMBER NOT NULL,
    logradouro   VARCHAR2(250),
    numero       NUMBER,
    complemento  VARCHAR2(50),
    tb_bairro_id NUMBER NOT NULL,
    cep          NUMBER
);

CREATE UNIQUE INDEX tb_endereco__idx ON
    tb_endereco (
        tb_bairro_id
    ASC );

ALTER TABLE tb_endereco ADD CONSTRAINT tb_endereco_pk PRIMARY KEY ( cod_endereco );

CREATE TABLE tb_estado (
    cod_estado NUMBER NOT NULL,
    nome       VARCHAR2(50),
    tb_pais_id NUMBER NOT NULL
);

CREATE UNIQUE INDEX tb_estado__idx ON
    tb_estado (
        tb_pais_id
    ASC );

ALTER TABLE tb_estado ADD CONSTRAINT tb_estado_pk PRIMARY KEY ( cod_estado );

CREATE TABLE tb_pais (
    cod_pais NUMBER NOT NULL,
    nome     VARCHAR2(50)
);

ALTER TABLE tb_pais ADD CONSTRAINT tb_pais_pk PRIMARY KEY ( cod_pais );

CREATE TABLE tb_produto (
    cod_produto   NUMBER NOT NULL,
    nome          VARCHAR2(100),
    descricao     VARCHAR2(250),
    preco         NUMBER,
    tb_cliente_id NUMBER NOT NULL
);

ALTER TABLE tb_produto ADD CONSTRAINT tb_produto_pk PRIMARY KEY ( cod_produto );

ALTER TABLE tb_bairro
    ADD CONSTRAINT tb_bairro_tb_cidade_fk FOREIGN KEY ( tb_cidade_id )
        REFERENCES tb_cidade ( cod_cidade );

ALTER TABLE tb_cidade
    ADD CONSTRAINT tb_cidade_tb_estado_fk FOREIGN KEY ( tb_estado_id )
        REFERENCES tb_estado ( cod_estado );

ALTER TABLE tb_cliente
    ADD CONSTRAINT tb_cliente_tb_endereco_fk FOREIGN KEY ( tb_endereco_id )
        REFERENCES tb_endereco ( cod_endereco );

ALTER TABLE tb_endereco
    ADD CONSTRAINT tb_endereco_tb_bairro_fk FOREIGN KEY ( tb_bairro_id )
        REFERENCES tb_bairro ( cod_bairro );

ALTER TABLE tb_estado
    ADD CONSTRAINT tb_estado_tb_pais_fk FOREIGN KEY ( tb_pais_id )
        REFERENCES tb_pais ( cod_pais );

ALTER TABLE tb_produto
    ADD CONSTRAINT tb_produto_tb_cliente_fk FOREIGN KEY ( tb_cliente_id )
        REFERENCES tb_cliente ( cod_cliente );



-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             7
-- CREATE INDEX                             5
-- ALTER TABLE                             13
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
