package com.sitiapp.pruebatecnicasitiapp.service;

import com.sitiapp.pruebatecnicasitiapp.entity.IdentificationType;

import java.util.List;

public interface IdentificationTypeService {


    IdentificationType save(IdentificationType identification);

    IdentificationType findById(Integer id);
    List<IdentificationType> findAll();
}
