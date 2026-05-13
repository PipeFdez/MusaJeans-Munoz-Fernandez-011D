package com.musa_jeans.service_jean.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musa_jeans.service_jean.model.Marca;
import com.musa_jeans.service_jean.repository.MarcaRepository;

@Service
public class MarcaService {

    @Autowired
    MarcaRepository marcaRepository;

    public List<Marca> listarTodos() {
        return marcaRepository.findAll();
    }

    public Marca guardar(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Optional<Marca> buscarPorId(Long id) {
        return marcaRepository.findById(id);
    }

    public void eliminar(Long id) {
        marcaRepository.deleteById(id);
    }

}
