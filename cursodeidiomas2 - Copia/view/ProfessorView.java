package view;

import controller.ProfessorController;
import model.Professor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

public class ProfessorView extends JFrame {
    private JTextField txtMatricula, txtNome, txtEndereco, txtTelefone, txtValorHora, txtLinguas; //faltou linguas
    private JTextArea txtLista;
    private ProfessorController controller = new ProfessorController();

    public ProfessorView() {
        setTitle("Cadastro de Professor");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelForm = new JPanel(new GridLayout(7, 2));
        painelForm.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        painelForm.add(txtMatricula);

        painelForm.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelForm.add(txtNome);

        painelForm.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        painelForm.add(txtEndereco);

        painelForm.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        painelForm.add(txtTelefone);

        painelForm.add(new JLabel("Valor Hora:"));
        txtValorHora = new JTextField();
        painelForm.add(txtValorHora);

        painelForm.add(new JLabel("Linguas:"));
        txtLinguas = new JTextField();
        painelForm.add(txtLinguas);

        add(painelForm, BorderLayout.NORTH);

        // Botões de ação
        JPanel botoes = new JPanel(new GridLayout(1, 5));
        JButton btnSalvar = new JButton("Salvar Professor");
        btnSalvar.addActionListener(this::salvar);
        botoes.add(btnSalvar);

        JButton btnAtualizar = new JButton("Atualizar Professor");
        btnAtualizar.addActionListener(this::atualizar);
        botoes.add(btnAtualizar);

        JButton btnExcluir = new JButton("Excluir Professor");
        btnExcluir.addActionListener(this::excluir);
        botoes.add(btnExcluir);

        JButton btnBuscar = new JButton("Buscar Professor");
        btnBuscar.addActionListener(this::buscar);
        botoes.add(btnBuscar);

        JButton btnListar = new JButton("Listar Todos os Professor");
        btnListar.addActionListener(this::listar);
        botoes.add(btnListar);

        txtLista = new JTextArea();
        txtLista.setEditable(false);
        add(botoes, BorderLayout.CENTER);
        add(new JScrollPane(txtLista), BorderLayout.CENTER);

        setVisible(true);
    }

    private void salvar(ActionEvent e) {
        try {
            List<String> linguas = Arrays.asList(txtLinguas.getText().split(",\s*"));
            Professor p = new Professor(
                txtMatricula.getText(),
                txtNome.getText(),
                txtEndereco.getText(),
                txtTelefone.getText(),
                Double.parseDouble(txtValorHora.getText()),
                linguas
            );
            controller.salvar(p);
            JOptionPane.showMessageDialog(this, "Professor salvo com sucesso!");
            listar(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void atualizar(ActionEvent e) {
        try {
            List<String> linguas = Arrays.asList(txtLinguas.getText().split(",\s*"));
            Professor professor = new Professor(
                txtMatricula.getText(),
                txtNome.getText(),
                txtEndereco.getText(),
                txtTelefone.getText(),
                Double.parseDouble(txtValorHora.getText()), 
                linguas
            );
            controller.atualizar(professor);
            JOptionPane.showMessageDialog(this, "Professor atualizado com sucesso!");
            listar(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar professor: " + ex.getMessage());
        }
    }

    private void excluir (ActionEvent e) {
        try {
            String matricula = txtMatricula.getText();
            controller.excluir(matricula);
            JOptionPane.showMessageDialog(this, "Professor excluído com sucesso!");
            listar(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir professor: " + ex.getMessage());
        }
    }

    private void buscar(ActionEvent e){
        try {
            String matricula = txtMatricula.getText();
            Professor professor = controller.buscarPorMatricula(matricula);
            if (professor != null) {
                txtNome.setText(professor.getNome());
                txtEndereco.setText(professor.getEndereco());
                txtTelefone.setText(professor.getTelefone());
                txtValorHora.setText(String.valueOf(professor.getValorHora()));
                txtLinguas.setText(String.join(", ", professor.getLinguas()));
                JOptionPane.showMessageDialog(this, "Professor encontrado.");
            } else {
                JOptionPane.showMessageDialog(this, "Professor não encontrado.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar professor: " + ex.getMessage());
        }
    }


    private void listar(ActionEvent e) {
        try {
            List<Professor> lista = controller.listar();
            StringBuilder sb = new StringBuilder();
            for (Professor p : lista) {
                sb.append(p).append("\n");
            }
            txtLista.setText(sb.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar professores: " + ex.getMessage());
        }
    }
}