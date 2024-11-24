package com.faesa.projetointegrador2.dtos;

import com.faesa.projetointegrador2.models.Paciente;
import com.faesa.projetointegrador2.models.Psicologo;
import com.faesa.projetointegrador2.models.Usuario;

public class UsuarioDTO {
    private String nome;
    private String email;
    private String senha;
    private String tipoUsuario;
    private String telefone;

    // Campos específicos de Paciente
    private String historicoTestes;
    private Integer idade;

    // Campos específicos de Psicologo
    private String crp;
    private Long idEspecialidade;

    // Construtor padrão
    public UsuarioDTO() {
    }

    // Construtor que recebe um objeto Usuario
    public UsuarioDTO(Usuario usuario) {
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();

        if (usuario instanceof Paciente) {
            Paciente paciente = (Paciente) usuario;
            this.nome = paciente.getNome();
            this.historicoTestes = paciente.getHistoricoTestes();
            this.idade = paciente.getIdade();
            this.telefone = paciente.getTelefone();
        } else if (usuario instanceof Psicologo) {
            Psicologo psicologo = (Psicologo) usuario;
            this.nome = psicologo.getNome();
            this.telefone = psicologo.getTelefone();
            this.crp = psicologo.getCrp();

        }
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getCrp() {
        return crp;
    }

    public void setCrp(String crp) {
        this.crp = crp;
    }

    public Long getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Long idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }
}
