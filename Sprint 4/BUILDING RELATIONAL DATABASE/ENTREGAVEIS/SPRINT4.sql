-- Mauricio Vieira Pereira    RM553748
-- Vitor Onofre Ramos         RM553241
-- Luiz Otávio Leitão         RM553542


-- DROP TABLES PARA ELIMINAR POSSIVEIS TABELAS JA EXISTENTES
DROP TABLE ch_armazem CASCADE CONSTRAINTS;
DROP TABLE ch_bairro CASCADE CONSTRAINTS;
DROP TABLE ch_cidade CASCADE CONSTRAINTS;
DROP TABLE ch_cliente CASCADE CONSTRAINTS;
DROP TABLE ch_endereco CASCADE CONSTRAINTS;
DROP TABLE ch_estado CASCADE CONSTRAINTS;
DROP TABLE ch_pais CASCADE CONSTRAINTS;
DROP TABLE ch_produto CASCADE CONSTRAINTS;

-- predefined type, no DDL - MDSYS.SDO_GEOMETRY
-- predefined type, no DDL - XMLTYPE

CREATE TABLE ch_armazem (
    cod_armazem            NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    qtd_total              NUMBER,
    ch_cliente_cod_cliente NUMBER NOT NULL
);

CREATE UNIQUE INDEX ch_armazem__idx ON ch_armazem (ch_cliente_cod_cliente);

CREATE TABLE ch_bairro (
    cod_bairro           NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome                 VARCHAR2(50),
    ch_cidade_cod_cidade NUMBER NOT NULL
);

CREATE TABLE ch_cidade (
    cod_cidade           NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome                 VARCHAR2(50),
    ch_estado_cod_estado NUMBER NOT NULL
);

CREATE TABLE ch_cliente (
    cod_cliente       NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome              VARCHAR2(50),
    sobrenome         VARCHAR2(80),
    data_nascimento   DATE,
    telefone          VARCHAR2(14),
    email_corporativo VARCHAR2(50),
    nome_usuario      VARCHAR2(20),
    senha             VARCHAR2(50)
);

CREATE TABLE ch_endereco (
    cod_endereco           NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    logradouro             VARCHAR2(250),
    numero                 NUMBER,
    complemento            VARCHAR2(50),
    cep                    NUMBER,
    ch_cliente_cod_cliente NUMBER NOT NULL,
    ch_bairro_cod_bairro   NUMBER NOT NULL
);

CREATE UNIQUE INDEX ch_endereco__idx ON ch_endereco (ch_cliente_cod_cliente);

CREATE TABLE ch_estado (
    cod_estado       NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome             VARCHAR2(50),
    ch_pais_cod_pais NUMBER NOT NULL
);

CREATE TABLE ch_pais (
    cod_pais NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome     VARCHAR2(50)
);

CREATE TABLE ch_produto (
    cod_produto            NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome                   VARCHAR2(100),
    descricao              VARCHAR2(250),
    preco                  NUMBER,
    ch_armazem_cod_armazem NUMBER NOT NULL,
    quantidade             NUMBER
);

ALTER TABLE ch_armazem
    ADD CONSTRAINT ch_armazem_ch_cliente_fk FOREIGN KEY (ch_cliente_cod_cliente)
        REFERENCES ch_cliente (cod_cliente)
    NOT DEFERRABLE;

ALTER TABLE ch_bairro
    ADD CONSTRAINT ch_bairro_ch_cidade_fk FOREIGN KEY (ch_cidade_cod_cidade)
        REFERENCES ch_cidade (cod_cidade)
    NOT DEFERRABLE;

ALTER TABLE ch_cidade
    ADD CONSTRAINT ch_cidade_ch_estado_fk FOREIGN KEY (ch_estado_cod_estado)
        REFERENCES ch_estado (cod_estado)
    NOT DEFERRABLE;

ALTER TABLE ch_endereco
    ADD CONSTRAINT ch_endereco_ch_bairro_fk FOREIGN KEY (ch_bairro_cod_bairro)
        REFERENCES ch_bairro (cod_bairro)
    NOT DEFERRABLE;

