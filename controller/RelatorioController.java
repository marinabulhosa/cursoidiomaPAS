package controller;

import service.RelatorioService;
import util.AlertUtil;

/*** Controlador para geração de relatórios financeiros.*/
public class RelatorioController {

    private final RelatorioService service = new RelatorioService();

    /*
      Gera o relatório financeiro contendo arrecadação, gastos realizados e gastos previstos.
      @return String com o conteúdo do relatório.
     */

    public String gerarRelatorio() {
        try {
            return service.gerarRelatorio(50.0); // valor fixo de custo por hora de aula
        } catch (Exception e) {
            AlertUtil.exibirErro("Erro ao gerar relatório: " + e.getMessage());
            return "Erro ao gerar relatório.";
        }    
    }
}