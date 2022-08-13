package com.sitiapp.pruebatecnicasitiapp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "factura_detalle")
public class InvoiceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "cantidad")
    private Integer amount;

    @Column(name = "valor_unitario")
    private Integer valorUnitario;

    @ManyToOne
    private Invoice invoice;

    @ManyToOne
//    @Column(name = "id_producto")
    private Product product;


}
