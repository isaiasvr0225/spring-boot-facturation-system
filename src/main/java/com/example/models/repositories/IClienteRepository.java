package com.example.models.repositories;

import com.example.models.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {

    @Override
    Cliente getById(Long id);
}
