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

  @PutMapping("/{id}")
public ResponseEntity<Psicologo> updatePsicologo(@PathVariable Long id, @RequestBody Psicologo updatedPsicologo) {
    return psicologoRepository.findById(id)
            .map(psicologo -> {
                psicologo.setNome(updatedPsicologo.getNome());
                psicologo.setCrp(updatedPsicologo.getCrp());
                psicologo.setTelefone(updatedPsicologo.getTelefone());
                psicologo.setEspecialidade(updatedPsicologo.getEspecialidade());
                return ResponseEntity.ok(psicologoRepository.save(psicologo));
            })
            .orElse(ResponseEntity.notFound().build());
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePsicologo(@PathVariable Long id) {
        if (psicologoRepository.existsById(id)) {
            psicologoRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        }
        return ResponseEntity.notFound().build(); // Retorna 404 Not Found
    }
}
