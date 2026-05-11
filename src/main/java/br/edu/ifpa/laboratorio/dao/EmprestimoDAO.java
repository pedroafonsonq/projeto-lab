package br.edu.ifpa.laboratorio.dao;

import br.edu.ifpa.laboratorio.database.ConexaoMySQL;
import java.sql.*;

public class EmprestimoDAO {

    /**
     * Realiza o empréstimo verificando se o equipamento está disponível.
     * Segue a regra de negócio exigida pelo professor.
     */
    public void realizarEmprestimo(int idAluno, int idEquipamento) {
        String sqlVerificar = "SELECT disponivel FROM equipamento WHERE id = ?";
        String sqlEmprestimo = "INSERT INTO emprestimo (id_aluno, id_equipamento, status) VALUES (?, ?, 'ATIVO')";
        String sqlUpdateEquip = "UPDATE equipamento SET disponivel = false WHERE id = ?";

        try (Connection conn = ConexaoMySQL.getConexao()) {
            conn.setAutoCommit(false); // Inicia transação para garantir que tudo ocorra ou nada ocorra

            // 1. Verifica se o equipamento existe e está disponível
            try (PreparedStatement stmtCheck = conn.prepareStatement(sqlVerificar)) {
                stmtCheck.setInt(1, idEquipamento);
                ResultSet rs = stmtCheck.executeQuery();

                if (rs.next()) {
                    boolean disponivel = rs.getBoolean("disponivel");
                    if (!disponivel) {
                        System.out.println("❌ ERRO: Equipamento ID " + idEquipamento + " já está ocupado!");
                        return;
                    }
                } else {
                    System.out.println("❌ ERRO: Equipamento não encontrado!");
                    return;
                }
            }

            // 2. Se chegou aqui, está disponível. Registra o empréstimo e "tranca" o equipamento
            try (PreparedStatement stmtEmp = conn.prepareStatement(sqlEmprestimo);
                 PreparedStatement stmtUpd = conn.prepareStatement(sqlUpdateEquip)) {

                stmtEmp.setInt(1, idAluno);
                stmtEmp.setInt(2, idEquipamento);
                stmtEmp.execute();

                stmtUpd.setInt(1, idEquipamento);
                stmtUpd.execute();

                conn.commit(); // Finaliza a transação com sucesso
                System.out.println("✅ SUCESSO: Empréstimo registrado!");
            } catch (SQLException e) {
                conn.rollback(); // Se der erro em qualquer SQL, desfaz tudo
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao processar transação de empréstimo.");
            e.printStackTrace();
        }
    }

    /**
     * Registra a devolução, libera o equipamento e finaliza o status.
     */
    public void registrarDevolucao(int idEmprestimo, int idEquipamento) {
        String sqlDevolucao = "UPDATE emprestimo SET status = 'FINALIZADO', data_devolucao = CURRENT_TIMESTAMP WHERE id = ?";
        String sqlUpdateEquip = "UPDATE equipamento SET disponivel = true WHERE id = ?";

        try (Connection conn = ConexaoMySQL.getConexao()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtDev = conn.prepareStatement(sqlDevolucao);
                 PreparedStatement stmtUpd = conn.prepareStatement(sqlUpdateEquip)) {

                stmtDev.setInt(1, idEmprestimo);
                stmtDev.execute();

                stmtUpd.setInt(1, idEquipamento);
                stmtUpd.execute();

                conn.commit();
                System.out.println("✅ SUCESSO: Devolução registrada. Item livre!");
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
