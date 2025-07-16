package dao;

import model.Turma;
import util.AlertUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Classe DAO para manipulação das turmas no banco de dados.
//Fornece métodos para salvar, listar, atualizar, excluir e buscar turmas.

  public class TurmaDAO {

    //Insere uma nova turma no banco de dados.
  
    public void salvar(Turma t) throws Exception{
    	String sql = "INSERT INTO turmas (id, dataInicio, dataFim, lingua, nivel, preco) VALUES (?, ?, ?, ?, ?, ?)";

    	try (Connection conn = Conexao.getConnection();
    	PreparedStatement stmt = conn.prepareStatement(sql)) {

     		stmt.setInt(1, t.getId());
     		stmt.setString(2, t.getDataInicio().toString());
     		stmt.setString(3, t.getDataFim().toString());
     		stmt.setString(4, t.getLingua());
     		stmt.setString(5, t.getNivel());
     		stmt.setDouble(6, t.getPreco());

     		stmt.executeUpdate();

    	} catch (SQLException e) {
    		AlertUtil.exibirErro("Erro ao salvar turma: " + e.getMessage());
    	}
    }

    //Lista todas as turmas do banco de dados.
    //Neste ponto não carrega os alunos associados à turma.
   
    public List<Turma> listar() throws Exception{
        List<Turma> lista = new ArrayList<>();
        String sql = "SELECT * FROM turmas";

        try (Connection conn = Conexao.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Turma t = new Turma(
                    rs.getInt("id"),
                    LocalDate.parse(rs.getString("dataInicio")),
                    LocalDate.parse(rs.getString("dataFim")),
                    rs.getString("lingua"),
                    rs.getString("nivel"),
                    rs.getDouble("preco"),
                    new ArrayList<>() // Lista de alunos pode ser carregada em outro método
                );
                lista.add(t);
            }
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao listar turmas: " + e.getMessage());
        }

        return lista;
    }


    //Atualiza os dados de uma turma existente no banco.
  
    public void atualizar(Turma t) throws Exception{
        String sql = "UPDATE turmas SET dataInicio = ?, dataFim = ?, lingua = ?, nivel = ?, preco = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getDataInicio().toString());
            stmt.setString(2, t.getDataFim().toString());
            stmt.setString(3, t.getLingua());
            stmt.setString(4, t.getNivel());
            stmt.setDouble(5, t.getPreco());
            stmt.setInt(6, t.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao atualizar turma: " + e.getMessage());
        }
    }


   //Exclui uma turma do banco pelo ID.
   
    public void excluir(int id) throws Exception{
        String sql = "DELETE FROM turmas WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao excluir turma: " + e.getMessage());
        }
    }


    //Busca uma turma pelo seu ID.

    public Turma buscarPorId(int id) throws Exception{
        String sql = "SELECT * FROM turmas WHERE id = ?";
        Turma turma = null;

        try (Connection conn = Conexao.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                turma = new Turma(
                    rs.getInt("id"),
                    LocalDate.parse(rs.getString("dataInicio")),
                    LocalDate.parse(rs.getString("dataFim")),
                    rs.getString("lingua"),
                    rs.getString("nivel"),
                    rs.getDouble("preco"),
                    new ArrayList<>()
                );
            }
            rs.close();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao buscar turma por ID: " + e.getMessage());
        }
        return turma;
    }

}
