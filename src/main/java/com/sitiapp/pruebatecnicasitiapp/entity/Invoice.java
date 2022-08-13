package com.sitiapp.pruebatecnicasitiapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "facturas")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Customer customer;


    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceDetails> facturaDetalle;
}
