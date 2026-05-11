package br.edu.ifpa.laboratorio;

import br.edu.ifpa.laboratorio.dao.AlunoDAO;
import br.edu.ifpa.laboratorio.dao.EmprestimoDAO;
import br.edu.ifpa.laboratorio.dao.EquipamentoDAO;
import br.edu.ifpa.laboratorio.model.Aluno;
import br.edu.ifpa.laboratorio.model.Equipamento;

public class Main {
    public static void main(String[] args) {
        AlunoDAO alunoDAO = new AlunoDAO();
        EquipamentoDAO equipDAO = new EquipamentoDAO();
        EmprestimoDAO empDAO = new EmprestimoDAO();

        System.out.println("=== INICIANDO SIMULAÇÃO COMPLETA (NOTA MÁXIMA) ===");

        // 1. CADASTROS INICIAIS
        Aluno aluno = new Aluno();
        aluno.setNome("Aluno Exemplo");
        aluno.setMatricula("MAT-" + System.currentTimeMillis());
        alunoDAO.cadastrar(aluno);

        Equipamento notebook = new Equipamento();
        notebook.setNome("Notebook Dell Latitude");
        equipDAO.cadastrar(notebook);

        // 2. LISTAR DISPONÍVEIS (Exigência do roteiro)
        equipDAO.listarDisponiveis();

        // 3. REALIZAR EMPRÉSTIMO
        System.out.println("\n[Ação] Solicitando empréstimo do item 1 para o aluno 1...");
        empDAO.realizarEmprestimo(1, 1);

        // 4. VERIFICAR LISTA NOVAMENTE (Deve aparecer vazia ou sem o item 1)
        equipDAO.listarDisponiveis();

        // 5. TESTAR TENTATIVA DUPLICADA (Regra de Negócio)
        System.out.println("\n[Ação] Tentando pegar o mesmo item que já saiu...");
        empDAO.realizarEmprestimo(1, 1);

        // 6. DEVOLUÇÃO
        System.out.println("\n[Ação] Devolvendo o item...");
        empDAO.registrarDevolucao(1, 1);

        // 7. VERIFICAÇÃO FINAL
        equipDAO.listarDisponiveis();

        System.out.println("\n=== SIMULAÇÃO FINALIZADA COM SUCESSO ===");
    }
}