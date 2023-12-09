package com.example.services;

import com.example.models.entities.Cliente;
import com.example.models.entities.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    List<Cliente> findAll();

    Page<Cliente> findAll(Pageable pageable);

    void save(Cliente cliente);

    Cliente findById(Long id);

    void deleteById(Long id);

    List<Producto> findProductByTerm(String term);
}
