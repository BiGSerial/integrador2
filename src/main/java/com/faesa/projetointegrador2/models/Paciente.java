package com.faesa.projetointegrador2.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "pacientes")
public class Paciente extends Usuario {
    private String nome;
    private int idade;
    private String telefone;

    @Column(length = 5000)
    private String historicoTestes;

    @ManyToMany(mappedBy = "pacientes")
    private List<Agenda> agendas;

    public Paciente() {}

    public Paciente(String senha, String email, String nome, int idade, String telefone, String historicoTestes) {
        super(senha, email);
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
        this.historicoTestes = historicoTestes;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getHistoricoTestes() {
        return historicoTestes;
    }

    public void setHistoricoTestes(String historicoTestes) {
        this.historicoTestes = historicoTestes;
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
        System.out.println("Idade: " + idade);
        System.out.println("Telefone: " + telefone);
    }
}
