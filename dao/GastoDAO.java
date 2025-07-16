package dao;

import model.Gasto;
import util.AlertUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


// Classe responsável pelo acesso aos dados de Gasto no banco.*/
public class GastoDAO {

    public void salvar(Gasto g) throws Exception{
        String sql = "INSERT INTO gastos (id, descricao, valor, data) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, g.getId());
            stmt.setString(2, g.getDescricao());
            stmt.setDouble(3, g.getValor());
            stmt.setString(4, g.getData().toString());

            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao salvar gasto: " + e.getMessage());
        }
    }

    
    public List<Gasto> listar()throws Exception {
        List<Gasto> lista = new ArrayList<>();
        String sql = "SELECT * FROM gastos";
        try (Connection conn = Conexao.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Gasto g = new Gasto(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        LocalDate.parse(rs.getString("data"))
                );
                lista.add(g);
            }
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao listar gastos: " + e.getMessage());
        }
        return lista;
    }


    public void atualizar(Gasto g)throws Exception {
        String sql = "UPDATE gastos SET descricao = ?, valor = ?, data = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, g.getDescricao());
                stmt.setDouble(2, g.getValor());
                stmt.setString(3, g.getData().toString());
                stmt.setInt(4, g.getId());

                stmt.executeUpdate();
        } catch (SQLException e) {
                AlertUtil.exibirErro("Erro ao atualizar gasto: " + e.getMessage());
        }   
    }


    public void excluir(int id) throws Exception{
        String sql = "DELETE FROM gastos WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao excluir gasto: " + e.getMessage());
        }
    }


    public Gasto buscarPorId(int id) throws Exception{
        String sql = "SELECT * FROM gastos WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Gasto(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        LocalDate.parse(rs.getString("data"))
                );
            }
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao buscar gasto: " + e.getMessage());
        }
        return null;
    }


}