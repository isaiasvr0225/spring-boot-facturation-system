package com.example.models.repositories;

import com.example.models.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario findUsuarioByUsername(String username);

    Usuario findByUsername(String username);
}
