package com.faesa.projetointegrador2.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "especialidade")
public class Especialidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String descricao;

    @OneToMany(mappedBy = "especialidade")
    private List<Psicologo> psicologos;

    public Especialidade() {
    }

    public Especialidade(String descricao) {
        this.descricao = descricao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Psicologo> getPsicologos() {
        return psicologos;
    }

    public void setPsicologos(List<Psicologo> psicologos) {
        this.psicologos = psicologos;
    }
}
