package com.faesa.projetointegrador2.repositories;

import com.faesa.projetointegrador2.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Métodos adicionais para Paciente, se necessário
}
