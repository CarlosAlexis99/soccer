package com.carlosalexis99.soccer.domain.service;

import com.carlosalexis99.soccer.persistence.entities.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoService {

    List<Equipo> findAll();
    Optional<Equipo> findById(Integer id);
    Equipo save(Equipo equipo);
    void deleteById(Integer id) throws Exception;
    Equipo update(Integer id, Equipo equipo);
    List<Equipo> getEquipoByUsuarioId(Integer id);
}
