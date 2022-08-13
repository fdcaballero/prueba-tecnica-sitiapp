package com.sitiapp.pruebatecnicasitiapp.service;

import com.sitiapp.pruebatecnicasitiapp.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);

    List<Customer> findAll();

    Customer findById(Integer id);

    boolean delete(Integer id);

    Customer findByIdentificationAndIdentificationType(String identification, String Abr);


}
