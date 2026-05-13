package com.musa_jeans.service_jean.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musa_jeans.service_jean.model.Modelo;
import com.musa_jeans.service_jean.repository.ModeloRepository;

@Service
public class ModeloService {

    @Autowired
    ModeloRepository modeloRepository;

    public List<Modelo> listarTodos() {
        return modeloRepository.findAll();
    }

    public Modelo guardar(Modelo modelo) {
        return modeloRepository.save(modelo);
    }

    public Optional<Modelo> buscarPorId(Long id) {
        return modeloRepository.findById(id);
    }

    public void eliminar(Long id) {
        modeloRepository.deleteById(id);
    }

}
