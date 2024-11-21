package com.faesa.projetointegrador2.repositories;

import com.faesa.projetointegrador2.models.Psicologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PsicologoRepository extends JpaRepository<Psicologo, Long> {
    // Métodos adicionais para Psicologo, se necessário
}
