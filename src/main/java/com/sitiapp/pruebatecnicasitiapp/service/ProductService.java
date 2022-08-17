package com.sitiapp.pruebatecnicasitiapp.service;

import com.sitiapp.pruebatecnicasitiapp.entity.Product;
import com.sitiapp.pruebatecnicasitiapp.entity.StateType;

import java.util.List;

public interface ProductService {

    Product findById(Integer id);

    List<Product> findByCode(String code);

    Product save(Product product);

    List<Product> findAll();


    List<Product> findAllByState(StateType state);

    boolean delete(Integer id);

    Product update(Product product);

}
