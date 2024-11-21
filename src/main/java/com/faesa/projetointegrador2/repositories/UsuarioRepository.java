package com.faesa.projetointegrador2.repositories;

import com.faesa.projetointegrador2.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Métodos adicionais para Usuario, se necessário
}