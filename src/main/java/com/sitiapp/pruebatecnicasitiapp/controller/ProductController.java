package com.sitiapp.pruebatecnicasitiapp.controller;

import antlr.Utils;
import com.sitiapp.pruebatecnicasitiapp.entity.Product;
import com.sitiapp.pruebatecnicasitiapp.service.ImageService;
import com.sitiapp.pruebatecnicasitiapp.service.ImageServiceImpl;
import com.sitiapp.pruebatecnicasitiapp.service.ProductService;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@RestController
@CrossOrigin
@RequestMapping("api/v1/product")
@Log4j2
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;


    //    @PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
    @GetMapping
    public List<Product> getAll() {
        return this.productService.findAll();
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.ok(this.productService.save(product));
    }

    @GetMapping("code/{code}")
    private ResponseEntity<Product> findByCode(@PathVariable String code) {
        Product product = this.productService.findByCode(code);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findBy(@PathVariable Integer id) {
        Product product = this.productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody Product product) {

        Product product1 = this.productService.findById(id);
        if (product1 == null) {
            return ResponseEntity.notFound().build();
        }
        product1.setCode(product.getCode());
        product1.setName(product.getName());
        product1.setState(product.getState());
        product1.setValue(product.getValue());
        return ResponseEntity.ok(this.productService.update(product1));

    }

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image) {
        try {
            return imageService.save(image) ? ResponseEntity.ok("image Save") :
                    ResponseEntity.badRequest().body("Error al aguardar image");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    Function<String, MediaType> getMimeType = s -> s.equals("jpeg") ?
            MediaType.IMAGE_JPEG : MediaType.IMAGE_PNG;


    @GetMapping("/image/{filename}")
    public ResponseEntity<?> getImage(@PathVariable String filename) {
        try {
            String type = filename.substring(filename.indexOf(".") + 1);

            Resource image = this.imageService.load(filename);
            if (image == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            HttpHeaders http = new HttpHeaders();
            http.setContentType(getMimeType.apply(type));
            http.setContentLength(image.contentLength());
            return new ResponseEntity(image, http, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
