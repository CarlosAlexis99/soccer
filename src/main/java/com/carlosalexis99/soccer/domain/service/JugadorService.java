package com.carlosalexis99.soccer.domain.service;

import com.carlosalexis99.soccer.persistence.entities.Jugador;

import java.util.List;
import java.util.Optional;

public interface JugadorService {

    List<Jugador> findAll();
    Optional<Jugador> findById(Integer id);
    Jugador save(Jugador jugador);
    void deleteById(Integer id) throws Exception;
    Jugador update(Integer id, Jugador jugador);

}
