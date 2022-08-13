package com.sitiapp.pruebatecnicasitiapp.service;

import com.sitiapp.pruebatecnicasitiapp.repository.IdentificationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdentificationTypeServiceImpl  implements  IdentificationTypeService{

    @Autowired
    private IdentificationTypeRepository identificationTypeRepository;
}
