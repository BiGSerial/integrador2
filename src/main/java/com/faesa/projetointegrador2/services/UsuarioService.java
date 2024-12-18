package com.faesa.projetointegrador2.services;

import com.faesa.projetointegrador2.dtos.UsuarioDTO;
import com.faesa.projetointegrador2.models.Paciente;
import com.faesa.projetointegrador2.models.Psicologo;
import com.faesa.projetointegrador2.models.Usuario;
import com.faesa.projetointegrador2.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordService passwordService;

    public Usuario criarUsuario(UsuarioDTO usuarioDTO) {
        // Verifica se o e-mail já está registrado
        if (usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um usuário cadastrado com esse email.");
        }

        // Determina qual tipo de usuário criar com base no tipo fornecido no DTO
        Usuario usuario;
        if ("paciente".equalsIgnoreCase(usuarioDTO.getTipoUsuario())) {
            Paciente paciente = new Paciente();
            paciente.setNome(usuarioDTO.getNome());
            paciente.setTelefone(usuarioDTO.getTelefone());
            paciente.setHistoricoTestes(usuarioDTO.getHistoricoTestes());
            paciente.setIdade(usuarioDTO.getIdade());
            usuario = paciente;

        } else if ("psicologo".equalsIgnoreCase(usuarioDTO.getTipoUsuario())) {
            Psicologo psicologo = new Psicologo();
            psicologo.setNome(usuarioDTO.getNome());
            psicologo.setCrp(usuarioDTO.getCrp());
            psicologo.setTelefone(usuarioDTO.getTelefone());
            usuario = psicologo;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario Não Encontrado.");
        }

        // Configura os campos comuns
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(passwordService.encodePassword(usuarioDTO.getSenha()));

        // Salva no banco
        return usuarioRepository.save(usuario);
    }

    public boolean autenticarUsuario(String email, String senha) {
        var usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Usuario ou Senha inválido."));

        // Verifica a senha
        return passwordService.matchesPassword(senha, usuario.getSenha());
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o email: " + email));
    }

}
