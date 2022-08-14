package com.sitiapp.pruebatecnicasitiapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "facturas")
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Customer customer;


    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "invoice")
    private List<InvoiceDetails> facturaDetalle = new ArrayList<>();
}
