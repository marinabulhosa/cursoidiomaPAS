package controller;

import dao.FuncionarioDAO;
import model.Funcionario;
import java.util.List;

/*** Controlador para gerenciamento de funcionários.*/
public class FuncionarioController {

    private FuncionarioDAO dao = new FuncionarioDAO();

    public void salvar(Funcionario f) throws Exception {
        dao.salvar(f);
    }

    public List<Funcionario> listar() throws Exception {
        return dao.listar();
    }

    public void atualizar(Funcionario f) throws Exception{
        dao.atualizar(f);
    }

    public void excluir(String nome) throws Exception{
        dao.excluir(nome);
    }

    public Funcionario buscarPorNome(String nome) throws Exception {
        return dao.buscarPorNome(nome);
    }
}