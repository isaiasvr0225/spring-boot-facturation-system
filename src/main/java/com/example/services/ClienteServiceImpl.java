package com.example.services;

import com.example.models.entities.Cliente;
import com.example.models.entities.Producto;
import com.example.models.repositories.IClienteRepository;
import com.example.models.repositories.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return clienteRepository.getById(id);
    }


    @Override
    @Transactional
    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findProductByTerm(String term) {
        return productoRepository.findProductoByTerm(term);
    }
}
