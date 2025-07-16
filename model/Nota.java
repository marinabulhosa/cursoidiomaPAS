package model;

/**
 * Representa a nota de um aluno em uma turma.
 */
public class Nota {
    private int turmaId;
    private String alunoMatricula;
    private String alunoNome; 
    private double valor;

    public Nota(int turmaId, String alunoMatricula, String alunoNome, double valor) {
        this.turmaId = turmaId;
        this.alunoMatricula = alunoMatricula;
        this.alunoNome = alunoNome;
        this.valor = valor;
    }

    public int getTurmaId() {
        return turmaId;
    }

    public String getAlunoMatricula() {
        return alunoMatricula;
    }

    public String getAlunoNome() { 
        return alunoNome; 
    }

    public double getValor() {
        return valor;
    }

    public void setTurmaId(int turmaId) { 
        this.turmaId = turmaId; 
    }

    public void setAlunoMatricula(String alunoMatricula) { 
        this.alunoMatricula = alunoMatricula;
    }
    
    public void setAlunoNome(String alunoNome) { 
        this.alunoNome = alunoNome; 
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "Turma=" + turmaId +
                ", Aluno='" + alunoMatricula + '\'' +
                ", Valor=" + valor +
                '}';
    }
}