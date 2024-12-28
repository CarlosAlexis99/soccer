package com.carlosalexis99.soccer.controller;

import com.carlosalexis99.soccer.domain.dto.JugadorDTO;
import com.carlosalexis99.soccer.domain.dto.JugadorMapper;
import com.carlosalexis99.soccer.domain.dto.response.ResponseBodyDTO;
import com.carlosalexis99.soccer.domain.service.JugadorService;
import com.carlosalexis99.soccer.persistence.entities.Jugador;
import com.carlosalexis99.soccer.util.Mensajes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jugador")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;

    @GetMapping
    public ResponseEntity<ResponseBodyDTO> showJugadores() {
        List<JugadorDTO> jugadores = jugadorService.findAll().stream().map(
                JugadorMapper.mapper::jugadorToDto
        ).collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseBodyDTO(
                Mensajes.SOLICITUD_EXITOSA, true, jugadores
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBodyDTO> findJugador(@PathVariable Integer id) {
        return jugadorService.findById(id)
                .map(jugador -> ResponseEntity.ok().body(new ResponseBodyDTO(
                        Mensajes.SOLICITUD_EXITOSA, true, JugadorMapper.mapper.jugadorToDto(jugador)
                )))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseBodyDTO(
                        Mensajes.RECURSO_INEXISTENTE, false, null
                )));
    }


    @PostMapping
    public ResponseEntity<ResponseBodyDTO> createJugador(@Valid @RequestBody JugadorDTO jugador, BindingResult bindingResult) {
        if (jugador.id() != null) {
            return ResponseEntity.badRequest().body(new ResponseBodyDTO(
                    Mensajes.ERROR_DE_CREACION, false, null
            ));
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseBodyDTO(
                    Mensajes.ERROR_DE_VALIDACION, false, null
            ));
        }

        try {
            Jugador savedJugador = jugadorService.save(JugadorMapper.mapper.jugadorDtoToJugador(jugador));
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedJugador.getId())
                    .toUri();
            return ResponseEntity.created(location).body(new ResponseBodyDTO(
                    Mensajes.SOLICITUD_EXITOSA, true, JugadorMapper.mapper.jugadorToDto(savedJugador)
            ));
        } catch (DataAccessException e) {
            return ResponseEntity.internalServerError().body(new ResponseBodyDTO(
                    Mensajes.REGISTRO_NO_PROCESADO + e.getMessage(), false, null
            ));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBodyDTO> updateJugador(@PathVariable Integer id, @Valid @RequestBody JugadorDTO jugadorDTO, BindingResult bindingResult) {

        ResponseEntity<ResponseBodyDTO> response = findJugador(id);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return response;
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseBodyDTO(
                    Mensajes.ERROR_DE_VALIDACION, false, bindingResult.getAllErrors()
            ));
        }

        try {
            Jugador jugador = jugadorService.update(id, JugadorMapper.mapper.jugadorDtoToJugador(jugadorDTO));
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand()
                    .toUri();
            return ResponseEntity.ok().location(location).body(new ResponseBodyDTO(
                    Mensajes.SOLICITUD_EXITOSA, true, JugadorMapper.mapper.jugadorToDto(jugador)
            ));
        } catch (DataAccessException e) {
            return ResponseEntity.internalServerError().body(new ResponseBodyDTO(
                    Mensajes.SOLICITUD_FALLIDA + e.getMessage(), false, null
            ));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyDTO> deleteJugador(@PathVariable Integer id) throws Exception {
        jugadorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

