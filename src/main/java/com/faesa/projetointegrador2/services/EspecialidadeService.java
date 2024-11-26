package com.faesa.projetointegrador2.services;

import com.faesa.projetointegrador2.models.Especialidade;
import com.faesa.projetointegrador2.repositories.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public List<Especialidade> listarTodas() {
        return especialidadeRepository.findAll();
    }

    public Especialidade criar(Especialidade especialidade) {
        return especialidadeRepository.save(especialidade);
    }

    public void deletar(Long id) {
        especialidadeRepository.deleteById(id);
    }

    public Especialidade atualizar(Long id, Especialidade especialidadeAtualizada) {
        Especialidade especialidadeExistente = especialidadeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Especialidade Não Encotrada."));

        especialidadeExistente.setDescricao(especialidadeAtualizada.getDescricao());

        return especialidadeRepository.save(especialidadeExistente);
    }

}
