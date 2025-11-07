-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS dashboard_populacional;
USE dashboard_populacional;

-- Tabela de estados
CREATE TABLE estado (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  sigla CHAR(2) NOT NULL UNIQUE
);

-- Tabela de municípios
CREATE TABLE municipio (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  populacao INT NOT NULL,
  ano INT NOT NULL,
  id_estado INT NOT NULL,
  FOREIGN KEY (id_estado) REFERENCES estado(id)
);

-- Dados de exemplo
INSERT INTO estado (nome, sigla) VALUES 
('São Paulo', 'SP'),
('Rio de Janeiro', 'RJ'),
('Minas Gerais', 'MG');

INSERT INTO municipio (nome, populacao, ano, id_estado) VALUES 
('São Paulo', 12000000, 2023, 1),
('Campinas', 1200000, 2023, 1),
('Rio de Janeiro', 6700000, 2023, 2),
('Niterói', 500000, 2023, 2),
('Belo Horizonte', 2500000, 2023, 3);