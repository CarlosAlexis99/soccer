package com.carlosalexis99.soccer.persistence.repositories;

import com.carlosalexis99.soccer.persistence.entities.Jugador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends CrudRepository<Jugador, Integer> {
}
