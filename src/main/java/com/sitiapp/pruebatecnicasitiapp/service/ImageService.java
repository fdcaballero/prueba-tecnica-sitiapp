package com.sitiapp.pruebatecnicasitiapp.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface ImageService {

    boolean save(MultipartFile file) throws Exception;

    Resource load(String name) throws Exception;

//    Stream<Path> loadAll() throws Exception;

//    boolean exist(String name);
}
