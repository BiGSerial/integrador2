package com.faesa.projetointegrador2.controllers;


import com.faesa.projetointegrador2.models.Psicologo;
import com.faesa.projetointegrador2.repositories.PsicologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/psicologos")
public class PsicologoController {

    @Autowired
    private PsicologoRepository psicologoRepository;

    @PostMapping
    public Psicologo criarPsicologo(@RequestBody Psicologo psicologo) {
        return psicologoRepository.save(psicologo);
    }

    @GetMapping
    public List<Psicologo> listarPsicologos() {
        return psicologoRepository.findAll();
    }
}