ALTER TABLE ch_endereco
    ADD CONSTRAINT ch_endereco_ch_cliente_fk FOREIGN KEY (ch_cliente_cod_cliente)
        REFERENCES ch_cliente (cod_cliente)
    NOT DEFERRABLE;

ALTER TABLE ch_estado
    ADD CONSTRAINT ch_estado_ch_pais_fk FOREIGN KEY (ch_pais_cod_pais)
        REFERENCES ch_pais (cod_pais)
    NOT DEFERRABLE;

ALTER TABLE ch_produto
    ADD CONSTRAINT ch_produto_ch_armazem_fk FOREIGN KEY (ch_armazem_cod_armazem)
        REFERENCES ch_armazem (cod_armazem)
    NOT DEFERRABLE;
    

--Trigger para alterar o a quantidade total de produtos baseado nos itens cadastrados
CREATE OR REPLACE TRIGGER trg_update_qtd_total
AFTER INSERT OR UPDATE OR DELETE ON ch_produto
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        UPDATE ch_armazem
        SET qtd_total = NVL(qtd_total, 0) + :NEW.quantidade
        WHERE cod_armazem = :NEW.ch_armazem_cod_armazem;
    ELSIF UPDATING THEN
        IF :OLD.ch_armazem_cod_armazem != :NEW.ch_armazem_cod_armazem THEN
            UPDATE ch_armazem
            SET qtd_total = NVL(qtd_total, 0) - NVL(:OLD.quantidade, 0)
            WHERE cod_armazem = :OLD.ch_armazem_cod_armazem;

            UPDATE ch_armazem
            SET qtd_total = NVL(qtd_total, 0) + NVL(:NEW.quantidade, 0)
            WHERE cod_armazem = :NEW.ch_armazem_cod_armazem;
        ELSE
            UPDATE ch_armazem
            SET qtd_total = NVL(qtd_total, 0) - NVL(:OLD.quantidade, 0) + NVL(:NEW.quantidade, 0)
            WHERE cod_armazem = :NEW.ch_armazem_cod_armazem;
        END IF;
    ELSIF DELETING THEN
        UPDATE ch_armazem
        SET qtd_total = NVL(qtd_total, 0) - NVL(:OLD.quantidade, 0)
        WHERE cod_armazem = :OLD.ch_armazem_cod_armazem;
    END IF;
END;
/

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
SELECT object_name, status
FROM user_objects
WHERE object_type = 'TRIGGER' AND object_name = 'TRG_UPDATE_QTD_TOTAL';


-- Inserindo 10 registros na tabela ch_cliente
INSERT INTO ch_cliente (nome, sobrenome, data_nascimento, telefone, email_corporativo, nome_usuario, senha)
VALUES ('João', 'Silva', TO_DATE('1985-05-15', 'YYYY-MM-DD'), '1198765-4321', 'joao.silva@empresa.com', 'joaosilva', 'senha123');

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


-- Inserindo dois registros na tabela ch_Armazem
-- Inserindo dois registros na tabela ch_armazem com clientes únicos
INSERT INTO ch_armazem (ch_cliente_cod_cliente) VALUES (1);
INSERT INTO ch_armazem (ch_cliente_cod_cliente) VALUES (2);



