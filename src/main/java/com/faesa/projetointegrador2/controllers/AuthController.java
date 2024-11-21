package com.faesa.projetointegrador2.controllers;

import com.faesa.projetointegrador2.dtos.TokenDTO;
import com.faesa.projetointegrador2.dtos.UsuarioDTO;
import com.faesa.projetointegrador2.security.JwtUtil;
import com.faesa.projetointegrador2.services.PasswordService;
import com.faesa.projetointegrador2.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> autenticar(@RequestBody UsuarioDTO usuarioDTO) {
        boolean autenticado = usuarioService.autenticarUsuario(usuarioDTO.getEmail(), usuarioDTO.getSenha());

        if (autenticado) {
            String token = jwtUtil.gerarToken(usuarioDTO.getEmail());

            return ResponseEntity.ok(new TokenDTO(token).getToken());

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas!");
        }
    }
}
