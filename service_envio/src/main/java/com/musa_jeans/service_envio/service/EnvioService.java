package com.musa_jeans.service_envio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musa_jeans.service_envio.model.Envio;
import com.musa_jeans.service_envio.repository.EnvioRepository;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    public List<Envio> listarTodos() {
        return envioRepository.findAll();
    }

    public Optional<Envio> buscarPorId(Long id) {
        return envioRepository.findById(id);
    }

    public Envio guardar(Envio envio) {
        return envioRepository.save(envio);
    }

    public Envio actualizar(Long id, Envio envioActualizado) {

        Envio envio = envioRepository.findById(id).orElse(null);

        if (envio != null) {
            envio.setDireccion(envioActualizado.getDireccion());
            envio.setEstado(envioActualizado.getEstado());
            envio.setFechaEntrega(envioActualizado.getFechaEntrega());

            return envioRepository.save(envio);
        }

        return null;
    }

    public void eliminar(Long id) {
        envioRepository.deleteById(id);
    }
}