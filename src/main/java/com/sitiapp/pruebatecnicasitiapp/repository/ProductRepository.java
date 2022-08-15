package com.sitiapp.pruebatecnicasitiapp.repository;

import com.sitiapp.pruebatecnicasitiapp.entity.Product;
import com.sitiapp.pruebatecnicasitiapp.entity.StateType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findProductByCode(String code);

    List<Product> findProductsByState(StateType state);
}
