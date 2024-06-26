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
    cod_estoque            NUMBER GENERATED ALWAYS AS IDENTITY,
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
    ADD CONSTRAINT ch_estoque_ch_cliente_fk FOREIGN KEY (ch_cliente_cod_cliente)
    REFERENCES ch_cliente (cod_cliente) ON DELETE CASCADE;

ALTER TABLE ch_produto
    ADD CONSTRAINT ch_produto_ch_estoque_fk FOREIGN KEY ( ch_estoque_cod_estoque )
        REFERENCES ch_estoque ( cod_estoque );



INSERT INTO CH_PAIS(NOME) VALUES('Brasil');

--Inserir os estados do Brasil
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


-- Inserir 10 bairros na cidade de S�o Paulo
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
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Tatuap���', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Casa Verdeee', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Brooklinnn', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Tatuap���', 1);
INSERT INTO ch_bairro (nome, ch_cidade_cod_cidade) VALUES ('Casa Verdeee', 1);

--Dando Update nos dados incorretos e corrigindo eles
UPDATE ch_bairro SET nome = 'Brooklin' WHERE nome = 'Brooklinnn' AND ch_cidade_cod_cidade = 1;
UPDATE ch_bairro SET nome = 'Tatuap�' WHERE nome = 'Tatuap���' AND ch_cidade_cod_cidade = 1;
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


-- Deletando um dos registros duplicados de 'Tatuap�'
DELETE FROM ch_bairro
WHERE ROWID IN (
    SELECT ROWID 
    FROM ch_bairro
    WHERE nome = 'Tatuap�' AND ch_cidade_cod_cidade = 1
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

-- Inserindo 10 registros na tabela ch_cliente
INSERT INTO ch_cliente (nome, sobrenome, data_nascimento, telefone, email_corporativo, nome_usuario, senha)
VALUES ('Jo�o', 'Silva', TO_DATE('1985-05-15', 'YYYY-MM-DD'), '1198765-4321', 'joao.silva@empresa.com', 'joaosilva', 'senha123');

INSERT INTO ch_cliente (nome, sobrenome, data_nascimento, telefone, email_corporativo, nome_usuario, senha)
VALUES ('Maria', 'Oliveira', TO_DATE('1990-10-20', 'YYYY-MM-DD'), '1191234-5678', 'maria.oliveira@empresa.com', 'mariaoliveira', 'senha456');

INSERT INTO ch_cliente (nome, sobrenome, data_nascimento, telefone, email_corporativo, nome_usuario, senha)
VALUES ('Carlos', 'Pereira', TO_DATE('1982-03-10', 'YYYY-MM-DD'), '1196543-2109', 'carlos.pereira@empresa.com', 'carlospereira', 'senha789');

INSERT INTO ch_cliente (nome, sobrenome, data_nascimento, telefone, email_corporativo, nome_usuario, senha)
VALUES ('Ana', 'Costa', TO_DATE('1975-08-25', 'YYYY-MM-DD'), '1193456-7890', 'ana.costa@empresa.com', 'anacosta', 'senha101');

INSERT INTO ch_cliente (nome, sobrenome, data_nascimento, telefone, email_corporativo, nome_usuario, senha)
VALUES ('Bruno', 'Martins', TO_DATE('1988-12-30', 'YYYY-MM-DD'), '1198765-1234', 'bruno.martins@empresa.com', 'brunomartins', 'senha102');

INSERT INTO ch_cliente (nome, sobrenome, data_nascimento, telefone, email_corporativo, nome_usuario, senha)
VALUES ('Fernanda', 'Souza', TO_DATE('1995-07-14', 'YYYY-MM-DD'), '1194321-8765', 'fernanda.souza@empresa.com', 'fernandasouza', 'senha103');

INSERT INTO ch_cliente (nome, sobrenome, data_nascimento, telefone, email_corporativo, nome_usuario, senha)
VALUES ('Ricardo', 'Almeida', TO_DATE('1983-11-22', 'YYYY-MM-DD'), '1195678-1234', 'ricardo.almeida@empresa.com', 'ricardoalmeida', 'senha104');

INSERT INTO ch_cliente (nome, sobrenome, data_nascimento, telefone, email_corporativo, nome_usuario, senha)
VALUES ('Patricia', 'Lima', TO_DATE('1992-06-05', 'YYYY-MM-DD'), '1193456-4321', 'patricia.lima@empresa.com', 'patricialima', 'senha105');

INSERT INTO ch_cliente (nome, sobrenome, data_nascimento, telefone, email_corporativo, nome_usuario, senha)
VALUES ('Thiago', 'Fernandes', TO_DATE('1989-09-17', 'YYYY-MM-DD'), '1199876-5432', 'thiago.fernandes@empresa.com', 'thiagofernandes', 'senha106');

INSERT INTO ch_cliente (nome, sobrenome, data_nascimento, telefone, email_corporativo, nome_usuario, senha)
VALUES ('Juliana', 'Gomes', TO_DATE('1987-04-19', 'YYYY-MM-DD'), '1196543-0987', 'juliana.gomes@empresa.com', 'julianagomes', 'senha107');
commit;


-- Inserindo dois registros na tabela ch_estoque
INSERT INTO ch_estoque (ch_cliente_cod_cliente) VALUES (1);
INSERT INTO ch_estoque (ch_cliente_cod_cliente) VALUES (2);



-- Inserindo trinta registros na tabela ch_produto, associando a cada um um dos estoques criados
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 1', 'Descri��o do Produto 1', 10.00, 50, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 2', 'Descri��o do Produto 2', 15.00, 30, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 3', 'Descri��o do Produto 3', 20.00, 20, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 4', 'Descri��o do Produto 4', 25.00, 40, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 5', 'Descri��o do Produto 5', 30.00, 60, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 6', 'Descri��o do Produto 6', 35.00, 70, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 7', 'Descri��o do Produto 7', 40.00, 80, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 8', 'Descri��o do Produto 8', 45.00, 90, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 9', 'Descri��o do Produto 9', 50.00, 100, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 10', 'Descri��o do Produto 10', 55.00, 110, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 11', 'Descri��o do Produto 11', 60.00, 120, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 12', 'Descri��o do Produto 12', 65.00, 130, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 13', 'Descri��o do Produto 13', 70.00, 140, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 14', 'Descri��o do Produto 14', 75.00, 150, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 15', 'Descri��o do Produto 15', 80.00, 160, 2);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 16', 'Descri��o do Produto 16', 85.00, 170, 1);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 17', 'Descri��o do Produto 17', 90.00, 180, 1);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 18', 'Descri��o do Produto 18', 95.00, 190, 1);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 19', 'Descri��o do Produto 19', 100.00, 200, 1);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 20', 'Descri��o do Produto 20', 105.00, 210, 1);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 21', 'Descri��o do Produto 21', 110.00, 220, 1);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 22', 'Descri��o do Produto 22', 115.00, 230, 1);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 23', 'Descri��o do Produto 23', 120.00, 240, 1);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 24', 'Descri��o do Produto 24', 125.00, 250, 1);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 25', 'Descri��o do Produto 25', 130.00, 260, 1);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 26', 'Descri��o do Produto 26', 135.00, 270, 1);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 27', 'Descri��o do Produto 27', 140.00, 280, 1);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 28', 'Descri��o do Produto 28', 145.00, 290, 1);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 29', 'Descri��o do Produto 29', 150.00, 300, 1);
INSERT INTO ch_produto (nome, descricao, preco, estoque, ch_estoque_cod_estoque) VALUES ('Produto 30', 'Descri��o do Produto 30', 155.00, 310, 1);
commit;

