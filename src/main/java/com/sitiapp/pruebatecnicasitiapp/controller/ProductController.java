package com.sitiapp.pruebatecnicasitiapp.controller;

import antlr.Utils;
import com.sitiapp.pruebatecnicasitiapp.common.BASE64DecodedMultipartFile;
import com.sitiapp.pruebatecnicasitiapp.dto.ImageDto;
import com.sitiapp.pruebatecnicasitiapp.dto.ProductRequestDto;
import com.sitiapp.pruebatecnicasitiapp.entity.Product;
import com.sitiapp.pruebatecnicasitiapp.entity.StateType;
import com.sitiapp.pruebatecnicasitiapp.service.ImageService;
import com.sitiapp.pruebatecnicasitiapp.service.ImageServiceImpl;
import com.sitiapp.pruebatecnicasitiapp.service.ProductService;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;
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

import java.io.File;
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
    public ResponseEntity<Product> create(@RequestBody ProductRequestDto request) {
        try {
            Product product = request.getProduct();
            ImageDto image = request.getImage();
            product.setImage(image.getName());
            Product response = this.productService.save(product);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }


    }

    @GetMapping("code/{code}")
    private List<Product> findByCode(@PathVariable String code) {
        return this.productService.findByCode(code);

    }

    @GetMapping("state/{state}")
    public List<Product> findAllByState(@PathVariable StateType state) {
        return this.productService.findAllByState(state);
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
    public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody ProductRequestDto request) {
        try {
            ImageDto image = request.getImage();
            Product product = request.getProduct();
            Product product1 = this.productService.findById(id);
            if (product1 == null) {
                return ResponseEntity.notFound().build();
            }

            product1.setCode(product.getCode());
            product1.setName(product.getName());
            product1.setState(product.getState());
            product1.setValue(product.getValue());
            log.info(product1.getBase64());
            return ResponseEntity.ok(this.productService.update(product1));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }

    }

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image) {
        try {
            log.info("carga img " + image.getName() + " " + image.getContentType() + " " + image.getOriginalFilename());
            return imageService.save(image) ? ResponseEntity.ok("image Save") : ResponseEntity.badRequest().body("Error al aguardar image");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    Function<String, MediaType> getMimeType = s -> s.equals("jpeg") ? MediaType.IMAGE_JPEG : MediaType.IMAGE_PNG;


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
