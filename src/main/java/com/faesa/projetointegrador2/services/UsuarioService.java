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
        try {
            if (usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um usuário cadastrado com esse email.");
            }

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
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de usuário inválido.");
            }

            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setSenha(passwordService.encodePassword(usuarioDTO.getSenha()));

            return usuarioRepository.save(usuario);

        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar o usuário: " + e.getMessage(), e);
        }
    }

    public boolean autenticarUsuario(String email, String senha) {
        try {
            var usuario = usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Usuário ou Senha inválido."));

            return passwordService.matchesPassword(senha, usuario.getSenha());
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao autenticar o usuário: " + e.getMessage(), e);
        }
    }

    public Usuario findByEmail(String email) {
        try {
            return usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o email: " + email));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar o usuário: " + e.getMessage(), e);
        }
    }
}
