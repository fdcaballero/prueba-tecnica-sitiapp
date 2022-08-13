package com.sitiapp.pruebatecnicasitiapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "usuario", unique = true)

    private String userName;

    @Column(name = "nombre", nullable = false)
    private String name;

    @Column(name = "apellido")
    private String lastName;

    @Column(nullable = false, name = "contrasena")
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Profile perfil;
}
