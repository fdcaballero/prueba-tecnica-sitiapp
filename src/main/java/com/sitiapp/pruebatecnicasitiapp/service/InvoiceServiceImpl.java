package com.sitiapp.pruebatecnicasitiapp.service;

import com.sitiapp.pruebatecnicasitiapp.entity.Invoice;
import com.sitiapp.pruebatecnicasitiapp.repository.InvoiceRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;


    @Override
    public Invoice save(Invoice invoice) {
        return this.invoiceRepository.save(invoice);
    }

    @Override
    public Invoice findById(Integer id) {
        return this.invoiceRepository.findById(id).orElse(null);
    }

    @Override
    public Invoice update(Invoice invoice) {
        return this.invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> findAll() {
        return this.invoiceRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        try {
            this.invoiceRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
