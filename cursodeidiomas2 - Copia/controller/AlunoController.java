package controller;

import dao.AlunoDAO;
import model.Aluno;
import java.util.List;

/*** Controlador responsável pelas operações relacionadas ao Aluno.*/
public class AlunoController {

    private AlunoDAO dao = new AlunoDAO();

    /*** Salva um novo aluno.*/
    public void salvar(Aluno aluno) throws Exception {
        dao.salvar(aluno);
    }

    /*** Lista todos os alunos cadastrados.*/
    public List<Aluno> listar() throws Exception {
        return dao.listar();
    }

    public void atualizar(Aluno a) throws Exception{
        dao.atualizar(a);
    }

    /*** Remove um aluno com base na matrícula.*/
    public void excluir(String matricula) throws Exception {
        dao.excluir(matricula);
    }

    public Aluno buscarPorMatricula(String matricula) throws Exception{
        return dao.buscarPorMatricula(matricula);
    }
}

