package view;

import controller.AlunoController;
import model.Aluno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Interface gráfica para o módulo de Alunos com funcionalidades CRUD.
 * Agora inclui tratamento de exceções em todos os métodos.
 */
public class AlunoView extends JFrame {

    private JTextField txtMatricula, txtNome, txtEndereco, txtTelefone, txtEmail;
    private JTextArea txtLista;
    private AlunoController controller = new AlunoController();

    public AlunoView() {
        setTitle("Cadastro de Alunos");
        setSize(600, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Formulário de entrada
        JPanel form = new JPanel(new GridLayout(6, 2));
        form.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        form.add(txtMatricula);

        form.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        form.add(txtNome);

        form.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        form.add(txtEndereco);

        form.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        form.add(txtTelefone);

        form.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        form.add(txtEmail);

        // Botões de ação
        JPanel botoes = new JPanel(new GridLayout(1, 5));
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this::salvar);
        botoes.add(btnSalvar);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(this::atualizar);
        botoes.add(btnAtualizar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(this::excluir);
        botoes.add(btnExcluir);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(this::buscar);
        botoes.add(btnBuscar);

        JButton btnListar = new JButton("Listar Todos");
        btnListar.addActionListener(this::listar);
        botoes.add(btnListar);

        // Área de listagem
        txtLista = new JTextArea();
        txtLista.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtLista);

        add(form, BorderLayout.NORTH);
        add(botoes, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void salvar(ActionEvent e) {
        try {
            Aluno aluno = new Aluno(
                txtMatricula.getText(),
                txtNome.getText(),
                txtEndereco.getText(),
                txtTelefone.getText(),
                txtEmail.getText()
            );
            controller.salvar(aluno);
            JOptionPane.showMessageDialog(this, "Aluno salvo com sucesso!");
            listar(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar aluno: " + ex.getMessage());
        }
    }

    private void atualizar(ActionEvent e) {
        try {
            Aluno aluno = new Aluno(
                txtMatricula.getText(),
                txtNome.getText(),
                txtEndereco.getText(),
                txtTelefone.getText(),
                txtEmail.getText()
            );
            controller.atualizar(aluno);
            JOptionPane.showMessageDialog(this, "Aluno atualizado com sucesso!");
            listar(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar aluno: " + ex.getMessage());
        }
    }

    private void excluir(ActionEvent e) {
        try {
            String matricula = txtMatricula.getText();
            controller.excluir(matricula);
            JOptionPane.showMessageDialog(this, "Aluno excluído com sucesso!");
            listar(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir aluno: " + ex.getMessage());
        }
    }

    private void buscar(ActionEvent e) {
        try {
            String matricula = txtMatricula.getText();
            Aluno aluno = controller.buscarPorMatricula(matricula);
            if (aluno != null) {
                txtNome.setText(aluno.getNome());
                txtEndereco.setText(aluno.getEndereco());
                txtTelefone.setText(aluno.getTelefone());
                txtEmail.setText(aluno.getEmail());
                JOptionPane.showMessageDialog(this, "Aluno encontrado.");
            } else {
                JOptionPane.showMessageDialog(this, "Aluno não encontrado.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar aluno: " + ex.getMessage());
        }
    }

    private void listar(ActionEvent e) {
        try {
            List<Aluno> lista = controller.listar();
            StringBuilder sb = new StringBuilder();
            for (Aluno a : lista) {
                sb.append(a).append("\n");
            }
            txtLista.setText(sb.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar alunos: " + ex.getMessage());
        }
    }
}
