package com.sitiapp.pruebatecnicasitiapp.controller;

import com.sitiapp.pruebatecnicasitiapp.entity.Invoice;
import com.sitiapp.pruebatecnicasitiapp.entity.InvoiceDetails;
import com.sitiapp.pruebatecnicasitiapp.service.InvoiceDetailsService;
import com.sitiapp.pruebatecnicasitiapp.service.InvoiceService;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/invoice")
@Log4j2
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceDetailsService invoiceDetailsService;

    @GetMapping
    public ResponseEntity<List<Invoice>> getAll() throws IllegalStateException {
        try {

            return ResponseEntity.ok(this.invoiceService.findAll());
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Invoice> create(@RequestBody Invoice invoice) {
        try {
            List<InvoiceDetails> invoiceDetails = invoice.getFacturaDetalle();
            invoice.setFacturaDetalle(new ArrayList<>());
            Invoice invoicedb = this.invoiceService.save(invoice);
            if (invoicedb == null) {
                return ResponseEntity.badRequest().build();
            }
            for (int i = 0; i < invoiceDetails.size(); i++) {
                invoiceDetails.get(i).setInvoice(invoicedb);
            }
            this.invoiceDetailsService.saveAll(invoiceDetails);

            return ResponseEntity.status(HttpStatus.CREATED).body(this.invoiceService.findById(invoicedb.getId()));

        } catch (Exception e) {
//            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Integer id) {
        Invoice invoice = this.invoiceService.findById(id);
        if (invoice == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoice);
    }

}
