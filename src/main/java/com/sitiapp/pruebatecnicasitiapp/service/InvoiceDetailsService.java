package com.sitiapp.pruebatecnicasitiapp.service;

import com.sitiapp.pruebatecnicasitiapp.entity.InvoiceDetails;

import java.util.List;

public interface InvoiceDetailsService {

    InvoiceDetails save(InvoiceDetails invoiceDetails);

    List<InvoiceDetails> saveAll(List<InvoiceDetails> invoiceDetailsList);

    InvoiceDetails findById(Integer id);

    boolean delete(Integer id);
}
