package com.sitiapp.pruebatecnicasitiapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clientes")
@Data
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "identificacion", length = 100)
    private String identification;

    @Column(name = "razon_social", length = 100)
    private String businessName;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date registratedDate = new Date();

    @Column(name = "estado", length = 1)
    private String state;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Invoice> invoices;

    @ManyToOne(fetch = FetchType.LAZY)
//    @Column(name = "tipo_identificacion")
    private IdentificationType identificationType;
}
