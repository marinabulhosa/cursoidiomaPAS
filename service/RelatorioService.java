package service;

import dao.AulaDAO;
import dao.GastoDAO;
import dao.TurmaDAO;
import model.Aula;
import model.Gasto;
import model.Turma;
import util.AlertUtil;

import java.time.LocalDate;
import java.util.List;

public class RelatorioService {

    private final TurmaDAO turmaDAO = new TurmaDAO();
    private final AulaDAO aulaDAO = new AulaDAO();
    private final GastoDAO gastoDAO = new GastoDAO();

    public String gerarRelatorio(double valorHoraAula) throws Exception{

        try {
            StringBuilder sb = new StringBuilder();
            sb.append("=== Relatório Financeiro ===\n");

            double arrecadado = calcularArrecadado();
            double gastosRealizados = calcularGastoAulasRealizadas(valorHoraAula) + calcularGastosGerais();
            double gastosPendentes = calcularGastoAulasPendentes(valorHoraAula);

            sb.append("Valor arrecadado: R$ ").append(String.format("%.2f", arrecadado)).append("\n");
            sb.append("Gastos realizados: R$ ").append(String.format("%.2f", gastosRealizados)).append("\n");
            sb.append("Gastos pendentes: R$ ").append(String.format("%.2f", gastosPendentes)).append("\n");

            return sb.toString();
            
        } catch (Exception e) {
            AlertUtil.exibirErro("Erro ao processar relatório: " + e.getMessage());
            return "Erro ao gerar relatório.";
        }    
    }

    private double calcularArrecadado() throws Exception{
        double total = 0.0;
        List<Turma> turmas = turmaDAO.listar();
        LocalDate hoje = LocalDate.now();

        for (Turma t : turmas) {
            if (t.getDataInicio().isBefore(hoje)) {
                total += t.getPreco();
            }
        }
        return total;
    }

    private double calcularGastoAulasRealizadas(double valorHoraAula) throws Exception{
        double total = 0.0;
        List<Aula> aulas = aulaDAO.listar();
        LocalDate hoje = LocalDate.now();

        for (Aula a : aulas) {
            if (a.getData().isBefore(hoje)) {
                total += valorHoraAula;
                if (a.getProfessor() != null) {
                    total += a.getProfessor().getValorHora();
                }
            }
        }
        return total;
    }

    private double calcularGastoAulasPendentes(double valorHoraAula) throws Exception{
        double total = 0.0;
        List<Aula> aulas = aulaDAO.listar();
        LocalDate hoje = LocalDate.now();

        for (Aula a : aulas) {
            if (!a.getData().isBefore(hoje)) {
                total += valorHoraAula;
            }
        }
        return total;
    }

    private double calcularGastosGerais() throws Exception {
        double total = 0.0;
        List<Gasto> gastos = gastoDAO.listar();
        for (Gasto g : gastos) {
            total += g.getValor();
        }
        return total;
    }
}
