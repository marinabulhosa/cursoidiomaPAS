package view;

import controller.NotaController;
import model.Nota;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.List;

/**Interface gráfica para cadastro e gerenciamento de notas aos alunos nas turmas.*/
public class NotaView extends JFrame {
    private JTextField txtTurma, txtMatricula, txtNome, txtNota;
    private JTextArea txtLista;
    private NotaController controller = new NotaController();

    public NotaView() {
        setTitle("Gerenciar Notas");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

         // Painel de formulário (inputs)
        JPanel form = new JPanel(new GridLayout(4, 2));
        form.add(new JLabel("ID da Turma:"));
        txtTurma = new JTextField();
        form.add(txtTurma);

        form.add(new JLabel("Matricula do Aluno:"));
        txtMatricula = new JTextField();
        form.add(txtMatricula);

        form.add(new JLabel("Nome do Aluno:"));
        txtNome = new JTextField();
        form.add(txtNome);

        form.add(new JLabel("Nota do Aluno:"));
        txtNota = new JTextField();
        form.add(txtNota);

        JPanel botoes = new JPanel(new GridLayout(1, 5));

        JButton btnSalvar = new JButton("Salvar Nota");
        btnSalvar.addActionListener(this::salvar);
        botoes.add(btnSalvar);

        JButton btnAtualizar = new JButton("Atualizar Nota"); 
        btnAtualizar.addActionListener(this::atualizar);
        botoes.add(btnAtualizar);

        JButton btnExcluir = new JButton("Excluir Nota"); 
        btnExcluir.addActionListener(this::excluir);
        botoes.add(btnExcluir);

        JButton btnBuscarPorTurma = new JButton("Buscar Nota Por Turma"); 
        btnBuscarPorTurma.addActionListener(this::buscarPorTurma);
        botoes.add(btnBuscarPorTurma);

        JButton btnBuscarPorMatricula = new JButton("Buscar Nota Por Matricula"); 
        btnBuscarPorMatricula.addActionListener(this::buscarPorMatricula);
        botoes.add(btnBuscarPorMatricula);

        JButton btnListar = new JButton("Listar Notas");
        btnListar.addActionListener(this::listar);
        botoes.add(btnListar);

        add(form, BorderLayout.NORTH);
        add(botoes, BorderLayout.CENTER);

        txtLista = new JTextArea();
        txtLista.setEditable(false);
        add(new JScrollPane(txtLista), BorderLayout.CENTER);

        setVisible(true);
    }

    private void salvar(ActionEvent e) {
        try {
            Nota n = new Nota(
            Integer.parseInt(txtTurma.getText()),
            txtMatricula.getText(),
            txtNome.getText(),
            Double.parseDouble(txtNota.getText())
            );
            controller.salvar(n);
            JOptionPane.showMessageDialog(this, "Nota salva com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar nota: " + ex.getMessage());
        }
    }

    private void atualizar(ActionEvent e) {
        try {
            Nota n = new Nota(
            Integer.parseInt(txtTurma.getText()),
            txtMatricula.getText(),
            txtNome.getText(),
            Double.parseDouble(txtNota.getText())
            );
            controller.atualizar(n);
            JOptionPane.showMessageDialog(this, "Nota atualizada com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar nota: " + ex.getMessage());
        }
    }

    private void excluir(ActionEvent e) {
        try {
            int turmaId = Integer.parseInt(txtTurma.getText());
            String matricula = txtMatricula.getText();
            controller.excluir(turmaId, matricula);
            JOptionPane.showMessageDialog(this, "Nota excluída com sucesso!");
            listar(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir nota: " + ex.getMessage());
        }
    }

    private void buscarPorTurma(ActionEvent e) {
        try {
            int turmaId = Integer.parseInt(txtTurma.getText());
            List<Nota> notas = controller.buscarPorTurma(turmaId);
            mostrarNotas(notas);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar por turma: " + ex.getMessage());
        }
    }

    private void buscarPorMatricula(ActionEvent e) {
        try {
            String matricula = txtMatricula.getText();
            List<Nota> notas = controller.buscarPorMatricula(matricula);
            mostrarNotas(notas);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar por matrícula: " + ex.getMessage());
        }
    }

    private void listar(ActionEvent e) {
        try {
            List<Nota> notas = controller.listar();
            mostrarNotas(notas);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar notas: " + ex.getMessage());
        }
    }

    private void mostrarNotas(List<Nota> notas) {
        txtLista.setText("");
        for (Nota n : notas) {
            txtLista.append("Turma: " + n.getTurmaId() + ", Matrícula: " + n.getAlunoMatricula()
                    + ", Nome: " + n.getAlunoNome() + ", Nota: " + n.getValor() + "\n");
        }
    }
}

