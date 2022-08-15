package com.sitiapp.pruebatecnicasitiapp.service;

import com.sitiapp.pruebatecnicasitiapp.entity.IdentificationType;
import com.sitiapp.pruebatecnicasitiapp.repository.IdentificationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdentificationTypeServiceImpl  implements  IdentificationTypeService{

    @Autowired
    private IdentificationTypeRepository identificationTypeRepository;

    @Override
    public IdentificationType save(IdentificationType identification) {
        return this.identificationTypeRepository.save(identification);
    }

    @Override
    public IdentificationType findById(Integer id) {
        return this.identificationTypeRepository.findById(id).orElse(null);
    }

    @Override
    public List<IdentificationType> findAll() {
        return this.identificationTypeRepository.findAll();
    }
}
