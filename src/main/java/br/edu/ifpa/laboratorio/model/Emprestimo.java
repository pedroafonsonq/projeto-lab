package br.edu.ifpa.laboratorio.model;
import java.sql.Timestamp;

public class Emprestimo {
    private int id;
    private int idAluno;
    private int idEquipamento;
    private Timestamp dataEmprestimo;
    private Timestamp dataDevolucao;
    private String status;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdAluno() { return idAluno; }
    public void setIdAluno(int idAluno) { this.idAluno = idAluno; }
    public int getIdEquipamento() { return idEquipamento; }
    public void setIdEquipamento(int idEquipamento) { this.idEquipamento = idEquipamento; }
    public Timestamp getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(Timestamp dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }
    public Timestamp getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(Timestamp dataDevolucao) { this.dataDevolucao = dataDevolucao; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}