-- Inserindo trinta registros na tabela ch_produto, associando a cada um um dos quantidades criados
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 1', 'Descrição do Produto 1', 10.00, 50, 2);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 2', 'Descrição do Produto 2', 15.00, 30, 2);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 3', 'Descrição do Produto 3', 20.00, 20, 2);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 4', 'Descrição do Produto 4', 25.00, 40, 2);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 5', 'Descrição do Produto 5', 30.00, 60, 2);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 6', 'Descrição do Produto 6', 35.00, 70, 2);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 7', 'Descrição do Produto 7', 40.00, 80, 2);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 8', 'Descrição do Produto 8', 45.00, 90, 2);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 9', 'Descrição do Produto 9', 50.00, 100, 2);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 10', 'Descrição do Produto 10', 55.00, 110, 2);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 11', 'Descrição do Produto 11', 60.00, 120, 2);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 12', 'Descrição do Produto 12', 65.00, 130, 2);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 13', 'Descrição do Produto 13', 70.00, 140, 2);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 14', 'Descrição do Produto 14', 75.00, 150, 2);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 15', 'Descrição do Produto 15', 80.00, 160, 2);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 16', 'Descrição do Produto 16', 85.00, 170, 1);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 17', 'Descrição do Produto 17', 90.00, 180, 1);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 18', 'Descrição do Produto 18', 95.00, 190, 1);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 19', 'Descrição do Produto 19', 100.00, 200, 1);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 20', 'Descrição do Produto 20', 105.00, 210, 1);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 21', 'Descrição do Produto 21', 110.00, 220, 1);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 22', 'Descrição do Produto 22', 115.00, 230, 1);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 23', 'Descrição do Produto 23', 120.00, 240, 1);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 24', 'Descrição do Produto 24', 125.00, 250, 1);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 25', 'Descrição do Produto 25', 130.00, 260, 1);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 26', 'Descrição do Produto 26', 135.00, 270, 1);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 27', 'Descrição do Produto 27', 140.00, 280, 1);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 28', 'Descrição do Produto 28', 145.00, 290, 1);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 29', 'Descrição do Produto 29', 150.00, 300, 1);
INSERT INTO ch_produto (nome, descricao, preco, quantidade, ch_armazem_cod_armazem) VALUES ('Produto 30', 'Descrição do Produto 30', 155.00, 310, 1);
commit;

--Listar clientes ordenados pelo sobrenome e nome
--Relatório utilizando classificação de dados
SELECT nome, sobrenome, data_nascimento, telefone, email_corporativo 
FROM ch_cliente 
ORDER BY sobrenome, nome;


--Mostrar todos os produtos agrupados pelo nome dos clientes que o possuem
--Classificação de Dados e Junção de Tabelas
select
    c.nome || ' ' || c.sobrenome AS nome_completo,
    p.cod_produto,
    p.nome as Nome_Produto,
    p.preco,
    p.quantidade
FROM
    ch_produto p
JOIN 
    ch_armazem e ON p.ch_armazem_cod_armazem = e.cod_armazem
JOIN
    ch_cliente c ON e.ch_cliente_cod_cliente = c.cod_cliente
ORDER BY
    p.cod_produto;


--Mostrar a quantidade total de itens em quantidade por pessoa e o valor total dos itens em quantidade   
--Função Numérica Simples, Função de Grupo e Junção de Tabelas
SELECT 
    c.nome || ' ' || c.sobrenome AS nome_completo,
    SUM(p.quantidade) AS quantidade_total_itens,
    TO_CHAR(SUM(p.preco * p.quantidade), 'L9G999G999D99') AS valor_total_quantidade
FROM 
    ch_cliente c
JOIN 
    ch_armazem e ON c.cod_cliente = e.ch_cliente_cod_cliente
JOIN 
    ch_produto p ON e.cod_armazem = p.ch_armazem_cod_armazem
GROUP BY 
    c.nome, c.sobrenome
ORDER BY 
    c.nome, c.sobrenome;


--Relatório que mostra os bairros na cidade de São Paulo.    
--Relatório utilizando sub consulta
SELECT nome 
FROM ch_bairro 
WHERE ch_cidade_cod_cidade = (SELECT cod_cidade FROM ch_cidade WHERE nome = 'São Paulo');

--Relatório que mostra todos os clientes que nãp possuem itens em quantidade
--Classificação de Dados e Junção de Tabelas
SELECT 
    c.nome || ' ' || c.sobrenome AS nome_completo,
    c.telefone,
    c.email_corporativo,
    c.nome_usuario
FROM 
    ch_cliente c
LEFT JOIN 
    ch_armazem e ON c.cod_cliente = e.ch_cliente_cod_cliente
LEFT JOIN 
    ch_produto p ON e.cod_armazem = p.ch_armazem_cod_armazem
WHERE 
    p.cod_produto IS NULL
ORDER BY 
    nome_completo;
    
    select * from ch_cliente;

