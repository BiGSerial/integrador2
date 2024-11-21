package com.faesa.projetointegrador2.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "psicologos")
public class Psicologo extends Usuario {
    private String nome, crp, telefone;

    @ManyToOne
    @JoinColumn(name = "idEspecialidade")
    private Especialidade especialidade;

    @OneToMany(mappedBy = "psicologo")
    private List<Agenda> agendas;

    public Psicologo() {}

    public Psicologo(String senha, String email, String nome, String crp, String telefone) {
        super(senha, email);
        this.nome = nome;
        this.crp = crp;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrp() {
        return crp;
    }

    public void setCrp(String crp) {
        this.crp = crp;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public List<Agenda> getAgendas() {
        return agendas;
    }

    public void setAgendas(List<Agenda> agendas) {
        this.agendas = agendas;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("CRP: " + crp);
        System.out.println("Telefone: " + telefone);
        System.out.println("Especialidades: ");
    }
}
