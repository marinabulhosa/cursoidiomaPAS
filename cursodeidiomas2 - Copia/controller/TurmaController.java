package controller;

import dao.TurmaDAO;
import model.Turma;
import java.util.List;

/*** Controlador para turmas.*/
public class TurmaController {

    private TurmaDAO dao = new TurmaDAO();

    public void salvar(Turma turma) throws Exception {
        dao.salvar(turma);
    }

    public void atualizar(Turma turma) throws Exception {
        dao.atualizar(turma);
    }

    public void excluir(int id) throws Exception {
        dao.excluir(id);
    }

    public Turma buscarPorId(int id) throws Exception {
        return dao.buscarPorId(id);
    }
    public List<Turma> listar() throws Exception {
        return dao.listar();
    }
}