--Listar clientes ordenados pelo sobrenome e nome
--Relat�rio utilizando classifica��o de dados
SELECT nome, sobrenome, data_nascimento, telefone, email_corporativo 
FROM ch_cliente 
ORDER BY sobrenome, nome;


--Mostrar todos os produtos agrupados pelo nome dos clientes que o possuem
--Classifica��o de Dados e Jun��o de Tabelas
select
    c.nome || ' ' || c.sobrenome AS nome_completo,
    p.cod_produto,
    p.nome as Nome_Produto,
    p.preco,
    p.estoque
FROM
    ch_produto p
JOIN 
    ch_estoque e ON p.ch_estoque_cod_estoque = e.cod_estoque
JOIN
    ch_cliente c ON e.ch_cliente_cod_cliente = c.cod_cliente
ORDER BY
    p.cod_produto;


--Mostrar a quantidade total de itens em estoque por pessoa e o valor total dos itens em estoque   
--Fun��o Num�rica Simples, Fun��o de Grupo e Jun��o de Tabelas
SELECT 
    c.nome || ' ' || c.sobrenome AS nome_completo,
    SUM(p.estoque) AS quantidade_total_itens,
    TO_CHAR(SUM(p.preco * p.estoque), 'L9G999G999D99') AS valor_total_estoque
FROM 
    ch_cliente c
JOIN 
    ch_estoque e ON c.cod_cliente = e.ch_cliente_cod_cliente
JOIN 
    ch_produto p ON e.cod_estoque = p.ch_estoque_cod_estoque
GROUP BY 
    c.nome, c.sobrenome
ORDER BY 
    c.nome, c.sobrenome;


--Relat�rio que mostra os bairros na cidade de S�o Paulo.    
--Relat�rio utilizando sub consulta
SELECT nome 
FROM ch_bairro 
WHERE ch_cidade_cod_cidade = (SELECT cod_cidade FROM ch_cidade WHERE nome = 'S�o Paulo');

--Relat�rio que mostra todos os clientes que n�p possuem itens em estoque
--Classifica��o de Dados e Jun��o de Tabelas
SELECT 
    c.nome || ' ' || c.sobrenome AS nome_completo,
    c.telefone,
    c.email_corporativo,
    c.nome_usuario
FROM 
    ch_cliente c
LEFT JOIN 
    ch_estoque e ON c.cod_cliente = e.ch_cliente_cod_cliente
LEFT JOIN 
    ch_produto p ON e.cod_estoque = p.ch_estoque_cod_estoque
WHERE 
    p.cod_produto IS NULL
ORDER BY 
    nome_completo;

