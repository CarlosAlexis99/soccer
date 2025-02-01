package com.carlosalexis99.soccer.domain.service;

import com.carlosalexis99.soccer.persistence.entities.Equipo;
import com.carlosalexis99.soccer.persistence.repositories.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Override
    public List<Equipo> findAll() {
        return (List<Equipo>) equipoRepository.findAll();
    }

    @Override
    public List<Equipo> findAll(String nombre) {
        if (nombre == null || nombre.isEmpty()){
            return (List<Equipo>) equipoRepository.findAll();
        }
        String nombreToLower = nombre.toLowerCase();
        return (List<Equipo>) equipoRepository.findAllByNombreContaining(nombreToLower);
    }

    @Override
    public List<Equipo> findAll(Specification specification) {
        return (List<Equipo>) equipoRepository.findAll(specification);
    }

    @Override
    public Optional<Equipo> findById(Integer id) {
        return equipoRepository.findById(id);
    }

    @Override
    public Equipo save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        Equipo equipo = equipoRepository.findById(id).orElseThrow(() -> new Exception("Equipo not found"));
        equipo.getJugadores().forEach(jugador -> jugador.getEquipos().remove(equipo));
        equipo.getJugadores().clear();
        equipo.getLigas().forEach(liga -> liga.getEquipos().remove(equipo));
        equipo.getLigas().clear();
        equipoRepository.save(equipo);
        equipoRepository.delete(equipo);
    }

    @Override
    public void deleteEquipoFromLiga(Integer idEquipo, Integer idLiga) throws Exception {
        Equipo equipo = equipoRepository.findById(idEquipo).orElseThrow(() -> new Exception("Equipo not found"));
        equipo.getLigas().forEach(liga -> {
            if (liga.getId().equals(idLiga)) {
                liga.getEquipos().remove(equipo);
            }
        });
        equipoRepository.save(equipo);
    }


    @Override
    public Equipo update(Integer id, Equipo equipo) {
        Equipo existingEquipo = findById(id).get();
        equipo.setId(existingEquipo.getId());
        return save(equipo);
    }

    @Override
    public List<Equipo> getEquipoByUsuarioId(Integer id) {
        return equipoRepository.getEquipoByUsuarioId(id);
    }
}
