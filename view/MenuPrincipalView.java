package view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/*** Tela principal do sistema com acesso aos módulos.*/
public class MenuPrincipalView extends JFrame {
    public MenuPrincipalView() {
        setTitle("Sistema de Curso de Idiomas");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 1));

        addButton("Alunos", _ -> new AlunoView());
        addButton("Professores", _ -> new ProfessorView());
        addButton("Funcionários", _ -> new FuncionarioView());
        addButton("Turmas", _ -> new TurmaView());
        addButton("Aulas", _ -> new AulaView());
        addButton("Notas", _ -> new NotaView());
        addButton("Gastos", _ -> new GastoView());
        addButton("Relatórios", _ -> new RelatorioView());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addButton(String texto, ActionListener listener ) {
        JButton btn = new JButton(texto);
        btn.addActionListener(listener);
        add(btn);
    }
}


/*
---import java.awt.event.*;-----
    addButton("Relatórios", () -> new RelatorioView());
    private void addButton(String nome, Runnable action) {
        JButton btn = new JButton(nome);
        btn.addActionListener(e -> action.run());
        add(btn);
    }
*/ 