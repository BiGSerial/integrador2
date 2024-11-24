package com.faesa.projetointegrador2.services;

import com.faesa.projetointegrador2.models.Paciente;
import com.faesa.projetointegrador2.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente atualizarPaciente(Long id, Paciente pacienteAtualizado) {
        Paciente pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));


        pacienteExistente.setNome(pacienteAtualizado.getNome());
        pacienteExistente.setTelefone(pacienteAtualizado.getTelefone());
        pacienteExistente.setHistoricoTestes(pacienteAtualizado.getHistoricoTestes());
        pacienteExistente.setIdade(pacienteAtualizado.getIdade());


        return pacienteRepository.save(pacienteExistente);
    }
}
