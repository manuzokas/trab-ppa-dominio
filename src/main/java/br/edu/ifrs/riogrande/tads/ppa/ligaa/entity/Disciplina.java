package br.edu.ifrs.riogrande.tads.ppa.ligaa.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Disciplina {
    private Integer id;
    private String nome;
    private int cargaHoraria;
    private List<String> preRequisitos;
    private String ementa;
    @JsonProperty("aulasSemanais")
    private int aulasSemana;
    private LocalDateTime dataHoraCriacao;
    private LocalDateTime dataHoraAlteracao;
    private boolean desativado;

    public Disciplina() {
        this.dataHoraCriacao = LocalDateTime.now();
        this.desativado = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getAulasSemana() {
        return aulasSemana;
    }

    public void setAulasSemana(int aulasSemana) {
        this.aulasSemana = aulasSemana;
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

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public List<String> getPreRequisitos() {
        return preRequisitos;
    }

    public void setPreRequisitos(List<String> preRequisitos) {
        this.preRequisitos = preRequisitos;
    }
}
