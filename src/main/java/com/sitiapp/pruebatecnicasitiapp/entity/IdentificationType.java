package com.sitiapp.pruebatecnicasitiapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "tipos_identificaciones")
@Entity
@Data
public class IdentificationType  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "abreviatura", length = 3)
    private String abbreviation;

    @Column(name = "descripcion", length = 100)
    private String description;


}
