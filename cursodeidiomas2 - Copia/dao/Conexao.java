package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*** Responsável por fornecer a conexão com o banco de dados SQLite.*/
public class Conexao {
    // Caminho do banco de dados SQLite. O arquivo "banco.db" será criado na raiz do projeto.
    private static final String URL = "jdbc:sqlite:banco.db";

    
    /**
     * Retorna uma conexão ativa com o banco de dados SQLite.
     * 
     * @return objeto Connection pronto para uso
     * @throws SQLException caso haja erro ao conectar
     */
    public static Connection getConnection() throws SQLException {
         return DriverManager.getConnection(URL);
    }
}