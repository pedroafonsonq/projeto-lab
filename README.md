# Sistema de Controle de Empréstimo de Equipamentos

## Descrição do Problema
O laboratório necessita de um sistema para gerenciar o fluxo de empréstimos de equipamentos para alunos, 
garantindo que um item não seja emprestado em duplicidade e mantendo o histórico de devoluções.

## Requisitos Implementados
* Cadastro de Alunos e Equipamentos.
* Listagem de itens disponíveis em tempo real.
* Registro de empréstimo com validação de disponibilidade.
* Registro de devolução com atualização automática de status.

## Diagramas UML
*Os diagramas foram gerados no Astah e estão anexados na pasta /docs.*

## Modelo do banco de dados
* Modelo Relacional (OLTP) implementado em MySQL.
* Relacionamento 1:N entre Aluno/Equipamento e Empréstimos.

## Como criar o banco MySQL
1. Abra o XAMPP e inicie o MySQL e Apache.
2. Acesse http://localhost/phpmyadmin.
3. Importe o arquivo `script_banco.sql` fornecido ou execute o código SQL na aba "SQL".

## Como executar o projeto Java
1. Abra o projeto no IntelliJ IDEA.
2. Certifique-se de que o driver `mysql-connector-j-8.0.33.jar` está adicionado às Libraries.
3. Execute a classe `Main.java` localizada em `br.edu.ifpa.laboratorio`.

## Testes realizados
1. Cadastro de aluno: **OK**
2. Cadastro de equipamento: **OK**
3. Empréstimo válido: **OK**
4. Bloqueio de item indisponível: **OK**
5. Devolução e liberação de item: **OK**

## Decisões de projeto
* Uso do padrão **DAO** para isolar a lógica de banco do código principal.
* Uso de **Transações SQL (commit/rollback)** no EmprestimoDAO para garantir a integridade dos dados (se o empréstimo falhar, o equipamento não é marcado como indisponível).

## Melhorias futuras
* Implementação de uma interface gráfica (PWA ou Swing).
* Sistema de login para coordenadores.