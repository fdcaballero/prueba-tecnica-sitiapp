package com.sitiapp.pruebatecnicasitiapp.service;

import com.sitiapp.pruebatecnicasitiapp.dto.ReportProduct;
import com.sitiapp.pruebatecnicasitiapp.entity.Invoice;
import com.sitiapp.pruebatecnicasitiapp.repository.InvoiceRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ReportProduct> getAllProductMostSelling() {


        return this.invoiceRepository.FindproductMostSelling().stream().map(obj -> {
            Object object[] = (Object[]) obj;
            return new ReportProduct((Date) object[0], (String) object[1], (BigDecimal) object[2]);
        }).collect(Collectors.toList());
    }
}
