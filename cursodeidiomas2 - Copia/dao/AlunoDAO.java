package dao;

import model.Aluno;
import util.AlertUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



//Classe responsável por operações de persistência do Aluno.

public class AlunoDAO {

    public void salvar(Aluno a) throws Exception{
        String sql = "INSERT INTO alunos (matricula, nome, endereco, telefone, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, a.getMatricula());
            stmt.setString(2, a.getNome());
            stmt.setString(3, a.getEndereco());
            stmt.setString(4, a.getTelefone());
            stmt.setString(5, a.getEmail());

            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao salvar aluno: " + e.getMessage());
        }
    }

    public List<Aluno> listar() throws Exception{
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alunos";
        try (Connection conn = Conexao.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Aluno a = new Aluno(
                    rs.getString("matricula"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
                lista.add(a);
            }
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao listar alunos: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Aluno a) throws Exception{
        String sql = "UPDATE alunos SET nome = ?, endereco = ?, telefone = ?, email = ? WHERE matricula = ?";
        try (Connection conn = Conexao.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getEndereco());
            stmt.setString(3, a.getTelefone());
            stmt.setString(4, a.getEmail());
            stmt.setString(5, a.getMatricula());

            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    public void excluir(String matricula) throws Exception{
        String sql = "DELETE FROM alunos WHERE matricula = ?";
        try (Connection conn = Conexao.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula);
            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao excluir aluno: " + e.getMessage());
        }
    }

    public Aluno buscarPorMatricula(String matricula) throws Exception{
        String sql = "SELECT * FROM alunos WHERE matricula = ?";
        try (Connection conn = Conexao.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Aluno(
                    rs.getString("matricula"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao buscar aluno: " + e.getMessage());
        }
        return null;
    }
}