package com.carlosalexis99.soccer.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ligas")
public class Liga {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;
    private String direccion;


    @Column(precision = 7, scale = 2)
    private BigDecimal premio;

    private String logo;
    private Integer division;

    //Llave foránea FK
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonManagedReference
    private Usuario usuario;


    //Tabla de unión para llevar el control de los equipos por liga
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "equipos_ligas",
            joinColumns = @JoinColumn(name = "liga_id"),
            inverseJoinColumns = @JoinColumn(name = "equipo_id")
    )
    @JsonManagedReference
    private List<Equipo> equipos = new ArrayList<>();

}