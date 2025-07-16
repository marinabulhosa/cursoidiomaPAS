package controller;

import dao.GastoDAO;
import model.Gasto;
import java.util.List;

/*** Controlador para os gastos do sistema.*/
public class GastoController {
    private GastoDAO dao = new GastoDAO();

    public void salvar(Gasto gasto) throws Exception {
        dao.salvar(gasto);
    }

    public List<Gasto> listar() throws Exception {
        return dao.listar();
    }

     public void atualizar(Gasto g)throws Exception {
        dao.atualizar(g);
    }

    public void excluir(int id) throws Exception{
        dao.excluir(id);
    }

    public Gasto buscarPorId(int id) throws Exception{
        return dao.buscarPorId(id);
    }
}