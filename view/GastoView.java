package view;

import controller.GastoController;
import model.Gasto;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.awt.*;
import java.util.List;

/**Interface gráfica para cadastrar e listar gastos*/
public class GastoView extends JFrame {
    private JTextField txtId, txtDescricao, txtValor, txtData;
    private JTextArea txtLista;
    private GastoController controller = new GastoController();

    public GastoView() {
        setTitle("Cadastro de Gasto");
        setSize(500, 350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(4, 2));

        form.add(new JLabel("Id gasto:"));
        txtId = new JTextField();
        form.add(txtId);

        form.add(new JLabel("Descrição:"));
        txtDescricao = new JTextField();
        form.add(txtDescricao);

        form.add(new JLabel("Valor:"));
        txtValor = new JTextField();
        form.add(txtValor);

        form.add(new JLabel("Data (dd/MM/yyyy):"));
        txtData = new JTextField();
        form.add(txtData);

        JPanel botoes = new JPanel(new GridLayout(1, 5));

        JButton btnSalvar = new JButton("Salvar Gasto");
        btnSalvar.addActionListener(this::salvar);
        botoes.add(btnSalvar);

        JButton btnAtualizar = new JButton("Atualizar Gasto"); 
        btnAtualizar.addActionListener(this::atualizar);
        botoes.add(btnAtualizar);

        JButton btnExcluir = new JButton("Excluir Gasto"); 
        btnExcluir.addActionListener(this::excluir);
        botoes.add(btnExcluir);

        JButton btnBuscar = new JButton("Buscar Gasto"); 
        btnBuscar.addActionListener(this::buscar);
        botoes.add(btnBuscar);

        JButton btnListar = new JButton("Listar Gasto");
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
            Gasto g = new Gasto(
                Integer.parseInt(txtId.getText()),
                txtDescricao.getText(),
                Double.parseDouble(txtValor.getText()),
                LocalDate.parse(txtData.getText())
                );
            controller.salvar(g);
            JOptionPane.showMessageDialog(this, "Gasto salvo com sucesso!");
        } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
        }
    }

    private void atualizar (ActionEvent e) {
        try {
            Gasto g = new Gasto(
                Integer.parseInt(txtId.getText()),
                txtDescricao.getText(),
                Double.parseDouble(txtValor.getText()),
                LocalDate.parse(txtData.getText())
                );
            controller.atualizar(g);
            JOptionPane.showMessageDialog(this, "Gasto atualizado com sucesso!");
            listar(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage());
        }
    }

    private void excluir (ActionEvent e) {
        try {
            int id = Integer.parseInt(txtId.getText());
            controller.excluir(id);
            JOptionPane.showMessageDialog(this, "Gasto excluído com sucesso!");
            listar(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
        }
    }

    private void buscar(ActionEvent e) {
        try {
            int id = Integer.parseInt(txtId.getText());
            Gasto g = controller.buscarPorId(id);
            if (g != null) {
                txtDescricao.setText(g.getDescricao());
                txtData.setText(g.getData().toString());
                txtValor.setText(String.valueOf(g.getValor()));
                JOptionPane.showMessageDialog(this, "Gasto encontrado.");
            } else {
                JOptionPane.showMessageDialog(this, "Gasto não encontrado.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar: " + ex.getMessage());
        }
    }

    private void listar(ActionEvent e) {
        try {
            List<Gasto> gastos = controller.listar();
            txtLista.setText("");
            for (Gasto g : gastos) {
                txtLista.append(g.toString() + "\n");
        }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar: " + ex.getMessage());
        }
    }
}