package com.sitiapp.pruebatecnicasitiapp.service;

import com.sitiapp.pruebatecnicasitiapp.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    Invoice save(Invoice invoice);

    Invoice findById(Integer id);

    Invoice update(Invoice invoice);

    List<Invoice> findAll();

    boolean delete(Integer id);

}
