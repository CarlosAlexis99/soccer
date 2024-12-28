package com.carlosalexis99.soccer.domain.service;

import com.carlosalexis99.soccer.persistence.entities.Jugador;
import com.carlosalexis99.soccer.persistence.repositories.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorServiceImpl implements JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    @Override
    public List<Jugador> findAll() {
        return (List<Jugador>) jugadorRepository.findAll();
    }

    @Override
    public Optional<Jugador> findById(Integer id) {
        return jugadorRepository.findById(id);
    }

    @Override
    public Jugador save(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        Jugador jugador = jugadorRepository.findById(id).orElseThrow(() -> new Exception("Jugador not found"));
        jugador.getEquipos().forEach(equipo -> equipo.getJugadores().remove(jugador));
        jugador.getEquipos().clear();
        jugadorRepository.save(jugador);
        jugadorRepository.delete(jugador);
    }


    @Override
    public Jugador update(Integer id, Jugador jugador) {
        Jugador existingJugador = findById(id).get();
        jugador.setId(existingJugador.getId());
        return save(jugador);
    }
}
