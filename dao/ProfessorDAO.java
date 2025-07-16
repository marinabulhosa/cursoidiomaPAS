package dao;

import model.Professor;
import util.AlertUtil;

import java.sql.*;
import java.util.*;

/*** Classe de acesso ao banco para a entidade Professor.*/
public class ProfessorDAO {

    public void salvar(Professor p) throws Exception {
        String sql = "INSERT INTO professores (matricula, nome, endereco, telefone, valorHora, linguas) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getMatricula());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getEndereco());
            stmt.setString(4, p.getTelefone());
            stmt.setDouble(5, p.getValorHora());
            stmt.setString(6, String.join(",", p.getLinguas()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao salvar professor: " + e.getMessage());
        }
    }

    public List<Professor> listar() throws Exception {
        List<Professor> lista = new ArrayList<>();
        String sql = "SELECT * FROM professores";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                List<String> linguas = Arrays.asList(rs.getString("linguas").split(","));
                Professor p = new Professor(
                    rs.getString("matricula"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getDouble("valorHora"),
                    linguas
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao listar professores: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Professor p) throws Exception{
        String sql = "UPDATE professores SET nome = ?, endereco = ?, telefone = ?, valorHora = ?, linguas = ? WHERE matricula = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEndereco());
            stmt.setString(3, p.getTelefone());
            stmt.setDouble(4, p.getValorHora());
            stmt.setString(5, String.join(",", p.getLinguas()));
            stmt.setString(6, p.getMatricula());

            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao atualizar professor: " + e.getMessage());
        }
    }

    public void excluir(String matricula) throws Exception{
        String sql = "DELETE FROM professores WHERE matricula = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula);
            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao excluir professor: " + e.getMessage());
        }
    }

    public Professor buscarPorMatricula(String matricula) throws Exception{
        String sql = "SELECT * FROM professores WHERE matricula = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                List<String> linguas = Arrays.asList(rs.getString("linguas").split(","));
                return new Professor(
                    rs.getString("matricula"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getDouble("valorHora"),
                    linguas
                );
            }
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao buscar professor: " + e.getMessage());
        }
        return null;
    }
}