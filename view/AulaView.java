package view;

import controller.AulaController;
import dao.ProfessorDAO;
import dao.TurmaDAO;
import model.Aula;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

//Interface gráfica para cadastrar e listar aulas.

public class AulaView extends JFrame {
    private JTextField txtId, txtTurmaId, txtData, txtHoraInicio, txtHoraFim, txtProfessorMatricula;
    private JTextArea txtLista;
    private AulaController controller = new AulaController();

    public AulaView() {
        setTitle("Cadastro de Aulas");
        setSize(700, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(6,2));

        form.add(new JLabel("ID Aula:"));
        txtId = new JTextField();
        form.add(txtId);

        form.add(new JLabel("Id Turma:"));
        txtTurmaId = new JTextField();
        form.add(txtTurmaId);

        form.add(new JLabel("Data (dd/MM/yyyy):"));
        txtData = new JTextField();
        form.add(txtData);

        form.add(new JLabel("Hora Início (HH:mm):"));
        txtHoraInicio = new JTextField();
        form.add(txtHoraInicio);

        form.add(new JLabel("Hora Fim (HH:mm):"));
        txtHoraFim = new JTextField();
        form.add(txtHoraFim);

        form.add(new JLabel("Professor (opcional):"));
        txtProfessorMatricula = new JTextField();
        form.add(txtProfessorMatricula);


        JPanel botoes = new JPanel(new GridLayout(1, 7));

        JButton btnSalvar = new JButton("Salvar Aula");
        btnSalvar.addActionListener(this::salvar);
        botoes.add(btnSalvar);

        JButton btnAtualizar = new JButton("Atualizar Aula");
        btnAtualizar.addActionListener(this::atualizar);
        botoes.add(btnAtualizar);

        JButton btnExcluir = new JButton("Excluir Aula");
        btnExcluir.addActionListener(this::excluir);
        botoes.add(btnExcluir);

        JButton btnBuscar = new JButton("Buscar Aula");
        btnBuscar.addActionListener(this::buscar);
        botoes.add(btnBuscar);

        JButton btnListar = new JButton("Listar Todas as Aulas");
        btnListar.addActionListener(this::listar);
        botoes.add(btnListar);

        JButton btnReagendar = new JButton("Reagendar Aula");
        btnReagendar.addActionListener(this::reagendar);
        botoes.add(btnReagendar);

        JButton btnRegistrarProf = new JButton("Registrar Professor em Aula");
        btnRegistrarProf.addActionListener(this::registrarProfessor);
        botoes.add(btnRegistrarProf);

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
            Aula a = new Aula(
            Integer.parseInt(txtId.getText()),
            new TurmaDAO().buscarPorId(Integer.parseInt(txtTurmaId.getText())),
            LocalDate.parse(txtData.getText()),
            LocalTime.parse(txtHoraInicio.getText()),
            LocalTime.parse(txtHoraFim.getText()),
            txtProfessorMatricula.getText().isEmpty()
            ? null
            : new ProfessorDAO().buscarPorMatricula(txtProfessorMatricula.getText())
            );
            controller.salvar(a);
            JOptionPane.showMessageDialog(this, "Aula salva com sucesso!");
            listar(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
        }
    }


    private void atualizar(ActionEvent e) {
        try {
            Aula a = new Aula(
            Integer.parseInt(txtId.getText()),
            new TurmaDAO().buscarPorId(Integer.parseInt(txtTurmaId.getText())),
            LocalDate.parse(txtData.getText()),
            LocalTime.parse(txtHoraInicio.getText()),
            LocalTime.parse(txtHoraFim.getText()),
            txtProfessorMatricula.getText().isEmpty()
            ? null
            : new ProfessorDAO().buscarPorMatricula(txtProfessorMatricula.getText())
            );
            controller.atualizar(a);
            JOptionPane.showMessageDialog(this, "Aula atualizada com sucesso!");
            listar(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage());
        }
    }

    private void excluir(ActionEvent e) {
        try {
            int id = Integer.parseInt(txtId.getText());
            controller.excluir(id);
            JOptionPane.showMessageDialog(this, "Aula excluída com sucesso!");
            listar(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir aula: " + ex.getMessage());
        }
    }

    private void buscar(ActionEvent e) {
        try {
            int id = Integer.parseInt(txtId.getText());
            Aula aula = controller.buscarPorId(id);
            if (aula != null) {
                txtTurmaId.setText(String.valueOf(aula.getTurma().getId()));
                txtData.setText(aula.getData().toString());
                txtHoraInicio.setText(aula.getHoraInicio().toString());
                txtHoraFim.setText(aula.getHoraFim().toString());
                txtProfessorMatricula.setText(aula.getProfessor() != null ? aula.getProfessor().getMatricula() : "");
                JOptionPane.showMessageDialog(this, "Aula encontrada.");
            } else {
                JOptionPane.showMessageDialog(this, "Aula não encontrada.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar aula: " + ex.getMessage());
        }
    }

    private void listar(ActionEvent e) {
        try {
            List<Aula> aulas = controller.listar();
            StringBuilder sb = new StringBuilder();
            for (Aula a : aulas) {
                sb.append(a).append("\n");
            }
            txtLista.setText(sb.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar: " + ex.getMessage());
        }
    }

    private void reagendar(ActionEvent e) {
        try {
            int id = Integer.parseInt(txtId.getText());
            LocalDate novaData = LocalDate.parse(txtData.getText());
            controller.reagendar(id, novaData);
            JOptionPane.showMessageDialog(this, "Aula reagendada!");
            listar(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao reagendar aula: " + ex.getMessage());
        }
    }

    private void registrarProfessor(ActionEvent e) {
        try {
            int id = Integer.parseInt(txtId.getText());
            String matricula = txtProfessorMatricula.getText();
            controller.registrarProfessor(id, matricula);
            JOptionPane.showMessageDialog(this, "Professor registrado com sucesso!");
            listar(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar professor: " + ex.getMessage());
        }
    }
}