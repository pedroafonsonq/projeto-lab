-- 1. Criação do Banco de Dados
CREATE DATABASE IF NOT EXISTS controle_laboratorio;
USE controle_laboratorio;

-- 2. Criação das Tabelas
CREATE TABLE aluno (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       nome VARCHAR(100) NOT NULL,
                       matricula VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE equipamento (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             nome VARCHAR(100) NOT NULL,
                             disponivel BOOLEAN DEFAULT TRUE
);

CREATE TABLE emprestimo (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            id_aluno INT,
                            id_equipamento INT,
                            data_emprestimo TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            data_devolucao TIMESTAMP NULL,
                            status ENUM('ATIVO', 'FINALIZADO') DEFAULT 'ATIVO',
                            FOREIGN KEY (id_aluno) REFERENCES aluno(id),
                            FOREIGN KEY (id_equipamento) REFERENCES equipamento(id)
);

-- 3. Dados Iniciais para Teste (Requisito da Imagem 2)
INSERT INTO equipamento (nome, disponivel) VALUES ('Notebook Dell 01', TRUE);
INSERT INTO equipamento (nome, disponivel) VALUES ('Projetor Epson', TRUE);
INSERT INTO aluno (nome, matricula) VALUES ('Aluno Teste', '2024IFPA01');