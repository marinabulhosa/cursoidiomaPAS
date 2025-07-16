package view;

import controller.RelatorioController;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**Interface gráfica para exibição dos relatórios financeiros.*/
public class RelatorioView extends JFrame {

    private JTextArea txtRelatorio;
    private RelatorioController controller = new RelatorioController();

    public RelatorioView() {
        setTitle("Relatórios Financeiros");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Botão para gerar relatório
        JButton btnGerar = new JButton("Gerar Relatório");
        btnGerar.addActionListener(this::gerarRelatorio);
        add(btnGerar, BorderLayout.SOUTH);

        // Área de texto para exibir resultado
        txtRelatorio = new JTextArea();
        txtRelatorio.setEditable(false);
        add(new JScrollPane(txtRelatorio), BorderLayout.CENTER);

        setVisible(true);
    }

    private void gerarRelatorio (ActionEvent e) {
        try {
            String relatorio = controller.gerarRelatorio();
            txtRelatorio.setText(relatorio);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao gerar relatório: " + ex.getMessage());
        }
    }
}