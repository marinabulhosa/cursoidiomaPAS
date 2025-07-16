package model;

import java.time.LocalDate;
import java.time.LocalTime;

/*** Representa uma aula pertencente a uma turma.*/
public class Aula {
    private int id;
    private Turma turma;
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private Professor professor;

    public Aula(int id, Turma turma, LocalDate data, LocalTime horaInicio, LocalTime horaFim, Professor professor) {
        this.id = id;
        this.turma = turma;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.professor = professor;
    }

    public int getId() {
        return id;
    }

    public Turma getTurma() {
        return turma;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
        
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }
    
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Aula{" +
        "id=" + id +
        ", turmaId=" + (turma != null ? turma.getId() : "null") +
        ", data=" + data +
        ", horaInicio=" + horaInicio +
        ", horaFim=" + horaFim +
        ", professor=" + (professor != null ? professor.getMatricula() : "não definido") +
        '}';
    }
    
}