package com.faesa.projetointegrador2.controllers;

import com.faesa.projetointegrador2.models.Especialidade;
import com.faesa.projetointegrador2.repositories.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @GetMapping
    public List<Especialidade> getAllEspecialidades() {
        return especialidadeRepository.findAll();
    }

    @PostMapping
    public Especialidade createEspecialidade(@RequestBody Especialidade especialidade) {
        return especialidadeRepository.save(especialidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidade> updateEspecialidade(
            @PathVariable Long id,
            @RequestBody Especialidade especialidadeAtualizada) {
        return especialidadeRepository.findById(id).map(especialidade -> {
            especialidade.setDescricao(especialidadeAtualizada.getDescricao());
            Especialidade especialidadeSalva = especialidadeRepository.save(especialidade);
            return ResponseEntity.ok(especialidadeSalva);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspecialidade(@PathVariable Long id) {
        if (especialidadeRepository.existsById(id)) {
            especialidadeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
