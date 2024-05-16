-- Mauricio Vieira Pereira    RM553748
-- Vitor Onofre Ramos         RM553241
-- Luiz Ot�vio Leit�o         RM553542


-- DROP TABLES PARA ELIMINAR POSSIVEIS TABELAS JA EXISTENTES
DROP TABLE ch_bairro CASCADE CONSTRAINTS;

DROP TABLE ch_cidade CASCADE CONSTRAINTS;

DROP TABLE ch_cliente CASCADE CONSTRAINTS;

DROP TABLE ch_endereco CASCADE CONSTRAINTS;

DROP TABLE ch_estado CASCADE CONSTRAINTS;

DROP TABLE ch_pais CASCADE CONSTRAINTS;

DROP TABLE ch_produto CASCADE CONSTRAINTS;
DROP TABLE ch_estoque CASCADE CONSTRAINTS;


-- CRIA��O DAS TABELAS E CONFIGURA��O DAS PRIMARY KEYS
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



-- ADI��O DAS CONSTRAINTS E FOREIGN KEYS
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
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Acre','1');

INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Alagoas', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Amap�', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Amazonas', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Bahia', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Cear�', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Distrito Federal', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Esp�rito Santo', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Goi�s', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Maranh�o', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Mato Grosso', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Mato Grosso do Sul', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Minas Gerais', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Par�', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Para�ba', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Paran�', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Pernambuco', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Piau�', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Rio de Janeiro', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Rio Grande do Norte', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Rio Grande do Sul', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Rond�nia', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Roraima', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Santa Catarina', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('S�o Paulo', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Sergipe', '1');
INSERT INTO ch_estado (nome, ch_pais_cod_pais) VALUES ('Tocantins', '1');


-- Inserir as 10 principais cidades de S�o Paulo
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('S�o Paulo', 25);
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('Guarulhos', 25);
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('Campinas', 25);
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('S�o Bernardo do Campo', 25);
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('Santo Andr�', 25);
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('S�o Jos� dos Campos', 25);
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('Osasco', 25);
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('Ribeir�o Preto', 25);
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('Sorocaba', 25);
INSERT INTO ch_cidade (nome, ch_estado_cod_estado) VALUES ('Mau�', 25);



INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Moema', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Pinheiros', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Itaim Bibi', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Vila Madalena', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Vila Mariana', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Jardins', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Mooca', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Perdizes', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Brooklin', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Tatuap�', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Casa Verde', 1);

commit;

