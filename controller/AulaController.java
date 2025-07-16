package controller;

import dao.AulaDAO;
import model.Aula;
import java.time.LocalDate;
import java.util.List;

/*** Controlador para gerenciamento de aulas.*/
public class AulaController {

    private AulaDAO dao = new AulaDAO();

    public void salvar(Aula aula) throws Exception {
        dao.salvar(aula);
    }

    /*** Permite reagendar uma aula.*/
    public void reagendar(int id, LocalDate novaData) throws Exception {
        dao.reagendar(id, novaData);
    }

    /*** Registra o professor no momento do início da aula.*/
    public void registrarProfessor(int idAula, String matriculaProfessor) throws Exception {
        dao.registrarProfessor(idAula, matriculaProfessor);
    }

    public List<Aula> listar() throws Exception {
        return dao.listar();
    }

    public void atualizar(Aula aula) throws Exception{
        dao.atualizar(aula);
    }

    public void excluir(int idAula) throws Exception{
        dao.excluir(idAula);
    }

    public Aula buscarPorId(int id) throws Exception{
        return dao.buscarPorId(id);
    }

}