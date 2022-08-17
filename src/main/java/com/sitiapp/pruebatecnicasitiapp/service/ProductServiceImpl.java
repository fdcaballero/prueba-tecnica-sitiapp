package com.sitiapp.pruebatecnicasitiapp.service;

import com.sitiapp.pruebatecnicasitiapp.entity.Product;
import com.sitiapp.pruebatecnicasitiapp.entity.StateType;
import com.sitiapp.pruebatecnicasitiapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findById(Integer id) {
        return this.productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findByCode(String code) {
        return this.productRepository.findProductByCode(code);
    }

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public List<Product> findAllByState(StateType state) {
        return this.productRepository.findProductsByState(state);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            this.productRepository.deleteById(id);
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    @Override
    public Product update(Product product) {
        return this.productRepository.save(product);
    }
}
