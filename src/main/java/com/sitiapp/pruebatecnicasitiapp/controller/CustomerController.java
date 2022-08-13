package com.sitiapp.pruebatecnicasitiapp.controller;


import com.sitiapp.pruebatecnicasitiapp.entity.Customer;
import com.sitiapp.pruebatecnicasitiapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customerInfo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.customerService.save(customerInfo));
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getById(@PathVariable Integer id) {
        Customer customer = this.customerService.findById(id);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public List<Customer> getAll() {
        return this.customerService.findAll();
    }

    @GetMapping("{identification}/abbreviature/{abbr}")
    public ResponseEntity<Customer> findCustomerByIdentification(@PathVariable String identification, String abbr) {
        Customer customer = this.customerService.findByIdentificationAndIdentificationType(identification, abbr);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Integer id) {
        return this.customerService.delete(id);
    }
}
