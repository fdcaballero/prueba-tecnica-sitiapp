package com.sitiapp.pruebatecnicasitiapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "productos")
@Entity
@Data
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nombre", length = 100)
    private String name;

    @Column(name = "estado", length = 45)
    private String state;

    @Column(name = "valor_unitario")
    private Integer value;

//    @OneToMany(mappedBy = "product")
//    private List<InvoiceDetails> invoiceDetails;

    @Column(name = "codigo")
    private String code;
}

