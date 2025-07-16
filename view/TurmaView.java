package view;

import controller.TurmaController;
import model.Turma;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

//Interface gráfica para manipulação de turmas.

public class TurmaView extends JFrame {
    private JTextField txtId, txtInicio, txtFim, txtLingua, txtNivel, txtPreco;
    private JTextArea txtLista;
    private TurmaController controller = new TurmaController();

    public TurmaView() {
        setTitle("Cadastro de Turmas");
        setSize(600, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(6, 2));

        form.add(new JLabel("ID:"));
        txtId = new JTextField();
        form.add(txtId);

        form.add(new JLabel("Data Início (dd/MM/yyyy):"));
        txtInicio = new JTextField();
        form.add(txtInicio);

        form.add(new JLabel("Data Fim (dd/MM/yyyy):"));
        txtFim = new JTextField();
        form.add(txtFim);

        form.add(new JLabel("Língua:"));
        txtLingua = new JTextField();
        form.add(txtLingua);

        form.add(new JLabel("Nível:"));
        txtNivel = new JTextField();
        form.add(txtNivel);

        form.add(new JLabel("Preço:"));
        txtPreco = new JTextField();
        form.add(txtPreco);

        JPanel botoes = new JPanel(new GridLayout(1, 5));

        JButton btnSalvar = new JButton("Salvar Turmas");
        btnSalvar.addActionListener(this::salvar);
        botoes.add(btnSalvar);

        JButton btnAtualizar = new JButton("Atualizar Turmas"); 
        btnAtualizar.addActionListener(this::atualizar);
        botoes.add(btnAtualizar);

        JButton btnExcluir = new JButton("Excluir Turmas"); 
        btnExcluir.addActionListener(this::excluir);
        botoes.add(btnExcluir);

        JButton btnBuscar = new JButton("Buscar Turmas"); 
        btnBuscar.addActionListener(this::buscar);
        botoes.add(btnBuscar);

        JButton btnListar = new JButton("Listar Turmas");
        btnListar.addActionListener(this::listar);
        botoes.add(btnListar);

        add(form, BorderLayout.NORTH);
        add(botoes, BorderLayout.CENTER);
       
        txtLista = new JTextArea();
        txtLista.setEditable(false);
        //JScrollPane scroll = new JScrollPane(txtLista); add(scroll, BorderLayout.SOUTH);
        add(new JScrollPane(txtLista), BorderLayout.CENTER);

        setVisible(true);
    }

        private void salvar(ActionEvent e) {
        try {
            Turma t = new Turma(
                Integer.parseInt(txtId.getText()),
                LocalDate.parse(txtInicio.getText()),
                LocalDate.parse(txtFim.getText()),
                txtLingua.getText(),
                txtNivel.getText(),
                Double.parseDouble(txtPreco.getText()),
                null
            );
            controller.salvar(t);
            JOptionPane.showMessageDialog(this, "Turma salva com sucesso!");
            listar(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
        }
    }

    private void atualizar (ActionEvent e) {
        try {
            Turma t = new Turma(
                Integer.parseInt(txtId.getText()),
                LocalDate.parse(txtInicio.getText()),
                LocalDate.parse(txtFim.getText()),
                txtLingua.getText(),
                txtNivel.getText(),
                Double.parseDouble(txtPreco.getText()),
                null
            );
            controller.atualizar(t);
            JOptionPane.showMessageDialog(this, "Turma atualizada com sucesso!");
            listar(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage());
        }
    }

    private void excluir (ActionEvent e) {
        try {
            int id = Integer.parseInt(txtId.getText());
            controller.excluir(id);
            JOptionPane.showMessageDialog(this, "Turma excluída com sucesso!");
            listar(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
        }
    }

    private void buscar(ActionEvent e) {
        try {
            int id = Integer.parseInt(txtId.getText());
            Turma t = controller.buscarPorId(id);
            if (t != null) {
                txtInicio.setText(t.getDataInicio().toString());
                txtFim.setText(t.getDataFim().toString());
                txtLingua.setText(t.getLingua());
                txtNivel.setText(t.getNivel());
                txtPreco.setText(String.valueOf(t.getPreco()));
                JOptionPane.showMessageDialog(this, "Turma encontrada.");
            } else {
                JOptionPane.showMessageDialog(this, "Turma não encontrada.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar: " + ex.getMessage());
        }
    }

    private void listar(ActionEvent e) {
        try {
            List<Turma> turmas = controller.listar();
            StringBuilder sb = new StringBuilder();
            for (Turma t : turmas) {
                sb.append(t).append("\n");
            }
            txtLista.setText(sb.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}