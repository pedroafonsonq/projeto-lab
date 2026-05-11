package br.edu.ifpa.laboratorio.dao;

import br.edu.ifpa.laboratorio.database.ConexaoMySQL;
import br.edu.ifpa.laboratorio.model.Equipamento;
import java.sql.*;

public class EquipamentoDAO {

    public void cadastrar(Equipamento eq) {
        String sql = "INSERT INTO equipamento (nome, disponivel) VALUES (?, ?)";
        try (Connection conn = ConexaoMySQL.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eq.getNome());
            stmt.setBoolean(2, true);
            stmt.execute();
            System.out.println("Equipamento " + eq.getNome() + " cadastrado!");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void listarDisponiveis() {
        String sql = "SELECT * FROM equipamento WHERE disponivel = true";
        try (Connection conn = ConexaoMySQL.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n--- LISTA DE EQUIPAMENTOS DISPONÍVEIS ---");
            boolean temItens = false;
            while (rs.next()) {
                temItens = true;
                System.out.println("ID: " + rs.getInt("id") + " | Nome: " + rs.getString("nome"));
            }
            if (!temItens) System.out.println("Nenhum equipamento disponível no momento.");
        } catch (SQLException e) { e.printStackTrace(); }
    }
}