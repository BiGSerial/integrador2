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

import java.util.Map;


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
    public ResponseEntity<Map<String, String>> autenticar(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            boolean autenticado = usuarioService.autenticarUsuario(usuarioDTO.getEmail(), usuarioDTO.getSenha());

            if (autenticado) {
                String token = jwtUtil.gerarToken(usuarioDTO.getEmail());

                return ResponseEntity.ok().body(Map.of("token", token));

            } else {
                return ResponseEntity.status(401).body(Map.of("error", "Credenciais inválidas"));
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Erro ao autenticar: " + e.getMessage()));
        }
    }
}
