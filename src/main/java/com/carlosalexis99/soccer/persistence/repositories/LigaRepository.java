package com.carlosalexis99.soccer.persistence.repositories;

import com.carlosalexis99.soccer.persistence.entities.Liga;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigaRepository extends CrudRepository<Liga, Integer> {

    //Usando QueryMethods
    List<Liga> getLigaByUsuarioId(Integer id);
}
