package com.faesa.projetointegrador2.repositories;

import com.faesa.projetointegrador2.models.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
    // Métodos adicionais para Especialidade, se necessário
}
