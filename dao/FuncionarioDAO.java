package dao;

import model.Funcionario;
import util.AlertUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*** DAO para manipulação da tabela de funcionários.*/
public class FuncionarioDAO {

    public void salvar(Funcionario f) throws Exception{
        String sql = "INSERT INTO funcionarios (nome, endereco, telefone, salario, cargo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getEndereco());
            stmt.setString(3, f.getTelefone());
            stmt.setDouble(4, f.getSalario());
            stmt.setString(5, f.getCargo());

            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao salvar funcionário: " + e.getMessage());
        }
    }


    public List<Funcionario> listar() throws Exception{
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";
        try (Connection conn = Conexao.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Funcionario f = new Funcionario(
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getDouble("salario"),
                    rs.getString("cargo")
                );
                lista.add(f);
            }
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao listar funcionários: " + e.getMessage());
        }
        return lista;
    }

     // Atualiza os dados de um funcionário existente, identificado pelo nome.
    
    public void atualizar(Funcionario f) throws Exception{
        String sql = "UPDATE funcionarios SET endereco = ?, telefone = ?, salario = ?, cargo = ? WHERE nome = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getEndereco());
            stmt.setString(3, f.getTelefone());
            stmt.setDouble(4, f.getSalario());
            stmt.setString(5, f.getCargo());
            

            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    // Exclui um funcionário com base no nome.
     
    public void excluir(String nome) throws Exception{
        String sql = "DELETE FROM funcionarios WHERE nome = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao excluir funcionário: " + e.getMessage());
        }
    }

    // Busca um funcionário pelo nome.
     
    public Funcionario buscarPorNome(String nome)throws Exception {
        String sql = "SELECT * FROM funcionarios WHERE nome = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Funcionario(
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getDouble("salario"),
                    rs.getString("cargo")
                );
            }
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao buscar funcionário: " + e.getMessage());
        }
        return null;
    }
}

