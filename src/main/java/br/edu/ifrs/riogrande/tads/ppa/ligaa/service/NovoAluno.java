package br.edu.ifrs.riogrande.tads.ppa.ligaa.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NovoAluno {

    private String nome;

    @JsonProperty("email")
    private String enderecoEletronico;
    
    private String cpf;

    private String login;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEnderecoEletronico() {
        return enderecoEletronico;
    }

    public void setEnderecoEletronico(String enderecoEletronico) {
        this.enderecoEletronico = enderecoEletronico;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "NovoAluno [nome=" + nome + ", enderecoEletronico=" + enderecoEletronico + ", cpf=" + cpf + "]";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
}
