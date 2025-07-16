package dao;

import model.Nota;
import util.AlertUtil;

import java.sql.*;
import java.util.*;

/**
 * DAO responsável por operações CRUD da entidade Nota.
 */
public class NotaDAO {

    /**
     * Salva uma nova nota no banco de dados.
     */
    public void salvar(Nota nota) throws Exception{
        String sql = "INSERT INTO notas (turmaId, alunoMatricula, alunoNome, valor) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, nota.getTurmaId());
            stmt.setString(2, nota.getAlunoMatricula());
            stmt.setString(3, nota.getAlunoNome());
            stmt.setDouble(4, nota.getValor());

            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao salvar nota: " + e.getMessage());
        }
    }

    
    /**
     * Lista todas as notas registradas no sistema.
     */
    public List<Nota> listar() throws Exception{
        List<Nota> lista = new ArrayList<>();
        String sql = "SELECT * FROM notas";

        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Nota nota = new Nota(
                    rs.getInt("turmaId"),
                    rs.getString("alunoMatricula"),
                    rs.getString("alunoNome"),
                    rs.getDouble("valor")
                );
                lista.add(nota);
            }
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao listar notas: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Atualiza o valor de uma nota com base na turma e matrícula.
     */
    public void atualizar(Nota nota) throws Exception{
        String sql = "UPDATE notas SET alunoNome = ?, valor = ? WHERE turmaId = ? AND alunoMatricula = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nota.getAlunoNome());
            stmt.setDouble(2, nota.getValor());
            stmt.setInt(3, nota.getTurmaId());
            stmt.setString(4, nota.getAlunoMatricula());

            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao atualizar nota: " + e.getMessage());
        }
    }

    /**
     * Exclui uma nota com base na turma e matrícula do aluno.
     */
    public void excluir(int turmaId, String alunoMatricula) throws Exception{
        String sql = "DELETE FROM notas WHERE turmaId = ? AND alunoMatricula = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, turmaId);
            stmt.setString(2, alunoMatricula);

            stmt.executeUpdate();
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao excluir nota: " + e.getMessage());
        }
    }

    /**
     * Busca todas as notas de uma determinada turma.
     */
    public List<Nota> buscarPorTurma(int turmaId)throws Exception {
        List<Nota> lista = new ArrayList<>();
        String sql = "SELECT * FROM notas WHERE turmaId = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, turmaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Nota nota = new Nota(
                    rs.getInt("turmaId"),
                    rs.getString("alunoMatricula"),
                    rs.getString("alunoNome"),
                    rs.getDouble("valor")
                );
                lista.add(nota);
            }
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao buscar notas por turma: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Busca todas as notas de um determinado aluno pela matrícula.
     */
    public List<Nota> buscarPorMatricula(String matricula) throws Exception{
        List<Nota> lista = new ArrayList<>();
        String sql = "SELECT * FROM notas WHERE alunoMatricula = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Nota nota = new Nota(
                    rs.getInt("turmaId"),
                    rs.getString("alunoMatricula"),
                    rs.getString("alunoNome"),
                    rs.getDouble("valor")
                );
                lista.add(nota);
            }
        } catch (SQLException e) {
            AlertUtil.exibirErro("Erro ao buscar notas por matrícula: " + e.getMessage());
        }

        return lista;
    }
}
