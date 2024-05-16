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


-- Inserindo dois registros na tabela ch_estoque
INSERT INTO ch_estoque (cod_estoque, cod_produto, quantidade, ch_cliente_cod_cliente) VALUES (1, NULL, 100, 1);
INSERT INTO ch_estoque (cod_estoque, cod_produto, quantidade, ch_cliente_cod_cliente) VALUES (2, NULL, 200, 2);

-- Inserindo dois registros na tabela ch_cliente
INSERT INTO ch_cliente (nome, sobrenome, data_nascimento, telefone, email_corporativo, nome_usuario, senha)
VALUES ('João', 'Silva', TO_DATE('1985-05-15', 'YYYY-MM-DD'), '1198765-4321', 'joao.silva@empresa.com', 'joaosilva', 'senha123');
select*from ch_cliente;

INSERT INTO ch_cliente (nome, sobrenome, data_nascimento, telefone, email_corporativo, nome_usuario, senha)
VALUES ('Maria', 'Oliveira', TO_DATE('1990-10-20', 'YYYY-MM-DD'), '1191234-5678', 'maria.oliveira@empresa.com', 'mariaoliveira', 'senha456');



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
