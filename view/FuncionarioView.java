package view;

import controller.FuncionarioController;
import model.Funcionario;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.List;

/**

Interface gráfica para cadastro e listagem de funcionários.
*/
public class FuncionarioView extends JFrame {
    private JTextField txtNome, txtEndereco, txtTelefone, txtSalario;
    private JComboBox<String> cbCargo;
    private JTextArea txtLista;
    private FuncionarioController controller = new FuncionarioController();

    public FuncionarioView() {
        setTitle("Cadastro de Funcionário");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(6, 2));
        form.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        form.add(txtNome);

        form.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        form.add(txtEndereco);

        form.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        form.add(txtTelefone);

        form.add(new JLabel("Salário:"));
        txtSalario = new JTextField();
        form.add(txtSalario);

        form.add(new JLabel("Cargo:"));
        cbCargo = new JComboBox<>(new String[]{"Secretário", "Gerente"});
        form.add(cbCargo);

        JPanel botoes = new JPanel(new GridLayout(1, 5));
        JButton btnSalvar = new JButton("Salvar Funcionario");
        btnSalvar.addActionListener(this::salvar);
        botoes.add(btnSalvar);

        JButton btnAtualizar = new JButton("Atualizar Funcionario");
        btnAtualizar.addActionListener(this::atualizar);
        botoes.add(btnAtualizar);

        JButton btnExcluir = new JButton("Excluir Funcionario");
        btnExcluir.addActionListener(this::excluir);
        botoes.add(btnExcluir);

        JButton btnBuscar = new JButton("Buscar Funcionario");
        btnBuscar.addActionListener(this::buscar);
        botoes.add(btnBuscar);

        JButton btnListar = new JButton("Listar Todos os Funcionarios");
        btnListar.addActionListener(this::listar);
        botoes.add(btnListar);

        add(form, BorderLayout.NORTH);
        add(botoes, BorderLayout.CENTER);

        txtLista = new JTextArea();
        add(new JScrollPane(txtLista), BorderLayout.CENTER);

        setVisible(true);
    }

    private void salvar(ActionEvent e) {
        try {
        Funcionario f = new Funcionario(
            txtNome.getText(),
            txtEndereco.getText(),
            txtTelefone.getText(), 
            Double.parseDouble(txtSalario.getText()),
            cbCargo.getSelectedItem().toString()
            );
        controller.salvar(f);
        JOptionPane.showMessageDialog(this, "Funcionário salvo com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void atualizar(ActionEvent e) {
        try {
            Funcionario f = new Funcionario(
            txtNome.getText(),
            txtEndereco.getText(),
            txtTelefone.getText(),
            Double.parseDouble(txtSalario.getText()),
            cbCargo.getSelectedItem().toString()
            );
            controller.atualizar(f);
            JOptionPane.showMessageDialog(this, "Funcionário atualizado!");
            listar(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage());
        }
    }

    private void excluir(ActionEvent e) {
        try {
        controller.excluir(txtNome.getText());
        JOptionPane.showMessageDialog(this, "Funcionário excluído!");
        listar(null);
        } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
        }
    }

    private void buscar(ActionEvent e) {
        try {
            Funcionario f = controller.buscarPorNome(txtNome.getText());
            if (f != null) {
                cbCargo.setSelectedItem(f.getCargo());
                txtEndereco.setText(f.getEndereco());
                txtTelefone.setText(f.getTelefone());
                txtSalario.setText(String.valueOf(f.getSalario()));
                JOptionPane.showMessageDialog(this, "Funcionário encontrado!");
            } else {
                JOptionPane.showMessageDialog(this, "Funcionário não encontrado.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar: " + ex.getMessage());
        }
    }

    private void listar(ActionEvent e) {
        try {
            List<Funcionario> lista = controller.listar();
            StringBuilder sb = new StringBuilder();
            for (Funcionario f : lista) {
                sb.append(f).append("\n");
            }
            txtLista.setText(sb.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar: " + ex.getMessage());
        }
    }

}