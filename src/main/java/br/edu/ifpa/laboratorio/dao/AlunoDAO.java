package br.edu.ifpa.laboratorio.dao;
import br.edu.ifpa.laboratorio.database.ConexaoMySQL;
import br.edu.ifpa.laboratorio.model.Aluno;
import java.sql.*;

public class AlunoDAO {
    public void cadastrar(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, matricula) VALUES (?, ?)";
        try (Connection conn = ConexaoMySQL.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.execute();
            System.out.println("Aluno " + aluno.getNome() + " cadastrado!");
        } catch (SQLException e) { e.printStackTrace(); }
    }
}