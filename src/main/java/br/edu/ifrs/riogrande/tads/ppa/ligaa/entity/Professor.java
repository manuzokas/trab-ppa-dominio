package br.edu.ifrs.riogrande.tads.ppa.ligaa.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Professor {

    private Integer id;
    private String siape;
    private String formacao;
    private LocalDateTime dataHoraAlteracao;
    private boolean desativado;

    public Professor() {
        this.desativado = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Professor))
            return false;
        Professor professor = (Professor) o;
        return Objects.equals(id, professor.id) &&
                Objects.equals(siape, professor.siape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, siape);
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", siape='" + siape + '\'' +
                ", formacao='" + formacao + '\'' +
                ", dataHoraAlteracao=" + dataHoraAlteracao +
                ", desativado=" + desativado +
                '}';
    }
}
