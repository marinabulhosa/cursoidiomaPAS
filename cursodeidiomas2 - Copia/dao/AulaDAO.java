package dao;

import model.Aula;
import model.Professor;
import model.Turma;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import util.AlertUtil;

public class AulaDAO {

    public void salvar(Aula a) throws Exception{
        String sql = "INSERT INTO aulas (id, turmaId, data, horaInicio, horaFim, professorMatricula) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, a.getId());
            stmt.setInt(2, a.getTurma().getId());
            stmt.setString(3, a.getData().toString());
            stmt.setString(4, a.getHoraInicio().toString());
            stmt.setString(5, a.getHoraFim().toString());
            stmt.setString(6, a.getProfessor() != null ? a.getProfessor().getMatricula() : null);

            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao salvar aula: " + e.getMessage());
        }
    }

    public void reagendar(int id, LocalDate novaData) throws Exception{
        String sql = "UPDATE aulas SET data = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novaData.toString());
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao reagendar aula: " + e.getMessage());
        }
    }

    public void registrarProfessor(int idAula, String matriculaProfessor) throws Exception{
        String sql = "UPDATE aulas SET professorMatricula = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matriculaProfessor);
            stmt.setInt(2, idAula);
            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao registrar professor: " + e.getMessage());
        }
    }
    
    public List<Aula> listar() throws Exception{
        List<Aula> lista = new ArrayList<>();
        String sql = "SELECT * FROM aulas";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Turma turma = new TurmaDAO().buscarPorId(rs.getInt("turmaId"));
                Professor professor = rs.getString("professorMatricula") != null ? new ProfessorDAO().buscarPorMatricula(rs.getString("professorMatricula")) : null;
                Aula a = new Aula(
                    rs.getInt("id"),
                    turma,
                    LocalDate.parse(rs.getString("data")),
                    LocalTime.parse(rs.getString("horaInicio")),
                    LocalTime.parse(rs.getString("horaFim")),
                    professor
                );
                lista.add(a);
            }
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao listar aulas: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Aula a) throws Exception{
        String sql = "UPDATE aulas SET turmaId=?, data=?, horaInicio=?, horaFim=?, professorMatricula=? WHERE id=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, a.getTurma().getId());
            stmt.setString(2, a.getData().toString());
            stmt.setString(3, a.getHoraInicio().toString());
            stmt.setString(4, a.getHoraFim().toString());
            stmt.setString(5, a.getProfessor() != null ? a.getProfessor().getMatricula() : null);
            stmt.setInt(6, a.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao atualizar aula: " + e.getMessage());
        }
    }

    public void excluir(int id) throws Exception{
        String sql = "DELETE FROM aulas WHERE id=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao excluir aula: " + e.getMessage());
        }
    }

    public Aula buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM aulas WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Turma turma = new TurmaDAO().buscarPorId(rs.getInt("turmaId"));
                Professor professor = rs.getString("professorMatricula") != null ? new ProfessorDAO().buscarPorMatricula(rs.getString("professorMatricula")) : null;
                return new Aula(
                    rs.getInt("id"),
                    turma,
                    LocalDate.parse(rs.getString("data")),
                    LocalTime.parse(rs.getString("horaInicio")),
                    LocalTime.parse(rs.getString("horaFim")),
                    professor
                );
            }
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao buscar aula: " + e.getMessage());
        }
        return null;
    }
}
