package com.sitiapp.pruebatecnicasitiapp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "perfil")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nombre", length = 13)
    private String name;


}
