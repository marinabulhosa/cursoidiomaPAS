package view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/*** Tela principal do sistema com acesso aos módulos.*/
public class MenuPrincipalView extends JFrame {

    public MenuPrincipalView() {
    setTitle("Sistema de Curso de Idiomas");
    setSize(600, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new GridLayout(4, 2, 10, 10));

    addButton("Alunos", this::abrirAluno);
    addButton("Professores", this::abrirProfessor);
    addButton("Funcionários", this::abrirFuncionario);
    addButton("Turmas", this::abrirTurma);
    addButton("Aulas", this::abrirAula);
    addButton("Notas", this::abrirNota);
    addButton("Gastos", this::abrirGasto);
    addButton("Relatórios", this::abrirRelatorio);

    setLocationRelativeTo(null); // Centraliza a janela
    setVisible(true);
    }

    private void addButton(String texto, java.awt.event.ActionListener listener) {
        JButton botao = new JButton(texto);
        botao.addActionListener(listener);
        add(botao);
    }

    private void abrirAluno(ActionEvent e) {
        new AlunoView();
    }

    private void abrirProfessor(ActionEvent e) {
        new ProfessorView();
    }

    private void abrirFuncionario(ActionEvent e) {
        new FuncionarioView();
    }

    private void abrirTurma(ActionEvent e) {
        new TurmaView();
    }

    private void abrirAula(ActionEvent e) {
        new AulaView();
    }

    private void abrirNota(ActionEvent e) {
        new NotaView();
    }

    private void abrirGasto(ActionEvent e) {
        new GastoView();
    }

    private void abrirRelatorio(ActionEvent e) {
        new RelatorioView();
    }
}



//private void addButton(String texto, AbstractAction action) { JButton botao = new JButton(action); botao.setText(texto); add(botao);}*//