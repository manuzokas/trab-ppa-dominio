package br.edu.ifrs.riogrande.tads.ppa.ligaa.entity;

import java.time.LocalDateTime;

public class Matricula {
    private Integer id;
    private Aluno aluno;
    private Turma turma;
    private LocalDateTime dataMatricula;
    private LocalDateTime dataHoraCriacao;
    private LocalDateTime dataHoraAlteracao;
    private boolean desativado;

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public LocalDateTime getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDateTime dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public LocalDateTime getDataHoraAlteracao() {
        return dataHoraAlteracao;
    }

    public void setDataHoraAlteracao(LocalDateTime dataHoraAlteracao) {
        this.dataHoraAlteracao = dataHoraAlteracao;
    }

    public boolean isDesativado() {
        return desativado;
    }

    public void setDesativado(boolean desativado) {
        this.desativado = desativado;
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "id=" + id +
                ", aluno=" + aluno +
                ", turma=" + turma +
                ", dataMatricula=" + dataMatricula +
                ", dataHoraCriacao=" + dataHoraCriacao +
                ", dataHoraAlteracao=" + dataHoraAlteracao +
                ", desativado=" + desativado +
                '}';
    }
}
