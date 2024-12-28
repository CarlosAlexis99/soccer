package com.carlosalexis99.soccer.persistence.repositories;

import com.carlosalexis99.soccer.persistence.entities.Equipo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends CrudRepository<Equipo, Integer> {

    //QueryMethods
    List<Equipo> getEquipoByUsuarioId(Integer id);
}
