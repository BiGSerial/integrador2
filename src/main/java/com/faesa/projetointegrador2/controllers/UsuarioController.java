package com.faesa.projetointegrador2.controllers;


import com.faesa.projetointegrador2.dtos.UsuarioDTO;
import com.faesa.projetointegrador2.models.Usuario;
import com.faesa.projetointegrador2.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuarioCriado = usuarioService.criarUsuario(usuarioDTO);
        return ResponseEntity.ok(usuarioCriado); // Retorna o usuário criado com status 200
    }

}
