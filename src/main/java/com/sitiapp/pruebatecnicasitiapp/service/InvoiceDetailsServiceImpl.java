package com.sitiapp.pruebatecnicasitiapp.service;

import com.sitiapp.pruebatecnicasitiapp.repository.InvoiceDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceDetailsServiceImpl implements  InvoiceDetailsService{


    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;
}
