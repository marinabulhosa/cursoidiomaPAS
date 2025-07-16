package controller;

import dao.NotaDAO;
import model.Nota;
import java.util.List;

/*** Controlador das notas dos alunos.*/
public class NotaController {

    private NotaDAO dao = new NotaDAO();

    //Salva uma nova nota no banco de dados.
    public void salvar(Nota nota) throws Exception{
        dao.salvar(nota);
    }

    //Lista todas as notas existentes.
    public List<Nota> listar() throws Exception {
        return dao.listar();
    }

    // Atualiza uma nota existente no banco de dados.
     
    public void atualizar(Nota nota) throws Exception{
        dao.atualizar(nota);
    }

    //Exclui uma nota com base na turma e matrícula do aluno.
     
    public void excluir(int turmaId, String matricula) throws Exception{
        dao.excluir(turmaId, matricula);
    }

    //Busca todas as notas de uma turma específica.
     
    public List<Nota> buscarPorTurma(int turmaId) throws Exception{
        return dao.buscarPorTurma(turmaId);
    }

    // Busca todas as notas de um aluno pela matrícula.
    
    public List<Nota> buscarPorMatricula(String matricula) throws Exception{
        return dao.buscarPorMatricula(matricula);
    }
}