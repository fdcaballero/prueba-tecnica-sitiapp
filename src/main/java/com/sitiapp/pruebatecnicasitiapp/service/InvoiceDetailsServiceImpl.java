package com.sitiapp.pruebatecnicasitiapp.service;

import com.sitiapp.pruebatecnicasitiapp.entity.InvoiceDetails;
import com.sitiapp.pruebatecnicasitiapp.repository.InvoiceDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceDetailsServiceImpl implements InvoiceDetailsService {


    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;

    @Override
    public InvoiceDetails save(InvoiceDetails invoiceDetails) {
        return this.invoiceDetailsRepository.save(invoiceDetails);
    }

    @Override
    public List<InvoiceDetails> saveAll(List<InvoiceDetails> invoiceDetailsList) {
        return this.invoiceDetailsRepository.saveAll(invoiceDetailsList);
    }

    @Override
    public InvoiceDetails findById(Integer id) {
        return this.invoiceDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            this.invoiceDetailsRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
