package com.sitiapp.pruebatecnicasitiapp.controller;

import com.sitiapp.pruebatecnicasitiapp.entity.IdentificationType;
import com.sitiapp.pruebatecnicasitiapp.service.IdentificationTypeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@CrossOrigin
@RequestMapping("api/v1/indentification")

public class IdentificationTypeController {


    @Autowired
    private IdentificationTypeService identificationTypeService;

    @PostMapping
    public ResponseEntity<IdentificationType> create(@RequestBody IdentificationType identification) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.identificationTypeService.save(identification));
    }

    @GetMapping
    public List<IdentificationType> getAll() {
        return this.identificationTypeService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<IdentificationType> getById(@PathVariable Integer id) {
        IdentificationType identification = this.identificationTypeService.findById(id);
        return (identification != null) ? ResponseEntity.ok(identification) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
