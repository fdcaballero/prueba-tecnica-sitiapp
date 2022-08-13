package com.sitiapp.pruebatecnicasitiapp.repository;

import com.sitiapp.pruebatecnicasitiapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findProductByCode(String code);
}
