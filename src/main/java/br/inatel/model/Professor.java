package br.inatel.model;

import java.time.LocalTime;

public class Professor {
    private String nome;
    private LocalTime horario;
    private String periodo;

    public Professor(String nome, LocalTime horario, String periodo) {
        this.nome = nome;
        this.horario = horario;
        this.periodo = periodo;
    }

    public String getNome() {
        return nome;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public String getPeriodo() {
        return periodo;
    }
}
