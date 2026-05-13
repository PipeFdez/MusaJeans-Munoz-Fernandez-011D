package com.musa_jeans.service_jean.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musa_jeans.service_jean.model.Jean;
import com.musa_jeans.service_jean.repository.JeanRepository;

@Service
public class JeanService {

    @Autowired
    private JeanRepository jeanRepository;

    public List<Jean> listarTodos() {
        return jeanRepository.findAll();
    }

    public Optional<Jean> buscarPorId(Long id) {
        return jeanRepository.findById(id);
    }

    public Jean guardar(Jean jean) {
        return jeanRepository.save(jean);
    }

    public void eliminar(Long id) {
        jeanRepository.deleteById(id);
    }

    public List<Jean> buscarPorTalla(String talla) {
        return jeanRepository.findByTallaIgnoreCase(talla);
    }

    public List<Jean> buscarPorMarcaNombre(String nombre) {
        return jeanRepository.findByMarcaNombreIgnoreCase(nombre);
    }

}
